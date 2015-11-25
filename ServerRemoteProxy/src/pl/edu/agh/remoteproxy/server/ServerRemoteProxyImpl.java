package pl.edu.agh.remoteproxy.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.remoteproxy.common.remote.ClientRemoteProxy;
import pl.edu.agh.remoteproxy.common.remote.RemoteConfig;
import pl.edu.agh.remoteproxy.common.remote.ServerRemoteProxy;
import pl.edu.agh.remoteproxy.common.remote.Vector;

public class ServerRemoteProxyImpl extends UnicastRemoteObject implements ServerRemoteProxy{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ClientRemoteProxy> clients;

	public ServerRemoteProxyImpl() throws RemoteException {
		clients = new LinkedList<>();
	}

	@Override
	public synchronized void initialise(ClientRemoteProxy client) throws RemoteException {
		clients.add(client);
	}

	@Override
	public synchronized void broadcast(Vector vec) throws RemoteException {
		for(ClientRemoteProxy client : clients) {
			client.receive(vec);
		}	
	}
	
	public static void main(String[] args) {
		try {
//			Naming.rebind(RemoteConfig.RMI_ID, new ServerRemoteProxyImpl());
			ServerRemoteProxy server = new ServerRemoteProxyImpl();
			Registry registry = LocateRegistry.createRegistry(RemoteConfig.PORT);
			registry.bind(RemoteConfig.RMI_ID, server);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package pl.edu.agh.remoteproxy.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.remoteproxy.common.remote.ClientService;
import pl.edu.agh.remoteproxy.common.remote.RemoteConfig;
import pl.edu.agh.remoteproxy.common.remote.ServerService;
import pl.edu.agh.remoteproxy.common.remote.Vector;

public class ServerServiceImpl extends UnicastRemoteObject implements ServerService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ClientService> clients;

	public ServerServiceImpl() throws RemoteException {
		clients = new LinkedList<>();
	}

	@Override
	public synchronized void initialise(ClientService client) throws RemoteException {
		clients.add(client);
	}

	@Override
	public synchronized void broadcast(Vector vec) throws RemoteException {
		for(ClientService client : clients) {
			client.receive(vec);
		}	
	}
	
	public static void main(String[] args) {
		try {
//			Naming.rebind(RemoteConfig.RMI_ID, new ServerRemoteProxyImpl());
			ServerService server = new ServerServiceImpl();
			Registry registry = LocateRegistry.createRegistry(RemoteConfig.PORT);
			registry.bind(RemoteConfig.RMI_ID, server);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

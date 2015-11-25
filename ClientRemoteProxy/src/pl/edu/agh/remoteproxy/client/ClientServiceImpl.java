package pl.edu.agh.remoteproxy.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

import pl.edu.agh.remoteproxy.common.remote.ClientService;
import pl.edu.agh.remoteproxy.common.remote.RemoteConfig;
import pl.edu.agh.remoteproxy.common.remote.ServerService;
import pl.edu.agh.remoteproxy.common.remote.Vector;

public class ClientServiceImpl extends UnicastRemoteObject implements ClientService, Runnable {
	private static final long serialVersionUID = 1L;
	private ServerService server;

	public ClientServiceImpl(ServerService server) throws RemoteException {
		this.server = server;
		this.server.initialise(this);
	}

	@Override
	public synchronized void receive(Vector vec) throws RemoteException {
		System.out.println("Received: " + vec);
	}

	@Override
	public void run() {
		Vector vec;

		Scanner in = new Scanner(System.in);
		
		while (true) {
			try {
				in.nextLine();
				Random rand = new Random();
				vec = new Vector();
				vec.setX(rand.nextInt() % 100);
				vec.setY(rand.nextInt() % 100);
				server.broadcast(vec);
				System.out.println("Sent: " + vec);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String url = "rmi://localhost/" + RemoteConfig.RMI_ID;
		try {
//			ServerRemoteProxy server = (ServerRemoteProxy) Naming.lookup(url);
//			new Thread(new ClientRemoteProxyImpl(server)).start();
			Registry registry = LocateRegistry.getRegistry("localhost", RemoteConfig.PORT);
			ServerService server = (ServerService) registry.lookup(RemoteConfig.RMI_ID);
			new Thread(new ClientServiceImpl(server)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

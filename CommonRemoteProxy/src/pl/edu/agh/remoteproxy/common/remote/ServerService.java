package pl.edu.agh.remoteproxy.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote {
	void initialise(ClientService client) throws RemoteException;

	void broadcast(Vector vector) throws RemoteException;

}

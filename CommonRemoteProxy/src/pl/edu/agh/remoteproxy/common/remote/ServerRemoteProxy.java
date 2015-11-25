package pl.edu.agh.remoteproxy.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemoteProxy extends Remote {
	void initialise(ClientRemoteProxy client) throws RemoteException;

	void broadcast(Vector vector) throws RemoteException;

}

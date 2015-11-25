package pl.edu.agh.remoteproxy.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRemoteProxy extends Remote {
	void receive(Vector vec) throws RemoteException;
}

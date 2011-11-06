package RMITesting;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ISimpleServer extends Remote {
	public String toUpperCase(String string) throws RemoteException;
}

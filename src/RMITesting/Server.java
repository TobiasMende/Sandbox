package RMITesting;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			SimpleServer server = new SimpleServer();
			ISimpleServer stub = (ISimpleServer) UnicastRemoteObject.exportObject(server,0);
			registry.rebind("SimpleServer", stub);
			System.out.println("Server initialized");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

}

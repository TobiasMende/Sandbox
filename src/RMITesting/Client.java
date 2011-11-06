package RMITesting;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ISimpleServer server;
		try {
			server = (ISimpleServer)Naming.lookup("SimpleServer");
			System.out.println("Client initialized");
			String response = server.toUpperCase("Bla bla blub");
			System.out.println(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

	}
}

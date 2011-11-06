package RMITesting;
import java.rmi.RemoteException;


public class SimpleServer implements ISimpleServer {
	private static final long serialVersionUID = -593338372729658649L;

	public SimpleServer() throws RemoteException {
	}

	@Override
	public String toUpperCase(String string){
		return string.toUpperCase();
	}
}

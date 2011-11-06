package ThreadTesting;

public class Runnable implements java.lang.Runnable {
	private boolean interrupted = false;
	private SubThread workingThread;
	
	public Runnable(SubThread t) {
		workingThread = t;
	}
	@Override
	public void run() {
		while(!interrupted) {
			System.out.println(workingThread.getValue());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				interrupt();
				System.out.println("Runnable was interrupted");
			}
		}
	}
	
	public void interrupt() {
		interrupted = true;
	}

}

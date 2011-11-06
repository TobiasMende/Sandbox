package ThreadTesting;

public class MainThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubThread testThread = new SubThread();
		testThread.setShowOther(true);
		testThread.start();
		System.out.println("TestThread was started");
		Runnable rn = new Runnable(testThread);
		Thread t = new Thread(rn);
		t.start();
		for(int i = 0; i < 20; i++) {
			testThread.setValue((double)i);
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		t.interrupt();
		testThread.interrupt();
	}

}
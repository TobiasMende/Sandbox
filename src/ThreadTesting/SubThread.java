package ThreadTesting;

public class SubThread extends Thread {
	
	private boolean showOther = false;
	private int result = 0;
	private double value =0;
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public synchronized void setValue(double value) {
		System.out.println("Enterring setter");
	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.value = value;
		System.out.println("--- setting value ---> "+value);
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the showOther
	 */
	public boolean isShowOther() {
		return showOther;
	}

	/**
	 * @param showOther the showOther to set
	 */
	public void setShowOther(boolean showOther) {
		this.showOther = showOther;
	}

	@Override
	public void run() {
		super.run();
		while(!isInterrupted()) {
			setValue(Math.random());
			System.err.println(getValue());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				interrupt();
				System.out.println("SubThread was interrupted");
			}
		}
		
	}
}

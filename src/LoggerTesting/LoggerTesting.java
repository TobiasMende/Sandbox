package LoggerTesting;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test project for testing logger integration with two different loggers
 * @author Tobias Mende
 *
 */
public class LoggerTesting {
	/** Default logger for all application information and debugging */
	private static Logger log = Logger.getLogger("LoggerTesting"); //TODO define highest package here instead of the class
	/** Default logger for logging simulation information. Should be used from the whole project */
	public static final Logger SIM_LOG = Logger.getLogger("SimLog");
	/** flag for determining wether productiv mode is on or off */
	public static final boolean PRODUCTIVE_MODE = false;
	/** Handlers for logging in file */
	private static FileHandler devFileHandler, simFileHandler;
	/** Handlers for logging on the console */
	private static ExtendedConsoleHandler devConsoleHandler, simConsoleHandler;
	
	public static void main(String[] args) {
		setupLogging();
		
		// logging test
		SIM_LOG.info("Eine Simulations Info Log");
		log.info("Ein Application Info Log");
		SIM_LOG.fine("Eine Simulations Fine Log");
		log.fine("Ein Application Fine Log");
		log.severe("Critical App");
		SIM_LOG.severe("Critical Simulation");
		
		
	}
	private static void setupLogging() {
		// setup logging
		try {
			log.setUseParentHandlers(false);
			SIM_LOG.setUseParentHandlers(false);
			
			//configure the lowest levels for different loggers
			log.setLevel(Level.ALL);
			SIM_LOG.setLevel(Level.INFO);
			
			configureDevelopmentLogging();
			log.addHandler(devConsoleHandler);
			log.addHandler(devFileHandler);
			if(PRODUCTIVE_MODE) {
				//Create the sim log only in productive mode
				configureSimulationLogging();
				SIM_LOG.addHandler(simConsoleHandler);
				SIM_LOG.addHandler(simFileHandler);
			} else {
				//use the dev console for sim logging only in dev mode
				SIM_LOG.addHandler(devConsoleHandler);
			}
			// write simulation information to the dev log in every case
			SIM_LOG.addHandler(devFileHandler);
		} catch (Exception e) {
			log.info("Es wird keine Protokolldatei erzeugt: " + e.getMessage());
		}
	}
	/**
	 * Configuring the development logging
	 * @throws SecurityException if no write permissions are given for the log directory
	 * @throws IOException if an error occurred while writing the log file
	 */
	private static void configureDevelopmentLogging() throws SecurityException, IOException {
		//Configure console logger
		devConsoleHandler = new ExtendedConsoleHandler();
		devConsoleHandler.setFormatter(new DevLogFormatter());
		
		// Configure file logger
		long time = Calendar.getInstance().getTimeInMillis();
		devFileHandler = new FileHandler(String.format("log/%d.log", time));
		devFileHandler.setFormatter(new DevLogFormatter()); //Use HTMLFormatter for fancy output
		
		
		if(PRODUCTIVE_MODE) {
			// define the behavior of the development handler in productive mode
			devConsoleHandler.setLevel(Level.SEVERE); //show important errors on the console
			// log everything important into the dev log file
			devFileHandler.setLevel(Level.CONFIG);
		} else {
			// log more information on the console
			devConsoleHandler.setLevel(Level.INFO);
			// log everything to the log file
			devFileHandler.setLevel(Level.ALL);
		}
	}
	
	/**
	 * Configuring the simulation logging
	 * @throws SecurityException if no write permissions are given for the log directory
	 * @throws IOException if an error occurred while writing the log file
	 */
	private static void configureSimulationLogging() throws SecurityException, IOException {
		//Configure console logger
		simConsoleHandler = new ExtendedConsoleHandler();
		simConsoleHandler.setFormatter(new SimLogFormatter());
		
		// Configure file logger
		long time = Calendar.getInstance().getTimeInMillis();
		
		simFileHandler = new FileHandler(String.format("log/sim-%d.log", time));
		simFileHandler.setFormatter(new SimLogFormatter()); //Use HTMLFormatter for fancy output
		simConsoleHandler.setLevel(Level.INFO);
		simFileHandler.setLevel(Level.ALL);
		
	}
}

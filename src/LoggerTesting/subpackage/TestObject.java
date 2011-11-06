package LoggerTesting.subpackage;

import java.util.logging.Logger;

import LoggerTesting.LoggerTesting;

/**
 * Class for explaining the use of the loggers in sub packages and classes
 * @author Tobias Mende
 *
 */
public class TestObject {
	private static Logger log = Logger.getLogger(TestObject.class.getName());
	 public TestObject() {
		 log.entering(TestObject.class.getName(), "default constructor");
		 log.info("Info Log");
		 LoggerTesting.SIM_LOG.info("Sim Log Info");
		 log.severe("Bad things happen");
		 LoggerTesting.SIM_LOG.warning("Sim warning test");
		 log.exiting(TestObject.class.getName(), "default constructor");
	}
}

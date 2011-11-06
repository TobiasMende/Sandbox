package LoggerTesting;

import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Extended ConsoleHandler which is able to decide whether using System.out or
 * System.err according to the log level.
 * 
 * @author Tobias Mende
 * 
 */
public class ExtendedConsoleHandler extends Handler {

	@Override
	public void close() throws SecurityException {
		// nothing to do here
	}

	@Override
	public void flush() {
		// nothing to do here
	}

	@Override
	public void publish(LogRecord record) {
		if (record.getLevel().intValue() < getLevel().intValue()) {
			//ignore messages below the current log level
			return;
		}
		if (getFormatter() == null) {
			setFormatter(new SimpleFormatter());
		}

		try {
			String message = getFormatter().format(record);
			if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
				System.err.write(message.getBytes());
			} else {
				System.out.write(message.getBytes());
			}
		} catch (Exception exception) {
			reportError(null, exception, ErrorManager.FORMAT_FAILURE);
			return;
		}
	}

}

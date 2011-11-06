package LoggerTesting;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Simple log formatter for formatting the development log files in non
 * productive mode.
 * 
 * @author Tobias Mende
 * 
 */
public class DevLogFormatter extends Formatter {

	@Override
	public String format(LogRecord rec) {
		StringBuffer r = new StringBuffer();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM);
		String dateStr = dateFormat.format(new Date(rec.getMillis()));
		r.append(dateStr);
		r.append(": " + rec.getSourceClassName());
		r.append(":" + rec.getSourceMethodName()+" \t ");
		r.append(rec.getLevel().getLocalizedName());
		r.append("\t\t" + rec.getMessage());
		r.append("\n");
		return r.toString();
	}

}

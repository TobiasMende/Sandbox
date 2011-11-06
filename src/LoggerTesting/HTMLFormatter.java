package LoggerTesting;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Helper class to format a Logger with HTML.
 * 
 * @author Folke Will, Tobias Mende
 * 
 */
public class HTMLFormatter extends java.util.logging.Formatter {
	@Override
	public String format(LogRecord rec) {
		StringBuffer r = new StringBuffer();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM);

		String dateStr = dateFormat.format(new Date(rec.getMillis()));
		String className = rec.getSourceClassName();
		String methodName = rec.getSourceMethodName();
		r.append("<div class='log_entry'>");
		r.append("<span class='log_class'>" + className + "</span> ");
		r.append("<span class='log_method'>(" + methodName + ")</span>:<br />");
		r.append("<span class='log_date'>" + dateStr + "</span><br />");
		r.append("<span class='log_entry'>" + rec.getMessage() + "</span>");
		r.append("</div>");
		return r.toString();
	}

	@Override
	public String getHead(Handler h) {
		StringBuffer r = new StringBuffer();
		r.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN"
				+ "\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		r.append("<html lang='en' xml:lang='en'"
				+ "xmlns='http://www.w3.org/1999/xhtml'>");
		r.append("<head>");
		r.append("<title>JRummikub 06: Log</title>");
		r.append("<style type='text/css'>");
		r.append("body,html {font-family:Verdana,Helvetica, sans-serif; font-size:10pt;}");
		r.append(".log_entry {width:100%; margin-bottom:10px}");
		r.append(".log_class {color:#4B6A1B; font-size:120%;}");
		r.append(".log_method {color: #979797; font-size:110%;}");
		r.append(".log_date {width: 10em; font-size: 8pt; color:#666666;}");
		r.append("</style>");
		r.append("</head>");
		r.append("<body>");
		return r.toString();
	}

	@Override
	public String getTail(Handler h) {
		return "</body></html>";
	}
}

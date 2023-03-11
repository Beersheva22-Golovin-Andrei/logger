package telran.util;

public class Logger {

	private Level level = Level.INFO;
	
	private Handler handler;
	
	private String name;
	
	public Logger(Handler handler, String name) {
		this.handler = handler;
		this.name = name;
	}

	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void error (String message) {
		LoggerRecord rec = getRecord(Level.ERROR, message);
		if (rec!=null) {
			handler.publish(rec);
		}
	}

	public void warn (String message) {
		LoggerRecord rec = getRecord(Level.WARN, message);
		if (rec!=null) {
			handler.publish(rec);
		}
	}
	
	public void info (String message) {
		LoggerRecord rec = getRecord(Level.INFO, message);
		if (rec!=null) {
			handler.publish(rec);
		}
	}
	
	public void debug (String message) {
		LoggerRecord rec = getRecord(Level.ERROR, message);
		if (rec!=null) {
			handler.publish(rec);
		}
	}
	
	public void trace (String message) {
		LoggerRecord rec = getRecord(Level.TRACE, message);
		if (rec!=null) {
			handler.publish(rec);
		}
	}
	
	private boolean checkLevel (Level settedLevel) {
		return level==settedLevel;
	}
	
	private LoggerRecord getRecord (Level level, String message) {
		return checkLevel(level) ? new LoggerRecord (name, level, message) : null;
		
	}
}

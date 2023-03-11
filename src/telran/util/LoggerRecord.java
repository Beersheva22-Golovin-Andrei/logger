package telran.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Formatter;

public class LoggerRecord {

	private final Instant timestamp;
	
	private final String zoneId;
	
	private final Level level;
	
	private final String loggerName;
	
	private final String message;
	
	public LoggerRecord(String loggerName, Level level, String message) {
		timestamp = Instant.now();
		zoneId = ZoneId.systemDefault().toString();
		this.level = level;
		this.loggerName = loggerName;
		this.message = message;
	}

	@Override
	public String toString() {
		LocalDateTime date = getDateAndTime(timestamp, zoneId);
		return date.toLocalDate().toString() + " " + date.toLocalTime().toString() + " " +loggerName + " " + level.name()+": " + message;
	}
	
	LocalDateTime getDateAndTime (Instant inst, String zone) {
		return LocalDateTime.ofInstant(inst, ZoneId.of(zone));
			
	}
}

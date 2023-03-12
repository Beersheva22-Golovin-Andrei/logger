package telran.util;

public enum Level {

	TRACE(5), DEBUG(4), INFO(3), WARN(2), ERROR(1);
	
	private Level(int priority) {
		this.priority = priority;
	}

	private int priority;

	public int getPriority() {
		return priority;
	}
	
}

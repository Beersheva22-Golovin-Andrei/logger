package telran.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class FileStreamHandler implements Handler {

	private Writer writer;
	
	public FileStreamHandler(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		try {
			BufferedWriter wr = new BufferedWriter (writer);
			wr.write(loggerRecord.toString());
			wr.append('\n');
			wr.flush();
		} catch (IOException e) {
			System.out.println("Log cannot be written");
		}
	}
}

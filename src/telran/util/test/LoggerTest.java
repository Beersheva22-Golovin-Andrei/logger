package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import telran.util.FileStreamHandler;
import telran.util.Level;
import telran.util.Logger;
import telran.util.SimpleStreamHandler;

public class LoggerTest {

	@Test
	void myLoggerConsoleTest() {
		Logger logger = new Logger(new SimpleStreamHandler(System.out), "myLogger");
		logger.error("level - error. OK");
		logger.warn("level - warn. OK");
		logger.info("level - info. OK");
		logger.trace("level - trace. not OK");
	
	}
	
	@Test
	void myLoggerFileTest() throws IOException {
		try(FileWriter fw = new FileWriter("text.txt", true);
				BufferedReader br = new BufferedReader( new FileReader("text.txt"))){
			Logger logger = new Logger(new FileStreamHandler(fw), "myLogger");
			logger.setLevel(Level.WARN);
			logger.info("level - info");
			logger.warn("level - warn. OK");
			logger.info("level - info");
			logger.trace("level - trace");
			String s1 = br.readLine();
			assertTrue(!s1.equals("") && s1.endsWith("OK"));
		}
	}
}

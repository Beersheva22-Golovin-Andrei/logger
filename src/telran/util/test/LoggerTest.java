package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import telran.util.FileStreamHandler;
import telran.util.Logger;
import telran.util.SimpleStreamHandler;

public class LoggerTest {

	@Test
	void myLoggerConsoleTest() {
		Logger logger = new Logger(new SimpleStreamHandler(System.out), "myLogger");
		logger.info("level - info1. OK");
		logger.warn("level - warn. not OK");
		logger.info("level - info2. OK");
		logger.trace("level - trace. not OK");
	
	}
	
	@Test
	void myLoggerFileTest() throws IOException {
		try(FileWriter fw = new FileWriter("text.txt", true);
				BufferedReader br = new BufferedReader( new FileReader("text.txt"))){
			Logger logger = new Logger(new FileStreamHandler(fw), "myLogger");
			logger.info("level - info. OK");
			logger.warn("level - warn");
			logger.info("level - info. OK");
			logger.trace("level - info");
			String s1 = br.readLine();
			String s2 = br.readLine();
			assertTrue(!s1.equals("") && s1.endsWith("OK"));
			assertTrue(!s2.equals("")&&s2.endsWith("OK"));
		}
	}
}

package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.util.Level;
import telran.util.Logger;
import telran.util.tcplogger.TcpClientHandler;

public class TcpClientHandlerTest {

	static TcpClientHandler handler;
	static Logger logger;
	
	@BeforeAll
	static void setUp () throws Exception {
		handler = new TcpClientHandler("localhost", 0);
		logger = new Logger(handler, "tcp_logger");
		logger.setLevel(Level.TRACE);
	}
	
	@Test
	void sendLogsAndGetCountsTest() {
		logger.info("inf1");
		logger.info("inf2"); 
		logger.error("err1"); 
		logger.error("err2");
		logger.error("err3"); 
		logger.trace("trace1"); 
		logger.warn("warn1");

		assertEquals("3", handler.getCountOfLogs(Level.ERROR));
		assertEquals("2", handler.getCountOfLogs(Level.INFO));
		assertEquals("1", handler.getCountOfLogs(Level.TRACE));
		 
	}
	
	@AfterAll
	static void forFinish() {
		handler.close();
	}
}

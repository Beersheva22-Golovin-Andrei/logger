package telran.util.tcplogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import telran.util.Handler;
import telran.util.Level;
import telran.util.LoggerRecord;

public class TcpClientHandler implements Handler {

	private Socket socket;
	private PrintStream output;
	private BufferedReader input;
	
	private static final int DEFAULT_PORT = 5050;
	
	public TcpClientHandler(String hostName, int port) {
		int clientPort = port==0 ? DEFAULT_PORT : port;
		try {
			socket = new Socket(hostName, clientPort);
			output = new PrintStream(socket.getOutputStream());
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		try {
			output.println("log#" + loggerRecord.toString());
			String response = input.readLine();
			System.out.println(response);
			if(!response.equals("OK")) {
				  System.out.println("Record hasn't been accepted!"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	@Override
	public void close() {
		try {
			socket.close();
			output.close();
			input.close();
		} catch (IOException e) {
			System.out.println("Error due closing of connections!");
			e.printStackTrace();
		}
	}
	
	public String getCountOfLogs (Level level) {
		String res = null;
		try {
			output.println("counter#"+level.name());
				res = input.readLine();
			} catch (IOException e) {
				System.out.println("Count of errors cannot be received!");
			}
		return res;
	}
}

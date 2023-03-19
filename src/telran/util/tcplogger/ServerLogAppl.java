package telran.util.tcplogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import telran.util.Level;


public class ServerLogAppl {

	private static Map<String, Set<String>> records = new HashMap<>();
	private static final int PORT = 5050;
	
	public static void main(String[] args) throws Exception {
		initStorage();
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true) {
			System.out.println(records);
			Socket socket = serverSocket.accept();
			try {
				requestProccesing(socket);
			} catch (IOException e) {
				System.out.println("There is some problem");
			}
		}
	}
	
	
	private static void initStorage() {
		records.put(Level.ERROR.name(), new HashSet<>());
		records.put(Level.INFO.name(), new HashSet<>());
		records.put(Level.DEBUG.name(), new HashSet<>());
		records.put(Level.TRACE.name(), new HashSet<>());
		records.put(Level.WARN.name(), new HashSet<>());
	}


	private static void requestProccesing (Socket socket) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream w = new PrintStream(socket.getOutputStream());
		while (true) {
			String request = r.readLine();
			if (request == null) break;
			String response = getResponse(request);
			w.println(response);
		}	
	}
	
	
	private static String getResponse (String request) {
		String res;
		String tokens[] = request.split("#");
		if (tokens.length == 2) {
			res = switch(tokens[0]) {
			case "log" -> addRecord(tokens[1]);
			case "counter" -> getCountOfRecords(tokens[1]);
			default -> "unsupported command";
			};
		} else {
			res = "Incorrect request";
		}
		return res;
	}
	
	
	private static String addRecord (String loggerRecord) {
		String[] recordArr = loggerRecord.split(" ");
		String keyForRecords = recordArr[3].substring(0, recordArr[3].length()-1);
		return records.get(keyForRecords).add(loggerRecord) ? "OK":"Not accepted" ;
	}
	
	private static String getCountOfRecords (String type) {
		return ""+records.get(type.toUpperCase()).size();	
	}
}

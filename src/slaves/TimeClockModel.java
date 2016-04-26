package slaves;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeClockModel {
	private int id;
	private ZonedDateTime time;

	private static boolean waitClient;
	private static String buffer = "";
	private static ServerSocket srvSocket = null;
	private static OutputStream outStream = null;
	private static ArrayList<TimeClockModel> listOfCheckInOut = 
			new ArrayList<TimeClockModel>();
	
	
	public static void add(int id) {
		listOfCheckInOut.add(new TimeClockModel(id));
	}
	
	private static void waitForClient(int port) {
		if(srvSocket == null) {
			try {
				srvSocket = new ServerSocket(port, 1);}
			catch (IOException e) {
				System.err.println("The port " + port + " is already used by another "
						+ "application. Exiting...");
				System.exit(1);
			}
		}
	
		if(outStream == null)
			waitClient = true;
		
		if(waitClient) {
			System.out.println("Waiting for a client");
			
			try {
				//Block until a connection
				Socket socket = srvSocket.accept();
				outStream = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}

			waitClient = false;
			System.out.println("Client here");
		}

	}
	
	public static void sendAll(int port) {
		waitForClient(port);
		
		//The purpose of the iterator is to allow deleting while iterating
		Iterator<TimeClockModel> it = listOfCheckInOut.iterator();
		while(it.hasNext()) {
			buffer += it.next().stringFormat() + '\n';
			it.remove();
		}
		
		if(!buffer.isEmpty()) {
			try {
				outStream.write(buffer.getBytes());
				buffer = ""; //Clear the buffer
			} catch (IOException e) {
				System.out.println("Client disconnected");
				waitClient = true;
			}
		}
	}
	
	
	private TimeClockModel(int id) {		
		this.id = id;
		this.time = Utils.roundTimeMinQuarter(ZonedDateTime.now(ZoneOffset.UTC));
	}
	
	private String stringFormat() {
		String format = String.valueOf(id) + ' ' +
				time.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
		return format;
	}
}
package centralapp.controlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import centralapp.model.Boss;
import centralapp.model.CheckInOut;
import centralapp.model.Company;
import centralapp.model.Employee;

//One instance per client
public class ComControler implements Runnable {
	final private int COUNT_OF_MAX_RETRIES = 3;
	final private int MILLISEC_BEFORE_RETRY = 10000;
	final private int SIZE_BYTES_BUFFER = 32; //"2147483647 2011-12-03T10:15:30Z\n"
	
	private String ip;
	private int port;
	private Socket clientSocket;
	private InputStream inStream;
	private String buffer;
	private int remainingRetries;
	private Company company;
	
	public ComControler(Company company, String ip, int port) throws IOException {
		this.company = company;
		this.remainingRetries = COUNT_OF_MAX_RETRIES;
		this.ip = ip;
		this.port = port;
		
		setUpConnection();
	}

	@Override
	public void run() {		
		while(true) {
			try {
				read();
				
			} catch (IOException cannotReadException) {
				if(remainingRetries == 0)
					return;
				
				readingIssue();
			}
		} //The main loop
	}
	
	
	private void setUpConnection() throws IOException {
		try {
			clientSocket = new Socket(ip, port);
			inStream = clientSocket.getInputStream();
			buffer = "";
			
		} catch (IOException e) {
			System.err.println("Cannot connect to " + ip + ":" + port);
			throw new IOException();
		}
	}
	
	private void readingIssue() {
		boolean isConnectionSetUp = false;
		
		while(isConnectionSetUp == false) {
			//Just sleep
			try {
			    Thread.sleep(MILLISEC_BEFORE_RETRY);
			} catch(InterruptedException sleepException) {
			    Thread.currentThread().interrupt();
			}
			
			//Set up again
			try {
				setUpConnection();
				isConnectionSetUp = true;
			} catch (IOException socketException) {
				remainingRetries--;
				System.err.println("Retrying (" + remainingRetries +
						" more times.");
			}
		}
	}
	
	private void read() throws IOException {
		byte[] bytesBuffer = new byte[SIZE_BYTES_BUFFER];
		
		inStream.read(bytesBuffer, 0, SIZE_BYTES_BUFFER);
		buffer = buffer + new String(bytesBuffer); //Issue here (bytesBuffer type)
		
		int indexOfLineFeed = buffer.indexOf("\n");
		
		if(indexOfLineFeed != -1) { //If line feed found
			String[] idDateHourAndEverythingElse = buffer.split("\\s+", 3);
			
			//Remove the processed part of the buffer
			buffer = idDateHourAndEverythingElse[2];
			
			int id = Integer.parseInt(idDateHourAndEverythingElse[0]);
			ZonedDateTime dateTime = ZonedDateTime.parse(idDateHourAndEverythingElse[1]);
			
			System.out.println(id + " has check in/out at " + dateTime);
			
			Employee employee = company.findEmployee(id);
			if(employee == null)
				employee = company.getMrX();
				
			employee.addCheckInOut(new CheckInOut(dateTime));
		}
	}
}

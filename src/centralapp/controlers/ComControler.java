package centralapp.controlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import centralapp.model.Boss;
import centralapp.model.CheckInOut;
import centralapp.model.Company;
import centralapp.model.Employee;

//One instance per client
public class ComControler implements Runnable {
	final private int MILLISEC_BEFORE_RETRY = 10000;
	final private int SIZE_BYTES_BUFFER = 32; //"2147483647 2011-12-03T10:15:30Z\n"
	
	private String ip;
	private int port;
	private Socket clientSocket;
	private InputStream inStream;
	private OutputStream outStream;
	private StringBuffer inBuffer, outBuffer;
	private Company company;
	
	public ComControler(Company company, String ip, int port) {
		this.company = company;
		this.ip = ip;
		this.port = port;
		this.inBuffer = new StringBuffer();
		this.outBuffer = new StringBuffer();
	}

	@Override
	public void run() {
		while(true) {
			try {
				setUpConnection();
				read();
				write();
				
			} catch(IOException cannotReadException) {
				//Just wait
				try {
				    Thread.sleep(MILLISEC_BEFORE_RETRY);
				} catch(InterruptedException sleepException) {
				    Thread.currentThread().interrupt();
				}
				
				System.err.println("Cannot connect to " + ip + ':' + port +
						" (next retry in " + MILLISEC_BEFORE_RETRY / 1000.0 + " seconds)");
			}
		}
	}
	
	public void updatePeopleList(ArrayList<Employee> peopleList) {	
		outBuffer.setLength(0);
		for(Employee emp : peopleList) {
			outBuffer.append(Integer.toString(emp.getId()) + ' ' + emp + '\n');
		}
	}
	
	
	private void setUpConnection() throws IOException {
		clientSocket = new Socket(ip, port);
		inStream = clientSocket.getInputStream();
		outStream = clientSocket.getOutputStream();
		inBuffer.setLength(0);
		outBuffer.setLength(0);
	}
	
	private void read() throws IOException {
		if(inStream.available() == 0)
			return;
		
		byte[] bytesBuffer = new byte[SIZE_BYTES_BUFFER];
		
		inStream.read(bytesBuffer, 0, SIZE_BYTES_BUFFER);
		inBuffer.append(new String(bytesBuffer));
		
		int indexOfLineFeed = inBuffer.indexOf("\n");
		
		if(indexOfLineFeed != -1) { //If line feed found
			String[] idDateHourAndEverythingElse = inBuffer.toString().split("\\s+", 3);
			
			//Remove the processed part of the buffer
			inBuffer.setLength(0);
			inBuffer.append(idDateHourAndEverythingElse[2]);
			
			int id = Integer.parseInt(idDateHourAndEverythingElse[0]);
			ZonedDateTime dateTime = ZonedDateTime.parse(idDateHourAndEverythingElse[1]);
			
			System.out.println(id + " has check in/out at " + dateTime);
			
			Employee employee = company.findEmployee(id);
			if(employee == null)
				employee = company.getMrX();
				
			employee.addCheckInOut(new CheckInOut(dateTime));
		}
	}
	
	private void write() throws IOException {
		if(outBuffer.length() > 0) {
			outStream.write(outBuffer.toString().getBytes());
			outBuffer.setLength(0); //Clear the buffer
		}
	}
}

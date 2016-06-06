package slaves;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlaveControler {
	private View view;
	private int port;
	private Pattern pattern;
	
	public SlaveControler(int port) {		
		view = new View();
		this.port = port;
		pattern = Pattern.compile("(\\d+) ([^\\n]+)");
	}
	
	public void run() {
		view.setVisible(true);
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			TimeClockModel.sendAll(port);
			receivePeople();
		}
	}
	
	public void receivePeople() {
		ArrayList<EmployeeModel> list = new ArrayList<EmployeeModel>();
		String people = TimeClockModel.receive(port);
		
		boolean doContinue = true;
		while(doContinue) {
			Matcher matcher = pattern.matcher(people); 
			if(matcher.find()) {
				int id = Integer.parseInt(matcher.group(0));
				String name = matcher.group(1);
				
				EmployeeModel newEmp = new EmployeeModel(id, name);
				list.add(newEmp);
			}
			else
				doContinue = false;
		}
	
		view.updatePeopleList(list);
	}
	
	public static void main(String[] args) {
		int port = 1337;
		
		if(args.length == 1)
			port = Integer.parseInt(args[0]);
		else if(args.length >= 2) { //Et si parseInt fail : TODO
			System.err.println("Invalid arguments. Please, put the port number or nothing. Exiting...");
			System.exit(0);
		}
		
		SlaveControler controler = new SlaveControler(port);
		System.out.println("Launching...");
		controler.run();
	}

}

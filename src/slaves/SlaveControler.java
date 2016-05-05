package slaves;

public class SlaveControler {
	private View view;
	private int port;
	
	public SlaveControler(int port) {		
		view = new View();
		this.port = port;
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
		}
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

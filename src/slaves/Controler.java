package slaves;

public class Controler {
	private View view;
	
	public Controler() {		
		view = new View();
	}
	
	public void run() {
		view.setVisible(true);
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			TimeClockModel.sendAll();
		}
	}
	
	public static void main(String[] args) {
		Controler controler = new Controler();
		System.out.println("Launching...");
		controler.run();
	}

}

package centralapp.controlers;

import centralapp.views.MainWindow;

public class CentralApp {
	MainWindow mainWindow;
	
	public CentralApp() {
		mainWindow = new MainWindow();
	}
	
	public void run() {
		mainWindow.setVisible(true);
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}

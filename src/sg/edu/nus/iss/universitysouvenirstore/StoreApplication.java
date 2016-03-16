package sg.edu.nus.iss.universitysouvenirstore;
import sg.edu.nus.iss.universitysouvenirstore.gui.*;
public class StoreApplication {

	private StoreWindow StoreWindow;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("University Souvenir Store Application!");

	}
	
	public void shutdown() {
		System.exit(0);
	}
	
	public StoreWindow getMainWindow() {
		return StoreWindow;
	}

	public StoreApplication () { 
		StoreWindow = new StoreWindow (this);
		StoreWindow.pack ();
	}

}

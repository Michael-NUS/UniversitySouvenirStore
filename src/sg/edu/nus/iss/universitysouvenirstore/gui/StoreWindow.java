package sg.edu.nus.iss.universitysouvenirstore.gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.universitysouvenirstore.StoreApplication;

public class StoreWindow extends JFrame {

	private StoreApplication manager;
    private ProductManagerDialog    productDialog;

    private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
    };



	/**
	 * Create the frame.
	 */
	public StoreWindow(StoreApplication manager) {
		super ("Club Management System");
		this.manager = manager;
		productDialog = new ProductManagerDialog(manager);
		addWindowListener(windowListener);
	}

}

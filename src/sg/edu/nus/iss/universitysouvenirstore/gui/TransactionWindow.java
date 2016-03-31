package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.universitysouvenirstore.*;


public class TransactionWindow extends JFrame {
	private TransactionDialog     manager;
    private TransactionPanel     transPanel;
    
    private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
    };
    
    public TransactionWindow (TransactionDialog manager) {
        super ("Transaction");
        this.manager = manager;
        transPanel = new TransactionPanel (manager);

        Panel p = new Panel ();
        p.setLayout (new GridLayout(0, 1));
        p.add (transPanel);

        add ("Center", p);
        addWindowListener(windowListener);
    }
    
    public void refresh () {
    	transPanel.refresh();
    }

}

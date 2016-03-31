package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TransactionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TransactionDialog        manager;
    private List<TransactionedItem> members;
    private java.awt.List          memberList;
    
	//private final JPanel contentPanel = new JPanel();
	//private JTextField textField;

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JLabel msglabel;

    public TransactionPanel (TransactionDialog manager) {
        
        /*
        setTitle("Transaction");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		*/
        
        //prepareGUI();
        //showJPanelDemo();
        
        setLayout (new BorderLayout(5,5));
        memberList = new java.awt.List (5);
        memberList.setMultipleMode (false);
        
        add ("North", new JLabel ("Items"));        
        add ("Center", memberList);
        add ("East", createButtonPanel());
        
        
    }

    public void refresh () {
        //members = manager.GetTransactionedItems();
        memberList.removeAll();
        Iterator<TransactionedItem> i = members.iterator();
        while (i.hasNext()) {
        	memberList.add (i.next().toString());
        }
    }

    /*
    public Member getSelectedMember () {
        int idx = memberList.getSelectedIndex();
        return (idx == -1) ? null : members.get(idx);
    }
*/
    
    
    private JPanel createButtonPanel () {

        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton b = new JButton ("Add");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                AddTransactionedItemDialog d = new AddTransactionedItemDialog (manager);
                d.pack();
                d.setVisible (true);
            }
        });
        p.add (b);
 
        b = new JButton ("Edit Quantity");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                manager.editSelectedItem();
            }
        });
        
        p.add (b);
        
        b = new JButton ("Remove");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                manager.removeSelectedItem();
            }
        });
        
        p.add (b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        return bp;
    }
    
//panel
    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.getContentPane().setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }        
        });    

        //msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial.", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
                statusLabel = new JLabel("Item",JLabel.CENTER);    
                
                statusLabel.setSize(350,100);
                mainFrame.getContentPane().add(statusLabel);
                
                memberList = new java.awt.List (5);
                memberList.setMultipleMode (false);
                //mainFrame.add(controlPanel);
                mainFrame.getContentPane().add(memberList);
                headerLabel = new JLabel("", JLabel.CENTER);        
                
                mainFrame.getContentPane().add(headerLabel);
                


        mainFrame.setVisible(true);  
     }

     private void showJPanelDemo(){
        //headerLabel.setText("Container in action: JPanel");      

        JPanel panel = new JPanel();
        panel.setBackground(Color.magenta);
        panel.setLayout(new FlowLayout());        
        panel.add(msglabel);

        controlPanel.add(panel);        
        mainFrame.setVisible(true);      
     }   
    
}
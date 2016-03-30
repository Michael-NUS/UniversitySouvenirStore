package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TransactionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TransactionDialog        manager;
    private List<TransactionedItem> members;
    private java.awt.List          memberList;

    public TransactionPanel (TransactionDialog manager) {
        this.manager = manager;
        setLayout (new BorderLayout());
        memberList = new java.awt.List (5);
        memberList.setMultipleMode (false);
        add ("North", new JLabel ("Items"));
        add ("Center", memberList);
        add ("East", createButtonPanel());
    }

    public void refresh () {
        members = manager.GetTransactionedItems();
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
        
        /*
        b = new JButton ("Remove");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                manager.removeSelectedMember();
            }
        });
        */
        p.add (b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        return bp;
    }
    

}
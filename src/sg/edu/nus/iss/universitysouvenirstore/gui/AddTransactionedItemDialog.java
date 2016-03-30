package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.util.*;
import java.awt.*;
import javax.swing.*;

public class AddTransactionedItemDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	
	private TransactionDialog manager;
    private JTextField quantityField;

    private JTextField productIDField;

    public AddTransactionedItemDialog (TransactionDialog manager) {
        super (manager.getMainWindow(), "Add Member"); //??
        this.manager = manager;
    }

    protected JPanel createFormPanel () {
        JPanel p = new JPanel ();
        p.setLayout (new GridLayout (0, 2));
        p.add (new JLabel ("ProductID"));
        productIDField = new JTextField (20);
        p.add (productIDField);
        p.add (new JLabel ("Quantity"));
        quantityField = new JTextField (20);
        p.add (quantityField);
        return p;
    }

    protected boolean performOkAction () {
        String productID = productIDField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        if ((productID.length() == 0) || (quantityField.getText() == null)) {
            return false;
        }

        manager.AddTransactionedItem(productID, quantity);
        return true;
    }

}
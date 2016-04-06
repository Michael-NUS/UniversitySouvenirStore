package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddTransactionedItemDialog extends JDialog {



	private static final long serialVersionUID = 1L;
	
	private JTextField quantityField;

    private JTextField productIDField;



    public AddTransactionedItemDialog (TransactionDialog manager) {
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
        Integer.parseInt(quantityField.getText());
        if ((productID.length() == 0) || (quantityField.getText() == null)) {
            return false;
        }

        //manager.AddTransactionedItem(productID, quantity);
        return true;
    }

}
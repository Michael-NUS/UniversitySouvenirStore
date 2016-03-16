package sg.edu.nus.iss.universitysouvenirstore.gui;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitysouvenirstore.*;
import sg.edu.nus.iss.universitysouvenirstore.util.*;

public class ProductManagerDialog extends OkCancelDialog {

	
	private StoreApplication manager;
	/**
	 * Create the dialog.
	 */
	public ProductManagerDialog(StoreApplication manager) {
		   super (manager.getMainWindow(), "Add Product");
		   this.manager = manager;
	}

	@Override

    protected JPanel createFormPanel () {
        JPanel p = new JPanel ();
       
        return p;
    }


	@Override
	protected boolean performOkAction() {
		// TODO Auto-generated method stub
		return false;
	}

}

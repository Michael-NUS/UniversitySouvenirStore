package sg.edu.nus.iss.universitysouvenirstore.util;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ConfirmDialog extends OkCancelDialog {

    private JLabel       messageLabel;

    public ConfirmDialog (JFrame parent, String title, String message) {
        super (parent, title);
        messageLabel.setText (message);
    }

    protected JPanel createFormPanel () {
        JPanel p = new JPanel ();
        messageLabel = new JLabel ();
        p.add (messageLabel);
        return p;
    }

}

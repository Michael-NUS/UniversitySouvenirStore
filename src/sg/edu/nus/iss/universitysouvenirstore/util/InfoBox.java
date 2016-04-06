/**
 * An information box to pop up, it hogs the screen until it is accepted.
 */
package sg.edu.nus.iss.universitysouvenirstore.util;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * @author aungmyo
 *
 */
public class InfoBox {
	public static void showInfoBox(Component parentComponent, String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(parentComponent, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

/**
 * File name : SelectionAlert.java
 * 
 * Description : JDialog GUI class for handling Alert when doing storekeeper removal.
 * 
 * @author : NayLA 
 * 
 * Date :25/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SelectionAlert extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public SelectionAlert() {
		setBounds(100, 100, 302, 107);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Please select an action to be performed!");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblNewLabel.setForeground(new Color(0, 0, 255));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
									
						dispose();
					}
				});
				
				okButton.addKeyListener(new MyKeyListener(){
					
					/* Override keyPressed() method of MyKeyListener class .*/
					public void keyPressed(KeyEvent evt)
					{
						if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
							
							dispose();							
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
									
						dispose();
					}
				});
				
				cancelButton.addKeyListener(new MyKeyListener(){
					
					/* Override keyPressed() method of MyKeyListener class .*/
					public void keyPressed(KeyEvent evt)
					{
						if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
							
							dispose();							
						}
					}
				});
			}
		}
	}

}

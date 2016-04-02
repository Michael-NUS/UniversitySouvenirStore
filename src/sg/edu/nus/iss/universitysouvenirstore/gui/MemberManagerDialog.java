package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	AddMemberDialog AddMemberDialog = new AddMemberDialog();

	/**
	 * Create the dialog.
	 */
	public MemberManagerDialog() {
		setTitle("Member manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddMemberDialog.setTitle("New Item");
						AddMemberDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						AddMemberDialog.setEnabled(true);
						AddMemberDialog.setVisible(true);
						AddMemberDialog.setCurProduct(null);

						//Edit
						AddMemberDialog.setEditCase(false);
						AddMemberDialog.setDeleteCase(false);
						AddMemberDialog.Refresh();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

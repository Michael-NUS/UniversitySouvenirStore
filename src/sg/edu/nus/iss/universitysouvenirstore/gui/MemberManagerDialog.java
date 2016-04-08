/**
 * @author LIU YAKUN
 */

package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MemberManagerDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	AddMemberDialog AddMemberDialog = new AddMemberDialog();
	// RemoveMemberDialog RemoveMemberDialog = new RemoveMemberDialog();

	/**
	 * Create the dialog.
	 */
	public MemberManagerDialog() {
		setTitle("Member Manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(null);

		JButton btnAddNewMember = new JButton("Add Member");
		btnAddNewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddMemberDialog.setTitle("Add Member");
				AddMemberDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				AddMemberDialog.setEnabled(true);
				AddMemberDialog.setVisible(true);
				// newMemberDialog.setCurProduct(null);

				// Edit
				// AddMemberDialog.setEditCase(false);
				// AddMemberDialog.setDeleteCase(false);
				// AddMemberDialog.Refresh();
				// NewMemberDialog.dataInit();
			}
		});

		btnAddNewMember.setBounds(26, 65, 172, 45);
		contentPanel.add(btnAddNewMember);

		// JButton btnRemoveMember = new JButton("Remove Member");
		// btnRemoveMember.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		//
		// AddMemberDialog.setTitle("Remove Member");
		// AddMemberDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// AddMemberDialog.setEnabled(true);
		// AddMemberDialog.setVisible(true);
		// // newMemberDialog.setCurProduct(null);
		//
		// // Edit
		// // AddMemberDialog.setEditCase(false);
		// // AddMemberDialog.setDeleteCase(false);
		// // AddMemberDialog.Refresh();
		// // NewMemberDialog.dataInit();
		//
		// }
		// });
		//
		// btnRemoveMember.setBounds(236, 65, 172, 45);
		// contentPanel.add(btnRemoveMember);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			//
			// {
			// JButton okButton = new JButton("OK");
			// okButton.addActionListener(new ActionListener() {
			// public void actionPerformed(ActionEvent e) {
			//
			// setVisible(false);
			// dispose();
			// }
			// });
			// okButton.setActionCommand("OK");
			// buttonPane.add(okButton);
			// getRootPane().setDefaultButton(okButton);
			// }
			//
			// {
			// JButton cancelButton = new JButton("Cancel");
			// cancelButton.addActionListener(new ActionListener() {
			// public void actionPerformed(ActionEvent e) {
			//
			// setVisible(false);
			// dispose();
			// }
			// });
			// cancelButton.setActionCommand("Cancel");
			// buttonPane.add(cancelButton);
			// }
		}
	}

}

// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.FileManagerUtils;
import sg.edu.nus.iss.universitysouvenirstore.Member;
import sg.edu.nus.iss.universitysouvenirstore.MemberManager;

public class AddMemberDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMemberName;
	private JTextField txtMemberID;

	private ArrayList<Member> members = null;

	JPanel buttonPane = new JPanel();
	JButton cancelButton = new JButton("Cancel");

	/**
	 * Create the dialog
	 */
	public AddMemberDialog() {

		setTitle("Member Registration");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblInstruction1 = new JLabel("Please fill in the fields below to register the new member.");
		lblInstruction1.setBounds(10, 15, 400, 27);
		contentPanel.add(lblInstruction1);

		JLabel lblInstruction2 = new JLabel("Please use the student/staff card number as the membership number.");
		lblInstruction2.setBounds(10, 50, 400, 27);
		contentPanel.add(lblInstruction2);

		JLabel lblMemberName = new JLabel("Member Name:");
		lblMemberName.setBounds(10, 100, 100, 27);
		contentPanel.add(lblMemberName);

		JLabel lblMemberID = new JLabel("Member ID:");
		lblMemberID.setBounds(10, 150, 100, 27);
		contentPanel.add(lblMemberID);

		txtMemberName = new JTextField();
		txtMemberName.setBounds(110, 100, 150, 25);
		contentPanel.add(txtMemberName);
		txtMemberName.setColumns(30);

		txtMemberID = new JTextField();
		txtMemberID.setBounds(110, 150, 150, 25);
		contentPanel.add(txtMemberID);
		txtMemberID.setColumns(30);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberName = txtMemberName.getText().trim();
				String memberID = txtMemberID.getText().trim();
				if (!(Pattern.matches("^[A-Za-z0-9]+$", txtMemberID.getText()))) {
					JOptionPane.showMessageDialog(null, "Please enter ONLY letters and numbers for member ID", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!(Pattern.matches("^[A-Za-z0-9 ]+$", txtMemberName.getText()))) {
					JOptionPane.showMessageDialog(null, "Please enter ONLY letters and whitespaces for member Name",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (txtMemberName.getText() != null && txtMemberID.getText() != null) {
					MemberManager.readExistingMembersFromDB();
					if (!MemberManager.checkExistOfMember(memberID)) {
						// Successfully register the member
						MemberManager.addMember(memberName, memberID);
						members = MemberManager.convertToMemberArraylist();
						FileManagerUtils.saveDataToDatFile(Member.class, members);
						String successMsg = "Successfully Registered the New Member!\n";
						JOptionPane.showMessageDialog(null, successMsg, "Registrion Completed",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						// Member ID already exists!
						String errorMsg = "Member ID already exists! Cannot register the member!\n";
						JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.INFORMATION_MESSAGE);

					}
				} else {
					setVisible(false);
					dispose();
				}

				txtMemberName.setText("");
				txtMemberID.setText("");
			}
		});

		// Save Button
		okButton.setActionCommand("Saved");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		// Cancel Button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

}

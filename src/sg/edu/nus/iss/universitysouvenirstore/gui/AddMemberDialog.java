package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.MemberManager;
import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;
import sg.edu.nus.iss.universitysouvenirstore.Member;

public class AddMemberDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMemberName;
	private JTextField txtMemberID;
	private String title;

	// private boolean isEditCase = false;
	// private boolean isDeleteCase = false;

	private JButton okButton = null;
	private Member curMember = null;
	private ArrayList<Member> members = null;
	private MemberManagerDialog memberManagerDialog;

	JPanel buttonPane = new JPanel();
	JButton cancelButton = new JButton("Cancel");

	// public boolean isEditCase() {
	// return isEditCase;
	// }
	//
	// public void setEditCase(boolean isEditCase) {
	// this.isEditCase = isEditCase;
	// }
	//
	// public void setCurMember(Member curMember) {
	// this.curMember = curMember;
	// }
	//
	// public String getTitle() {
	// return title;
	// }
	//
	// public void setTitle(String title) {
	// this.title = title;
	// }

	/**
	 * Create the dialog.
	 * 
	 * @param transactionDialog
	 */
	public AddMemberDialog() {
		// lblNewLabel.setBounds(74, 152, 56, 16); //label debug
		// contentPanel.add(lblNewLabel);//label debug

		// lblNewLabel_1.setBounds(74, 170, 56, 16);//label debug
		// contentPanel.add(lblNewLabel_1);//label debug

		this.memberManagerDialog = memberManagerDialog;
		// contentPanel.setBackground(new Color(244, 164, 96));
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
				String memberName = null;
				String memberID = null;
				if (txtMemberName.getText() != null && txtMemberID.getText() != null) {
					memberName = txtMemberName.getText();
					memberID = txtMemberID.getText();
					MemberManager.readExistingMembersFromDB();
					if (!MemberManager.checkExistOfMember(memberID)) {
						MemberManager.addMember(memberName, memberID);
						members = MemberManager.convertToMemberArraylist();
						curMember = MemberManager.getMember(memberID);
						FileManangerUtils.saveDataToDatFile(Member.class, members);
					} else {
						// Member ID already exists! Cannot register the new
						// member.
						;
					}

				} else {
					;
					// throw InvalidInput("Missing Product ID and/or Quantity")
					// lblNewLabel.setText(txtMemberName.getText()); //debug
					// lblNewLabel_1.setText(txtMemberID.getText()); //debug
					setVisible(false);
					dispose();
				}

				txtMemberName.setText("");
				txtMemberID.setText("");

			}
		});

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

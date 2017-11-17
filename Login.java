import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.*;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connDB=null;
	private JTextField UserNameField;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connDB = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 998, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBackground(new Color(0, 153, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(263, 160, 100, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(new Color(0, 153, 255));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(263, 233, 113, 26);
		frame.getContentPane().add(lblPassword);
		
		UserNameField = new JTextField();
		UserNameField.setBounds(419, 150, 200, 50);
		frame.getContentPane().add(UserNameField);
		UserNameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
						String query = "select * from EmployeeData where Username = ? and Password = ? ";
						PreparedStatement pst = connDB.prepareStatement(query);
						pst.setString(1, UserNameField.getText());
						pst.setString(2, passwordField.getText());
						
						ResultSet rs = pst.executeQuery();
						int count = 1;
						
						while(rs.next()){
							count++;
						}
						
						if(count == 2){
							JOptionPane.showMessageDialog(null, "Correct Username and Password!!!");
						}
						else if(count >= 2){
							JOptionPane.showMessageDialog(null, "Duplicate Username and Password!!!");
						}
						else{
							JOptionPane.showMessageDialog(null, "Username OR Password is incorrect!!!");
						}
						
						rs.close();
						pst.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(313, 340, 200, 50);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(419, 227, 200, 50);
		frame.getContentPane().add(passwordField);
	}
}

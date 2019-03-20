package com.sdlc.ExcelDemo;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Panel;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeysRegisterLogin {

	private JFrame frmKeyregisterLogin;
	private JTextField username_field;
	private JPasswordField password_field;
	
	Connection con=null;
	PreparedStatement ptsm=null;
	ResultSet rs=null;
	String username;
	String password;
	String sqlquery;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeysRegisterLogin window = new KeysRegisterLogin();
					window.frmKeyregisterLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KeysRegisterLogin() {
		initialize();
		/**
		 * Connecting to MySQL database 
		 *
		 */	
		con=DatabaseconnectionMysql.getconnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKeyregisterLogin = new JFrame();
		frmKeyregisterLogin.setTitle("KeyRegister Login");
		frmKeyregisterLogin.getContentPane().setBackground(new Color(255, 255, 255));
		frmKeyregisterLogin.setBounds(100, 100, 614, 414);
		frmKeyregisterLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKeyregisterLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUsername.setBounds(275, 136, 91, 30);
		frmKeyregisterLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(275, 197, 91, 30);
		frmKeyregisterLogin.getContentPane().add(lblPassword);
		
		username_field = new JTextField();
		username_field.setBounds(376, 138, 163, 30);
		frmKeyregisterLogin.getContentPane().add(username_field);
		username_field.setColumns(10);
		
		password_field = new JPasswordField();
		password_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent enter) {
				if(enter.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						//Getting the username and password from the jframe
						username=username_field.getText();
						password=password_field.getText();
						sqlquery="SELECT * FROM logininfo where username=? and PASSWORD=? ";
						
							ptsm= con.prepareStatement(sqlquery);
							ptsm.setString(1,username);
							ptsm.setString(2,password);
							rs=ptsm.executeQuery();
							if(rs.next())
							{
								JOptionPane.showMessageDialog(null, "Login sucessfull");
								frmKeyregisterLogin.dispose();
								Keysregister keyreg=new Keysregister();
								keyreg.setVisible(true);
								keyreg.setLocationRelativeTo(null);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Incorrect username or password");
							}
							rs.close();
							ptsm.close();
							con.close();
							
						}
						
						catch (SQLException se) 
						{
							se.printStackTrace();
						}
					
					
				}
				
			}
		});
		password_field.setBounds(376, 199, 163, 30);
		frmKeyregisterLogin.getContentPane().add(password_field);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//Getting the username and password from the jframe
				username=username_field.getText();
				password=password_field.getText();
				sqlquery="SELECT * FROM logininfo where username=? and PASSWORD=? ";
				
					ptsm= con.prepareStatement(sqlquery);
					ptsm.setString(1,username);
					ptsm.setString(2,password);
					rs=ptsm.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login sucessfull");
						frmKeyregisterLogin.dispose();
						Keysregister keyreg=new Keysregister();
						keyreg.setVisible(true);
						keyreg.setLocationRelativeTo(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
					} 
					
				}
				
				catch (SQLException se) 
				{
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(337, 278, 123, 38);
		frmKeyregisterLogin.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(228, 102, 346, 242);
		frmKeyregisterLogin.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VenkataVikram\\Downloads\\icons8-house-keys-96.png"));
		lblNewLabel.setBounds(49, 136, 123, 142);
		frmKeyregisterLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\VenkataVikram\\Downloads\\download.png"));
		lblNewLabel_1.setBounds(10, 23, 352, 68);
		frmKeyregisterLogin.getContentPane().add(lblNewLabel_1);
	}
}


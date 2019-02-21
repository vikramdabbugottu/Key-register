package com.sdlc.ExcelDemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;

public class Loginform extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JPasswordField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginform frame = new Loginform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loginform() {
		setFont(new Font("Tahoma", Font.BOLD, 14));
		setSize(new Dimension(5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\VenkataVikram\\Pictures\\IMG_20171201_114644.jpg"));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(90, 83, 99, 28);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(90, 129, 99, 28);
		contentPane.add(lblPassword);
		
		username_field = new JTextField();
		username_field.setBounds(199, 83, 129, 28);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		password_field = new JPasswordField();
		password_field.setBounds(199, 129, 129, 28);
		contentPane.add(password_field);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					/**
					 * Connecting to MySQL database and getting the data from the form.
					 */	
				Connection con=null;
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/keysdatabase","root","Vikram#123");
				//JOptionPane.showMessageDialog(null, "Connection sucessfull");
				//Getting the username and password from the jframe
				String username=username_field.getText();
				String password=password_field.getText();
				String sqlquery="SELECT * FROM logininfo where username=? and PASSWORD=? ";
				
					PreparedStatement ptsm= con.prepareStatement(sqlquery);
					ptsm.setString(1,username);
					ptsm.setString(2,password);
					ResultSet rs=ptsm.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login sucessfull");
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
					
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setBounds(192, 189, 106, 35);
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(68, 36, 356, 214);
		contentPane.add(panel);
	}
}


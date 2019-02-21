package com.sdlc.ExcelDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
// Checking the DB connection

public class DatabaseconnectionMysql 
{
	public static Connection getconnection()
	{
		Connection con=null;
		try {
			 con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/keysdatabase","root","Vikram#123");
			 JOptionPane.showMessageDialog(null, "Connection sucessfull");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	

}

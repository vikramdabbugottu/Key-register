package com.sdlc.ExcelDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Mysqldbconnection {

	public static void main(String[] args) 
		{
			try
			{
				//Loading the class is not required for the updated version of mysql
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/keysdatabase","root","Vikram#123");  
				Statement st=con.createStatement();
				st.executeUpdate("insert into student values(6,'shyam','prasad','cse','4.88')");
				System.out.println("data got inserted sucessfully");
				st.close();
				con.close();
			}
		    catch(Exception ex)
			{
		      ex.printStackTrace();	
			}
			

		}

}

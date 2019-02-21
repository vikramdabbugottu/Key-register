package com.sdlc.ExcelDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reading_excel_to_Db
{
	 public static void main(String[] args) throws IOException, SQLException
	 {
		 Reading_excel_to_Db dbc=new Reading_excel_to_Db();
		 dbc.readexcelfile();
	 }
	
	static String [] cellvariable=new String[6];
	public static void readexcelfile() throws IOException, SQLException
	{
		try {
			
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/keysdatabase","root","Vikram#123"); 
				
			FileInputStream file =new FileInputStream("G:\\excel\\testReadStudents.xlsx");
			Workbook workbook=new XSSFWorkbook(file);
			Sheet sh=workbook.getSheet("sheet1");
			int number_of_rows=sh.getLastRowNum();
			int number_of_cells;
			int number_of_columns=5;
			for(int i=1;i<number_of_rows;i++)
			{
				Row row=sh.getRow(i); 
				for(int j=0;j<=number_of_columns;j++)
				{
					Cell cell=row.getCell(j);
					String cell_value=cell.toString();
					cellvariable[j]=cell_value;
					System.out.println(cell_value);
				}
				Statement st=con.createStatement();
				String sqlquery="INSERT INTO keysinfo"
						+ "(district_name,adress,ownername,key_type,key_position,key_information) "
						+ "VALUES('"+cellvariable[0]+"','"+cellvariable[1]+"','"+cellvariable[2]+"','"+cellvariable[3]+"','"+cellvariable[4]+"','"+cellvariable[5]+"')";
				PreparedStatement pstm=con.prepareStatement(sqlquery);
				pstm.execute();				
			}
			//con.commit();
			con.close();
			
		} 
		
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
}
	 
	

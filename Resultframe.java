package com.sdlc.ExcelDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Resultframe 
{
	

	public static void main(String[] args) 
	{
		Secondframe sf=new Secondframe();
		sf.Readexcel();
	}
}
class Secondframe extends JFrame
{
	
	
	public Secondframe()//constructor
	{  
		
	}
	public void Readexcel()
	{
		JFrame f=new JFrame();       
	    
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("G:\\excel\\testReadStudents.xlsx");

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sh = workbook.getSheet("Sheet1");
			String strrowValue = null;
			String strCellValue;
			String[][] celldata;
			int numberofcells = 0;
			int totalcells = 0;
			int numberOfSheets = workbook.getNumberOfSheets();
			int numberofrows = sh.getLastRowNum();
			String findstring = "2002.0";
         
			for (int i = 1; i < numberofrows; i++) {
				Row row = sh.getRow(i);
				//numberofcells = row.getLastCellNum();
				//totalcells += numberofcells;
				//Cell cell = row.getCell(0);
				//strrowValue = cell.toString();
				//System.out.println(strrowValue);

				
					for (int j = 0; j < numberofcells; j++) {
						Cell cell1 = row.getCell(j);
						strCellValue = cell1.toString();
						System.out.println(strCellValue);
						String data=strCellValue;
						System.out.println(data);
					}
				System.out.println("////***////");

			}
			String column[]={"Distriktnumber","Adress","Ownername","Keytype","Keyposition","Keynmber"};         
		    Object[][] data = null;
			JTable jt=new JTable(data,column);    
		    jt.setBounds(30,40,80,100);          
		    JScrollPane sp=new JScrollPane(jt);    
		    f.add(sp);          
		    f.setSize(600,400);    
		    f.setVisible(true);  
			long endtme=System.currentTimeMillis();


			fis.close();
			//System.out.println("---");
			//System.out.println(numberOfSheets);
			//System.out.println(numberofrows);
			//System.out.println(totalcells);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


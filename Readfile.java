package com.sdlc.ExcelDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readfile {

	private static final String FILE_PATH = "testReadStudents.xlsx";

	public static void main(String args[]) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("G:\\excel\\testReadStudents.xlsx");

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sh = workbook.getSheet("Sheet1");
			String strrowValue = null;
			String strCellValue;
			int numberofcells = 0;
			int totalcells = 0;
			int numberOfSheets = workbook.getNumberOfSheets();
			int numberofrows = sh.getLastRowNum();
			String findstring = "2002.0";
         long b=System.currentTimeMillis();
			for (int i = 1; i < numberofrows; i++) {
				Row row = sh.getRow(i);
				numberofcells = row.getLastCellNum();
				totalcells += numberofcells;
				Cell cell = row.getCell(0);
				strrowValue = cell.toString();
				//System.out.println(strrowValue);

				if (strrowValue.equals(findstring)) {
					for (int j = 0; j < numberofcells; j++) {
						Cell cell1 = row.getCell(j);
						strCellValue = cell1.toString();
						System.out.println(strCellValue);
					}
				}
				System.out.println("////***////");

			}
			long endtme=System.currentTimeMillis();
			long time=endtme-b;
			System.out.println(time);

			fis.close();
			System.out.println("---");
			System.out.println(numberOfSheets);
			System.out.println(numberofrows);
			System.out.println(totalcells);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

package com.sdlc.ExcelDemo;

import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excelltojtable {

	public static void main(String[] args) 
	{
		Vector headers = new Vector();
		Vector data = new Vector();

		File file = new File("G:\\excel\\testReadStudents.xls");
		try {
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		headers.clear();
		for (int i = 0; i < sheet.getColumns(); i++) {
		Cell cell1 = sheet.getCell(i, 0);
		headers.add(cell1.getContents());
		}
		data.clear();
		for (int j = 1; j < sheet.getRows(); j++) {
		Vector d = new Vector();
		for (int i = 0; i < sheet.getColumns(); i++) {
		Cell cell = sheet.getCell(i, j);
		d.add(cell.getContents());
		}
		d.add("\n");
		data.add(d);
		}
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel(data,headers);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		model = new DefaultTableModel(data, headers);
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		JFrame f=new JFrame();
		f.add(scroll);
		f.setSize(600, 800);
		f.setResizable(true);
		f.setVisible(true);
		

	}

}

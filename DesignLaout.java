package com.sdlc.ExcelDemo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DesignLaout extends JFrame implements ActionListener  
{
	//First(search) display frame components
	private JFrame jframe;
	private JLabel Distrikt, Adress, Adress1,KeyPosition, Keytype,KeyValue,KeyInfo,Owner;
	private JTextField DField,AdressField,Adress1Field,KeyPositionField,
					   KeytypeField,KeyValueField,KeyInfoField,OwnerField;
	private static JButton Search;
	
	private JFrame jframe1;
	private JLabel displayDistrikt;
	private JLabel displayAdress;
	private JLabel displayAdress1;
	private JLabel displayKeyPosition;
	private JLabel displayKeytype;
	private JLabel displayKeyValue;
	private JLabel displayKeyInfo;
	private JLabel displayOwner;
	
	private JTextField displayDistriktField;
	private JTextField displayAdressField;
	private JTextField displayAdress1Field;
	private JTextField displayKeyPositionField;
	private JTextField displayKeytypeField;
	private JTextField displayKeyValueField;
	private JTextField displayKeyInfoField;
	private JTextField displayOwnerField;
	private static JButton Edit;
	private JButton Adddata;
	private JButton Deletedata;
	private JButton Savedata;
	
	Boolean searchvar=true;
	String [] Displayvariable=new String[6];
	String findstring;
	
	String distriktvariable;
	String adressvariable;
	String adressvariable1;
	String keypositionvariable;
	String keytypevariable;
	String keyvaluevariable;
	String keyinfovariable;
	String ownervariable;
	
	
	
	DesignLaout()//Constructor
	{
		displayLayout();// The first display layout function
		//searchExcelData();//The excel data sheet function
		
	}

	public static void main(String[] args) //Main method
	{
		DesignLaout object=new DesignLaout();
		
		//Edit.addActionListener(object);
		
		
		

	}
	/////////////First display layout. Here we enter the search data///////////
	public void displayLayout()
	{
		//The window is created
		jframe=new JFrame("Key Register");
		jframe.setSize(400,400);
		
		
		//The Distrikt lable and text box
		Distrikt=new JLabel("Distrikt");
		Distrikt.setBounds(30, 30, 55, 15);//x axis, y axis, width, height 
		
		DField=new JTextField();
		DField.setBounds(90, 30, 206, 21);
		//The Adress and Adress2 lable and textbox 
		Adress=new JLabel("Adress");
		Adress.setBounds(30, 60, 55, 15);//x axis, y axis, width, height 
		
		AdressField=new JTextField();
		AdressField.setBounds(90, 60, 206, 21);
		
		Adress1=new JLabel("Adress 2");
		Adress1.setBounds(30, 90, 55, 15);//x axis, y axis, width, height 
		
		Adress1Field=new JTextField();
		Adress1Field.setBounds(90, 90, 206, 21);
		//The KeyPosition lable and textbox 
		KeyPosition=new JLabel("position");
		KeyPosition.setBounds(30, 120, 55, 15);//x axis, y axis, width, height 
		
		KeyPositionField=new JTextField();
		KeyPositionField.setBounds(90, 120, 206, 21);
		//The keytype lable and textbox 
		Keytype=new JLabel("Keytype");
		Keytype.setBounds(30, 150, 55, 15);//x axis, y axis, width, height 
		
		KeytypeField=new JTextField();
		KeytypeField.setBounds(90, 150, 206, 21);
		//KeyValue lable and textbox
		KeyValue=new JLabel("Keyvalue");
		KeyValue.setBounds(30, 180, 55, 15);
		
		KeyValueField =new JTextField();
		KeyValueField.setBounds(90, 180, 206, 21);
		//keyInfo lable and textbox
		KeyInfo=new JLabel("KeyInfo");
		KeyInfo.setBounds(30, 210, 55, 15);
		
		KeyInfoField=new JTextField();
		KeyInfoField.setBounds(90, 210, 206, 21);
		//owner lable and textbox
		Owner=new JLabel("Ägare");
		Owner.setBounds(30, 240, 55, 15);
	
		OwnerField=new JTextField();
		OwnerField.setBounds(90, 240, 206, 21);
		//Search button 
		Search=new JButton("Search");
		Search.setBounds(90, 290, 100, 21);
		Search.addActionListener(this);	
		
		
		//Adding all the fields to the frame or window
		jframe.add(Distrikt);
		jframe.add(DField);
		jframe.add(Adress);
		jframe.add(AdressField);
		jframe.add(Adress1);
		jframe.add(Adress1Field);
		jframe.add(KeyPosition);
		jframe.add(KeyPositionField);
		jframe.add(Keytype);
		jframe.add(KeytypeField);
		jframe.add(KeyValue);
		jframe.add(KeyValueField);
		jframe.add(KeyInfo);
		jframe.add(KeyInfoField);
		jframe.add(Owner);
		jframe.add(OwnerField);
		jframe.add(Search);
	
		
		jframe.setLayout(null);
		jframe.setVisible(true);//The frame is displayed if the visbile is true.
		setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		
		
	}
	//The second Frame display to show the search results.
	public void searchresultdisplay()
	{
		jframe1=new JFrame("Search Display");
		jframe1.setSize(450,450);
		
		displayDistrikt=new JLabel("Distrikt");
		displayDistrikt.setBounds(30, 30, 55, 15);//x axis, y axis, width, height 
		
		displayDistriktField=new JTextField();
		displayDistriktField.setBounds(90, 30, 206, 21);
		
		displayAdress=new JLabel("Adress");
		displayAdress.setBounds(30, 60, 55, 15);//x axis, y axis, width, height 
		
		displayAdressField=new JTextField();
		displayAdressField.setBounds(90, 60, 206, 21);
		
		displayAdress1=new JLabel("Adress 2");
		displayAdress1.setBounds(30, 90, 55, 15);//x axis, y axis, width, height 
		
		displayAdress1Field=new JTextField();
		displayAdress1Field.setBounds(90, 90, 206, 21);
		
		displayKeyPosition=new JLabel("position");
		displayKeyPosition.setBounds(30, 120, 55, 15);//x axis, y axis, width, height 
		
		displayKeyPositionField=new JTextField();
		displayKeyPositionField.setBounds(90, 120, 206, 21);
		
		displayKeytype=new JLabel("Keytype");
		displayKeytype.setBounds(30, 150, 55, 15);//x axis, y axis, width, height 
		
		displayKeytypeField=new JTextField();
		displayKeytypeField.setBounds(90, 150, 206, 21);
		
		displayKeyValue=new JLabel("Keyvalue");
		displayKeyValue.setBounds(30, 180, 55, 15);
		
		displayKeyValueField =new JTextField();
		displayKeyValueField.setBounds(90, 180, 206, 21);
		
		displayKeyInfo=new JLabel("KeyInfo");
		displayKeyInfo.setBounds(30, 210, 55, 15);
		
		displayKeyInfoField=new JTextField();
		displayKeyInfoField.setBounds(90, 210, 206, 21);
		
		displayOwner=new JLabel("Ägare");
		displayOwner.setBounds(30, 240, 55, 15);
		
		displayOwnerField=new JTextField();
		displayOwnerField.setBounds(90, 240, 206, 21);
		
		Edit=new JButton("Edit");
		Edit.setBounds(30, 290, 100, 21);
		//Edit.addActionListener();	
		
		Adddata=new JButton("ADD");
		Adddata.setBounds(140, 290, 100, 21);
		//Adddata.addActionListener();
		
		Deletedata =new JButton("Deteledata");
		Deletedata.setBounds(250,290, 100, 21);
		//Deletedata.addActionListener();
		
		Savedata=new JButton("Save");
		Savedata.setBounds(140, 330, 100, 21);
		Savedata.setBackground(Color.green);
		Savedata.setToolTipText("Please press save button to save data");
		//savedata.addActionListener();
		
		jframe1.add(displayDistrikt);
		jframe1.add(displayDistriktField);
		jframe1.add(displayAdress);
		jframe1.add(displayAdressField);
		jframe1.add(displayAdress1);
		jframe1.add(displayAdress1Field);
		jframe1.add(displayKeyPosition);
		jframe1.add(displayKeyPositionField);
		jframe1.add(displayKeytype);
		jframe1.add(displayKeytypeField);
		jframe1.add(displayKeyValue);
		jframe1.add(displayKeyValueField);
		jframe1.add(displayKeyInfo);
		jframe1.add(displayKeyInfoField);
		jframe1.add(displayOwner);
		jframe1.add(displayOwnerField);
		
		jframe1.add(Edit);
		jframe1.add(Adddata);
		jframe1.add(Deletedata);
		jframe1.add(Savedata);
		jframe1.setVisible(false);
		jframe1.setLayout(null);
		setDefaultCloseOperation(jframe1.EXIT_ON_CLOSE);
	}
	
///////////////Search button action//////////////////////////////////
	///////////////////////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) 
	{

		Secondframe rs=new Secondframe();
		//database connection
		//DbConnection dbc=new DbConnection();
		//dbc.getConnection();
		distriktvariable=DField.getText();
		adressvariable=AdressField.getText();
		adressvariable1=Adress1Field.getText();
		keypositionvariable=KeyPositionField.getText();
		keytypevariable=KeytypeField.getText();
		keyvaluevariable=KeyValueField.getText();
		keyinfovariable=KeyInfoField.getText();
		ownervariable=OwnerField.getText();
		
		
		System.out.println(ownervariable);
		
		searchExcelData();//Calling the function to search in the excel sheet
		
		boolean isfound=searchExcelData();
		if(isfound)
		{  
			searchresultdisplay();//Function to display the search results from the excel
			jframe1.setVisible(true);
			displayDistriktField.setText(Displayvariable[0]);
			displayAdressField.setText(Displayvariable[1]);
			displayKeyPositionField.setText(Displayvariable[4]);
			displayKeytypeField.setText(Displayvariable[3]);
			displayKeyValueField.setText(Displayvariable[5]);
			displayOwnerField.setText(Displayvariable[2]);
			
		}
		else
		{
			Displayvariable=null;
			jframe1.setVisible(false);
		}
		
	}
	//Searching the excel file./////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	private boolean searchExcelData()
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("G:\\excel\\testReadStudents.xlsx");

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sh = workbook.getSheet("Sheet1");
			String strrowValue = null;
			String strCellValue = null;
			int numberofcells = 0;
			int totalcells = 0;
			int numberOfSheets = workbook.getNumberOfSheets();
			int numberofrows = sh.getLastRowNum();
			int cellNumber=0;
			int stringcount=0;//This string count helps to fit the data in textfields
			int fieldNumber=0;//The fileds in the display layout.To pass the value to getcell()
			String findstring=null;
			long b=System.currentTimeMillis();
         //Logic to get exact row number 
         if(distriktvariable!=null && !distriktvariable.equals(""))
         {
        	 System.out.println(distriktvariable);
        	 cellNumber=0;
        	 //cellTextvariable=DField;
        	 findstring = DField.getText();
        	
         }
         if(adressvariable!=null && !adressvariable.equals(""))
         {System.out.println(adressvariable);
        	 cellNumber=1;
        	 //cellTextvariable=Adress1Field;
        	 findstring =Adress1Field.getText();
         }
         if(ownervariable!=null && !ownervariable.equals(""))
         {
        	 System.out.println("ownervariable");
        	 cellNumber=2;
        	 //cellTextvariable=OwnerField;
        	 findstring =OwnerField.getText();
         }
         if(keytypevariable!=null && !keytypevariable.equals(""))
         {
        	 System.out.println("keytypevariable");
        	 cellNumber=3;
        	 findstring =KeytypeField.getText();
         }
         if(keypositionvariable!=null && !keypositionvariable.equals(""))
         {
        	 System.out.println("keypositionvariable");
        	 cellNumber=4;
        	 findstring =KeyPositionField.getText();
         }
         if(keyvaluevariable!=null && !keyvaluevariable.equals(""))
         {
        	 System.out.println("keyvaluevariable");
        	 cellNumber=5;
        	 findstring =KeyValue.getText();
         }
         
         /////
			for (int i = 1; i < numberofrows; i++) {
				Row row = sh.getRow(i);
				numberofcells = row.getLastCellNum();
				totalcells += numberofcells;
				Cell cell = row.getCell(cellNumber);
				strrowValue = cell.toString();
				System.out.println("cellnumber"+cellNumber);
				//String findstring = cellTextvariable.getText();
				if (strrowValue.equals(findstring)) {
					for (int j = 0; j < numberofcells; j++) 
					{
						Cell cell1 = row.getCell(j);
						strCellValue = cell1.toString();
						System.out.println(strCellValue);
						if(strrowValue.equals(findstring)&& stringcount<6)
						{
							
							Displayvariable[stringcount]=strCellValue;
							stringcount++;
							
						}
					}
				}
				//Displayvariable=strCellValue;//testing to fill the search filed with data
			}
			long endtme=System.currentTimeMillis();
			long time=endtme-b;
			System.out.println(time);
			
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public void keypressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			System.out.println("uparrow pressed");
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			System.out.println("Down arrow");
		
	}
}

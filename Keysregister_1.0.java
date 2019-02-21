package com.sdlc.ExcelDemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.tools.JavaCompiler;
import javax.swing.JScrollPane;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;

public class Keysregister extends JFrame {

	private JPanel contentPane;
	private static JTable table_key;
	private static JTextField districtfield;
	private static JTextField adressfield;
	private static JTextField keytypefield;
	private static JTextField keyinfofield;
	private static JTextField keynumberfield;
	private static JTextField keypositionfield;
	private static JTextField keyownerfield;
	private static JTextField ownerinfofield;
	private static JTextField idfield;
	
	static Connection con=null;
	static PreparedStatement ptsm=null;
	
	/**
	 * The ActionEvents and listeners code is written under the respective button or event. 
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Keysregister frame = new Keysregister();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					getconnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Code for Connecting to MySQL database
	 */
	public static Connection getconnection()
	{
		//Connection con=null;
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
	
	/**
	 * Code for  displaying all the content from DB table to the Jtable
	 */
	public static void displaytable()
	{
		
		String sqlquery="SELECT *FROM keysinfo";
		try {
			ptsm=con.prepareStatement(sqlquery);
			ResultSet rs=ptsm.executeQuery();
			table_key.setModel(DbUtils.resultSetToTableModel(rs));
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	/**
	 * Code for search button
	 */
	
	public static void search()
	{
		String districtvar=districtfield.getText();
		String adressvar=adressfield.getText();
		String keyownervar=keyownerfield.getText();
		String keytypevar=keytypefield.getText();
			
			try {
				if(districtvar!=null && !districtvar.equals(""))
				{
				String searchquery="SELECT * FROM keysinfo WHERE district_name=?";
				ptsm=con.prepareStatement(searchquery);
				ptsm.setString(1, districtfield.getText());
				ResultSet rs=ptsm.executeQuery();
				table_key.setModel(DbUtils.resultSetToTableModel(rs));
				}
				else if(adressvar!=null && !adressvar.equals(""))
				{
					String searchquery="SELECT * FROM keysinfo WHERE adress=?";
					ptsm=con.prepareStatement(searchquery);
					ptsm.setString(1, adressvar);
					ResultSet rs=ptsm.executeQuery();
					table_key.setModel(DbUtils.resultSetToTableModel(rs));
				}
				else if(keyownervar!=null && !keyownervar.equals(""))
				{
					String searchquery="SELECT * FROM keysinfo WHERE ownername=?";
					ptsm=con.prepareStatement(searchquery);
					ptsm.setString(1, keyownervar);
					ResultSet rs=ptsm.executeQuery();
					table_key.setModel(DbUtils.resultSetToTableModel(rs));
				}
				else if(keytypevar!=null && !keytypevar.equals(""))
				{
					String searchquery="SELECT * FROM keysinfo WHERE key_type=?";
					ptsm=con.prepareStatement(searchquery);
					ptsm.setString(1, keytypevar);
					ResultSet rs=ptsm.executeQuery();
					table_key.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
			} 
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
			
		
		
	}
	/**
	 * Code for update button
	 */
	public static void updatebutton()
	{
		String updatequery="UPDATE keysinfo set district_name='"+districtfield.getText()+"',"
				+ "adress='"+adressfield.getText()+"',ownername= '"+keyownerfield.getText()+"',"
				+ "key_type='"+keytypefield.getText()+"',key_position='"+keypositionfield.getText()+"',"
				+ "key_information='"+keyinfofield.getText()+"' WHERE ID='"+idfield.getText()+"'";
		try {
			ptsm=con.prepareStatement(updatequery);
			int rs=ptsm.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
				}
	
	
	/**
	 * Code for Add button
	 *
	 */
	
	public static void addinfo()
	{
		String addquery="INSERT INTO keysinfo(district_name,adress,ownername,key_type,key_position,key_information) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			ptsm=con.prepareStatement(addquery);
			ptsm.setString(1, districtfield.getText());
			ptsm.setString(2, adressfield.getText());
			ptsm.setString(3, keyownerfield.getText());
			ptsm.setString(4, keytypefield.getText());
			ptsm.setString(5, keypositionfield.getText());
			ptsm.setString(6, keyinfofield.getText());
			int rs=ptsm.executeUpdate();
			JOptionPane.showMessageDialog(null,"key is added to"+districtfield.getText());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Code for Add button
	 *
	 */
	
	public void deleteinfo()
	{
		String deletequery="DELETE FROM keysinfo WHERE district_name=? AND adress=? AND ownername=?"
				+ "AND key_type=? AND key_position=? AND key_information=?";
		
		try {
			ptsm=con.prepareStatement(deletequery);
			ptsm.setString(1, districtfield.getText());
			ptsm.setString(2, adressfield.getText());
			ptsm.setString(3, keyownerfield.getText());
			ptsm.setString(4, keytypefield.getText());
			ptsm.setString(5, keypositionfield.getText());
			ptsm.setString(6, keyinfofield.getText());
			int rs=ptsm.executeUpdate();
			JOptionPane.showMessageDialog(null, "key is deleted from"+districtfield.getText());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public Keysregister() {
		setForeground(new Color(65, 105, 225));
		setTitle("KeysRegister");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 678);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new LineBorder(SystemColor.activeCaption, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_key = new JTable();
		table_key.setBounds(310, 49, 564, 459);
		contentPane.add(table_key);
		
		JLabel lblDistrictNumber = new JLabel("District number");
		lblDistrictNumber.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDistrictNumber.setBounds(23, 49, 97, 23);
		contentPane.add(lblDistrictNumber);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAdress.setBounds(23, 86, 97, 23);
		contentPane.add(lblAdress);
		
		JLabel lblKeyType = new JLabel("Key Type");
		lblKeyType.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblKeyType.setBounds(23, 185, 97, 23);
		contentPane.add(lblKeyType);
		
		JLabel lblKeyInformation = new JLabel("Key Information");
		lblKeyInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblKeyInformation.setBounds(23, 224, 97, 23);
		contentPane.add(lblKeyInformation);
		
		JLabel lblKeytaggNumber = new JLabel("Key/Tagg number");
		lblKeytaggNumber.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblKeytaggNumber.setBounds(23, 268, 112, 23);
		contentPane.add(lblKeytaggNumber);
		
		JLabel lblKeyPosition = new JLabel("Key Position");
		lblKeyPosition.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblKeyPosition.setBounds(23, 314, 97, 23);
		contentPane.add(lblKeyPosition);
		
		JLabel lblKeyOwner = new JLabel("Key Owner");
		lblKeyOwner.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblKeyOwner.setBounds(23, 357, 97, 23);
		contentPane.add(lblKeyOwner);
		
		JLabel lblOwnerInfo = new JLabel("Owner Info");
		lblOwnerInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblOwnerInfo.setBounds(23, 406, 97, 23);
		contentPane.add(lblOwnerInfo);
		
		districtfield = new JTextField();
		districtfield.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 255), null, new Color(0, 102, 255), null));
		districtfield.setBounds(130, 50, 145, 20);
		contentPane.add(districtfield);
		districtfield.setColumns(10);
		
		adressfield = new JTextField();
		adressfield.setHorizontalAlignment(SwingConstants.CENTER);
		adressfield.setColumns(10);
		adressfield.setBounds(130, 87, 145, 88);
		contentPane.add(adressfield);
		
		keytypefield = new JTextField();
		keytypefield.setColumns(10);
		keytypefield.setBounds(130, 186, 145, 20);
		contentPane.add(keytypefield);
		
		keyinfofield = new JTextField();
		keyinfofield.setColumns(10);
		keyinfofield.setBounds(130, 225, 145, 20);
		contentPane.add(keyinfofield);
		
		keynumberfield = new JTextField();
		keynumberfield.setColumns(10);
		keynumberfield.setBounds(130, 269, 145, 35);
		contentPane.add(keynumberfield);
		
		keypositionfield = new JTextField();
		keypositionfield.setColumns(10);
		keypositionfield.setBounds(130, 315, 145, 20);
		contentPane.add(keypositionfield);
		
		keyownerfield = new JTextField();
		keyownerfield.setColumns(10);
		keyownerfield.setBounds(130, 358, 145, 20);
		contentPane.add(keyownerfield);
		
		ownerinfofield = new JTextField();
		ownerinfofield.setColumns(10);
		ownerinfofield.setBounds(130, 407, 145, 101);
		contentPane.add(ownerinfofield);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			search();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(250, 584, 89, 23);
		contentPane.add(button);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				updatebutton();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(395, 584, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnAddKey = new JButton("Add key");
		btnAddKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			addinfo();
			}
		});
		btnAddKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddKey.setBounds(536, 584, 89, 23);
		contentPane.add(btnAddKey);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Keys Info", "Owners Info", "Keys full Info"}));
		comboBox.setBounds(77, 585, 127, 20);
		contentPane.add(comboBox);
		
		JButton btnDeleteKey = new JButton("Delete key");
		btnDeleteKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteinfo();
			}
		});
		btnDeleteKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeleteKey.setBounds(671, 584, 112, 23);
		contentPane.add(btnDeleteKey);
		
		JScrollPane scrollPane = new JScrollPane(table_key);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				try
				{
					int row=table_key.getSelectedRow();
					String selectedrow=(table_key.getModel().getValueAt(row,0).toString());
					String sql="SELECT * FROM keysinfo WHERE id='"+selectedrow+"'";
					ptsm=con.prepareStatement(sql);
					ResultSet rs=ptsm.executeQuery();
					if(rs.next())
					{
						String txtfield1=rs.getString("district_name");
						districtfield.setText(txtfield1);
						String txtfield2=rs.getString("adress");
						adressfield.setText(txtfield2);
						String txtfield3=rs.getString("ownername");
						keyownerfield.setText(txtfield3);
						String txtfield4=rs.getString("id");
						idfield.setText(txtfield4);
						String txtfield5=rs.getString("key_type");
						keytypefield.setText(txtfield5);
						String txtfield6=rs.getString("key_position");
						keypositionfield.setText(txtfield6);
						String txtfield7=rs.getString("key_information");
						keyinfofield.setText(txtfield7);
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		scrollPane.setBounds(new Rectangle(1, 1, 1, 1));
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(310, 49, 664, 459);
		contentPane.add(scrollPane);
		
		idfield = new JTextField();
		idfield.setBounds(310, 18, 86, 20);
		contentPane.add(idfield);
		idfield.setColumns(10);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
	}
}


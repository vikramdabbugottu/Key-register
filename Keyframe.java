package com.sdlc.ExcelDemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyframe extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JTextField keyposition;
	private static JTextField keytype;
	private static JTextField keysystemno;
	private static JTextField markt;
	private static JTextField keyotherinfo;
	private static JTextField districtnumber;
	private static JTextField id;
	private static JTextArea ownerinfo;
	private static JTextArea adress;
	//private static JTextPane search_var;
	private static JTextArea searchvar;
	
	private JLabel lblKeyType;
	private JLabel lblKey;
	private JLabel lblMarkt;
	private JLabel lblKeyOtherInfo;
	private JLabel lblOwnerInfo;
	
	static Connection con=null;
	static PreparedStatement ptsm=null;
	static ResultSet rs=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Keyframe frame = new Keyframe();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
		try {
			 con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/keysdatabase","root","Vikram#123");
			 JOptionPane.showMessageDialog(null, "Connection sucessfull");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection not sucessfull");
		}
		return con;
		
	}
	
	/**
	 * Create the Search.
	 */
	public static void showmessage()
	{
		//getconnection();
		String searchvariable=searchvar.getText();
		JOptionPane.showMessageDialog(null, searchvariable);
	}
	
	public static void search()
	{
		getconnection();
		String searchvariable=searchvar.getText();
		try {
			String searchquery="SELECT *FROM keysexcel WHERE district like ?";
			ptsm=con.prepareStatement(searchquery);
			ptsm.setString(1, '%'+searchvariable+'%');
			rs=ptsm.executeQuery();
			if(rs.next())
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			searchquery="SELECT *FROM keysexcel WHERE adress like ?";
			ptsm=con.prepareStatement(searchquery);
			ptsm.setString(1, '%'+searchvar.getText()+'%');
			rs=ptsm.executeQuery();
			if(rs.next())
				{
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Code for the Update button.
	 */
	public static void update()
	{
		String updatequery="UPDATE keysexcel set district=?, key_position=?,adress=?,"
				+ "key_type=?,system_key=?,markt=?,frasc=?,ownername=? WHERE id=?";
		try {
			ptsm=con.prepareStatement(updatequery);
			ptsm.setString(1, districtnumber.getText());
			ptsm.setString(2, keyposition.getText());
			ptsm.setString(3, adress.getText());
			ptsm.setString(4, keytype.getText());
			ptsm.setString(5, keysystemno.getText());
			ptsm.setString(6, markt.getText());
			ptsm.setString(7, keyotherinfo.getText());
			ptsm.setString(8, ownerinfo.getText());
			ptsm.setString(9, id.getText());
			
			int rs=ptsm.executeUpdate();
			JOptionPane.showMessageDialog(null,"Key to"+""+districtnumber.getText()+""+"updated");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Code for the add key button.
	 */
	
	public static void addkey()
	{
		String addquery="INSERT INTO keysexcel (district,key_position,adress,key_type,"
				+ "system_key,markt,frasc,ownerinfo) values(?,?,?,?,?,?,?,?)";
		try {
			ptsm=con.prepareStatement(addquery);
			ptsm.setString(1, districtnumber.getText());
			ptsm.setString(2, keyposition.getText());
			ptsm.setString(3, adress.getText());
			ptsm.setString(4, keytype.getText());
			ptsm.setString(5, keysystemno.getText());
			ptsm.setString(6, markt.getText());
			ptsm.setString(7, keyotherinfo.getText());
			ptsm.setString(8, ownerinfo.getText());
			int rs=ptsm.executeUpdate();
			JOptionPane.showMessageDialog(null,"Key to"+""+districtnumber.getText()+""+"Added");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Code for delete button.
	 */
	
	public static void delete()
	{
		String deletequery="DELETE FROM keysexcel WHERE id=?";
		try {
			ptsm=con.prepareStatement(deletequery);
			ptsm.setString(1, id.getText());
			int rs=ptsm.executeUpdate();
			JOptionPane.showMessageDialog(null,"Key to"+""+districtnumber.getText()+""+"Deleted");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Keyframe() {
		setFont(new Font("Arial Black", Font.BOLD, 14));
		setTitle("Keys Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(500, 10, 670, 551);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					String selectedrow=(table.getModel().getValueAt(row,0).toString());
					String sql="SELECT * FROM keysexcel WHERE id='"+selectedrow+"'";
					ptsm=con.prepareStatement(sql);
					ResultSet rs=ptsm.executeQuery();
					if(rs.next())
					{
						String txtfield1=rs.getString("district");
						districtnumber.setText(txtfield1);
						String txtfield2=rs.getString("adress");
						adress.setText(txtfield2);
						//String txtfield3=rs.getString("ownername");
						//ownerinfo.setText(txtfield3);
						String txtfield3=rs.getString("id");
						id.setText(txtfield3);
						String txtfield4=rs.getString("key_type");
						keytype.setText(txtfield4);
						String txtfield5=rs.getString("key_position");
						keyposition.setText(txtfield5);
						String txtfield6=rs.getString("system_key");
						keysystemno.setText(txtfield6);
						String txtfield7=rs.getString("markt");
						markt.setText(txtfield7);
						String txtfield8=rs.getString("frasc");
						keyotherinfo.setText(txtfield8);
						String txtfield9=rs.getString("ownername");
						ownerinfo.setText(txtfield9);
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		scrollPane.setBounds(500, 10, 670, 551);
		contentPane.add(scrollPane);
		
		keyposition = new JTextField();
		keyposition.setColumns(10);
		keyposition.setBounds(164, 145, 148, 35);
		contentPane.add(keyposition);
		
		adress = new JTextArea();
		adress.setBounds(164, 191, 148, 90);
		contentPane.add(adress);
		
		keytype = new JTextField();
		keytype.setColumns(10);
		keytype.setBounds(164, 292, 148, 35);
		contentPane.add(keytype);
		
		keysystemno = new JTextField();
		keysystemno.setColumns(10);
		keysystemno.setBounds(164, 338, 148, 35);
		contentPane.add(keysystemno);
		
		markt = new JTextField();
		markt.setColumns(10);
		markt.setBounds(164, 384, 148, 35);
		contentPane.add(markt);
		
		keyotherinfo = new JTextField();
		keyotherinfo.setColumns(10);
		keyotherinfo.setBounds(164, 430, 148, 35);
		contentPane.add(keyotherinfo);
		
		districtnumber = new JTextField();
		districtnumber.setColumns(10);
		districtnumber.setBounds(164, 99, 148, 35);
		contentPane.add(districtnumber);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(164, 53, 148, 35);
		contentPane.add(id);
		
		ownerinfo = new JTextArea();
		ownerinfo.setBounds(164, 476, 148, 90);
		contentPane.add(ownerinfo);
		
		JLabel lblDistrictNumber = new JLabel("ID");
		lblDistrictNumber.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblDistrictNumber.setBounds(39, 56, 115, 28);
		contentPane.add(lblDistrictNumber);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblAdress.setBounds(39, 191, 115, 28);
		contentPane.add(lblAdress);
		
		JLabel lblDistrictNumber_1 = new JLabel("District Number");
		lblDistrictNumber_1.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblDistrictNumber_1.setBounds(39, 99, 115, 28);
		contentPane.add(lblDistrictNumber_1);
		
		JLabel lblKeyPosition = new JLabel("Key Position");
		lblKeyPosition.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblKeyPosition.setBounds(39, 145, 115, 28);
		contentPane.add(lblKeyPosition);
		
		lblKeyType = new JLabel("Key Type");
		lblKeyType.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblKeyType.setBounds(39, 292, 115, 28);
		contentPane.add(lblKeyType);
		
		lblKey = new JLabel("Key System no");
		lblKey.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblKey.setBounds(39, 338, 115, 28);
		contentPane.add(lblKey);
		
		lblMarkt = new JLabel("Markt");
		lblMarkt.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblMarkt.setBounds(39, 384, 115, 28);
		contentPane.add(lblMarkt);
		
		lblKeyOtherInfo = new JLabel("Key Other Info");
		lblKeyOtherInfo.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblKeyOtherInfo.setBounds(39, 430, 115, 28);
		contentPane.add(lblKeyOtherInfo);
		
		lblOwnerInfo = new JLabel("Owner Info");
		lblOwnerInfo.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		lblOwnerInfo.setBounds(39, 476, 115, 28);
		contentPane.add(lblOwnerInfo);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 34, 299, 551);
		contentPane.add(panel);
		
		JButton search_button = new JButton("Search");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				search();
				//showmessage();
			}
		});
		search_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		search_button.setBounds(480, 596, 101, 35);
		contentPane.add(search_button);
		
		JButton addkey_button = new JButton("Add Key");
		addkey_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addkey();
			}
		});
		addkey_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		addkey_button.setBounds(371, 105, 101, 35);
		contentPane.add(addkey_button);
		
		JButton update_button = new JButton("Update");
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		update_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		update_button.setBounds(371, 177, 101, 35);
		contentPane.add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		delete_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		delete_button.setBounds(371, 246, 101, 35);
		contentPane.add(delete_button);
		
		JButton print_button = new JButton("Print");
		print_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header=new MessageFormat("Keys Table");
				MessageFormat footer=new MessageFormat("page");
				
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					
				} catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Can't print the Table");
				}
			}
		});
		print_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		print_button.setBounds(371, 324, 101, 35);
		contentPane.add(print_button);
		
		JButton exit_button = new JButton("Exit");
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		exit_button.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		exit_button.setBounds(371, 407, 101, 35);
		contentPane.add(exit_button);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VenkataVikram\\Downloads\\download.png"));
		lblNewLabel.setBounds(812, 584, 349, 66);
		contentPane.add(lblNewLabel);
		
		searchvar = new JTextArea();
		searchvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent enter) {
				if(enter.getKeyCode()==KeyEvent.VK_ENTER)
				{
					search();
				}
			}
		});
		searchvar.setBounds(291, 602, 136, 29);
		contentPane.add(searchvar);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText(null);
				districtnumber.setText(null);
				keyposition.setText(null);
				adress.setText(null);
				keytype.setText(null);
				keysystemno.setText(null);
				markt.setText(null);
				keyotherinfo.setText(null);
				ownerinfo.setText(null);
				
			}
		});
		btnClear.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		btnClear.setBounds(371, 477, 101, 35);
		contentPane.add(btnClear);
	}
}


package com.PrjLibrary.Martha;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.awt.Font;

public class Confirmat extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookName;
	private JTextField txtName;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmat frame = new Confirmat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Confirmat() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Confirmation Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getDBInstance();
		Connection con=getConnection();
		final Statement stmt=con.createStatement();
		
		
		JButton btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String BookNam,Nam,Dat,Book;
				Nam=txtName.getText().toString();
				Dat=txtDate.getText().toString();
				BookNam=txtBookName.getText().toString();
				Book="";
				
				if(BookNam.length()<0 ||Dat.length()<0){
					JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
				}
				if(BookNam.length()>0){
					try{
				         
				         
				         ResultSet rs =stmt.executeQuery("SELECT * FROM libraryData.booksinstore WHERE (BookName='"+BookNam+"')");
				         while(rs.next()){
				                
				                Book=rs.getString("BookName");
				               // LibId=rs.getString("LibraryId");
				                
				         }
				         if (Book.equals(BookNam)){
				        	 stmt.execute("DELETE FROM booksinstore WHERE (BookName='"+BookNam+"')");
				        	 stmt.executeUpdate("INSERT INTO bookstaken(BookNames,UserName,Date) values('"+BookNam+"','"+Nam+"','"+Dat+"')");
				        	 
				        	 JOptionPane.showMessageDialog(null,"You took" + "  " +  BookNam + " " + "book on" +" "+ Dat + " "+ "return it after three days");
				        	 System.exit(0);
				        	 
				     }
				         else if(Book!=(BookNam)) {
				        	 JOptionPane.showMessageDialog(null,"Wrong Book Name");
				        	 
				        	
				         }
				      
					}
				         
				         catch(SQLException e){
				         }
				         }
				}
			
			
		});
		
		btnYes.setBounds(50, 212, 66, 23);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNo.setBounds(178, 212, 53, 23);
		contentPane.add(btnNo);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(161, 46, 86, 20);
		contentPane.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(161, 102, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setBounds(161, 160, 86, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblBookname = new JLabel("BookName");
		lblBookname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBookname.setBounds(50, 46, 86, 20);
		contentPane.add(lblBookname);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(52, 105, 64, 14);
		contentPane.add(lblName);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDate.setBounds(52, 163, 46, 14);
		contentPane.add(lblDate);
		
		JLabel lblNewLabel = new JLabel("Are you taking the book?Enter your details");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 11, 277, 24);
		contentPane.add(lblNewLabel);
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		try {
	        Connection con = DriverManager.getConnection
	        ("jdbc:mysql://localhost:3306/LibraryData","root",""); 
	        return con;
		}
	    catch(SQLException e){
	       System.out.println("SQL exception occured" + e);
	    }
		return null;
	}

	private void getDBInstance() {
		// TODO Auto-generated method stub
		try {
	         Class.forName("com.mysql.jdbc.Driver");
	      }
	      catch(ClassNotFoundException e) {
	         System.out.println("Class not found "+ e);
	      }
		 System.out.println("JDBC Class found");
	}
	
	}


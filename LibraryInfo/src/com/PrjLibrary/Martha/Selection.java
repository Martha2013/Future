package com.PrjLibrary.Martha;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.Statement;

public class Selection extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookName;
	private JTextField txtAuthor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selection frame = new Selection();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Selection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Selection Page");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter BookName:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(39, 31, 148, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAuthor.setBounds(39, 88, 86, 18);
		contentPane.add(lblAuthor);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=getConnection();
				getDBInstance();
				String BookNam=txtBookName.getText().toString();
				String Author=txtAuthor.getText().toString();
				String Book="";
				String Area="";
				String Borrower="";
				
                if(Author.length()<=0) {
			    	
			    	JOptionPane.showMessageDialog(null,"Who is the Author?");
			    }
			    
			    
			    if(BookNam.length()>0 || Author.length()>0){
			      try{
			         Statement st = con.createStatement();
			         
			         ResultSet rs =st.executeQuery("SELECT * FROM libraryData.booksinstore WHERE (BookName='"+BookNam+"')");
			         while(rs.next()){
			                
			                Book=rs.getString("BookName");
			                Area=rs.getString("Location");
			                
			                
			         }
				           
			        
			         if (Book.equals(BookNam)){
			        	 JOptionPane.showMessageDialog(null,"Its located at "+Area +  "\n" + "  ","_"+Book,JOptionPane.INFORMATION_MESSAGE);
			        	
			        	Confirmat next=new Confirmat();
			        	next.setVisible(true);
			        	 
			        	 
			        	 
					         }
			      
			         
			        
			         else if(Book!=(BookNam) ){
			        	 JOptionPane.showMessageDialog(null,"Not in store");
			        	 
			        	try{
			    			         ResultSet rs1 =st.executeQuery("SELECT * FROM LibraryData.bookstaken WHERE (BookNames='"+BookNam+"')");
			    			         while(rs1.next()){
			    			                
			    			                Book=rs1.getString("BookNames");
			    			                Borrower=rs1.getString("UserName");
			    			                
			    			                
			    			         }
			        			 }
			        	 catch(SQLException e){
			        		 
			        	 }
			        	
			        	if(Book.equals(BookNam)){
			        		JOptionPane.showMessageDialog(null,"Its taken by "+Borrower +  "\n" + "  " ,"_"+Book,JOptionPane.INFORMATION_MESSAGE);
			        	}else{
			        		JOptionPane.showMessageDialog(null, "We dont have that book in this library");
			        	}
			        	 
			        	
			         }
			      }
			      catch(SQLException e){
			      }
			      }
				
			    
				
				
			}

			private Connection getConnection() {
				// TODO Auto-generated method stub
				try{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryData","root","");
					return con;
				}
				catch(SQLException e){
					System.out.println("SQL Exception Occured"+ e);
				}
				return null;
			}

			private void getDBInstance() {
				// TODO Auto-generated method stub
				try{
					Class.forName("com.jdbc.mysql.Driver");
				}
				catch(ClassNotFoundException e){
					System.out.println("Class not found "+ e);
				}
				System.out.println("JDBC class Found");
				
			}
		});
		btnNewButton.setBounds(323, 168, 101, 23);
		contentPane.add(btnNewButton);
		
		txtBookName = new JTextField();
		txtBookName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtBookName.setBounds(181, 31, 159, 18);
		contentPane.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtAuthor.setBounds(181, 88, 159, 18);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
	}

}

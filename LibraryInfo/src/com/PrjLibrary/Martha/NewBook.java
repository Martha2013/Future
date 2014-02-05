package com.PrjLibrary.Martha;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.Font;
import java.sql.Connection;

public class NewBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAuthor;
	private JTextField txtId;
	private JTextField txtArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBook frame = new NewBook();
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
	public NewBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("NewBook Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtName.setBounds(170, 12, 102, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtAuthor.setBounds(170, 60, 102, 20);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtId.setBounds(170, 112, 102, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtArea = new JTextField();
		txtArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtArea.setBounds(170, 169, 102, 20);
		contentPane.add(txtArea);
		txtArea.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("ADD");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 getDBInstance();
				   Connection con= getConnection();
				  
				String BName,BId,Author,Area;
				
					BName=txtName.getText().toString();
					Author=txtAuthor.getText().toString();
					BId=txtId.getText().toString();
				    Area=txtArea.getText().toString(); 
			
				
				
				    if(BName.length()<=0 || Area.length()<=0) {
				    
				    	JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
				    }
				    
				    if(BName.length()>0 || Area.length()>0){
				      try{
				         Statement st = con.createStatement();

				         st.executeUpdate("INSERT INTO booksinstore(BookName,Author,BookId,Location) values('"+BName+"','"+Author+"','"+BId+"','"+Area+"')");
				        
				         con.close();
				            }
				         
				 	         
				      
				      catch (SQLException e) {
						// TODO: handle exception
				    	  
					}
				      JOptionPane.showMessageDialog(null,"Added to database");
				    }
				    else{
				    	JOptionPane.showMessageDialog(null,"Fill all the blanks");
				    }
				     
				    	  System.exit(0);
				      
				    
				    
				   
			
				     
				    
				    
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
				
		
			
		});
			
		
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton_4.setBounds(103, 217, 116, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Area");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(47, 172, 65, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BookId");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(47, 115, 65, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(47, 63, 65, 17);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("BookName");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(47, 15, 65, 17);
		contentPane.add(lblNewLabel_3);
	}

}

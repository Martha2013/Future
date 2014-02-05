package com.PrjLibrary.Martha;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textId;
	private JTextField textWork;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(300, 300, 450, 300);
		setTitle("SignUp Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(27, 11, 46, 14);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textName.setBounds(131, 8, 106, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		
		JLabel lblPhone = new JLabel("LibraryId");
		lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPhone.setBounds(27, 70, 94, 17);
		contentPane.add(lblPhone);
		
		textId = new JTextField();
		textId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textId.setBounds(131, 67, 106, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JLabel lblCourse = new JLabel("Work");
		lblCourse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCourse.setBounds(31, 136, 67, 14);
		contentPane.add(lblCourse);
		
		textWork = new JTextField();
		textWork.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textWork.setBounds(131, 134, 106, 20);
		contentPane.add(textWork);
		textWork.setColumns(10);
		
		
		
		
		
		
		JButton btnComplete = new JButton("Complete");
		btnComplete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnComplete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 getDBInstance();
				   Connection con= getConnection();
				  
				String Name,Work,Id;
				
					Name=textName.getText().toString();
				Work=textWork.getText().toString();
				Id=textId.getText().toString(); 
			
				
				
				    if(Name.length()<=0 || Id.length()<=0) {
				    
				    	JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
				    }
				    
				    if(Name.length()>0 || Id.length()>0){
				      try{
				         Statement st = con.createStatement();

				         st.executeUpdate("INSERT INTO members(Name,LibraryId,Work) values('"+Name+"','"+Id+"','"+Work+"')");
				         // st.execute();
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
				     
				    	  
				      
				    
				    
				   
			
				      Login now =new Login();
				      now.setVisible(true);
				    
				    
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
		btnComplete.setBounds(27, 228, 118, 23);
		contentPane.add(btnComplete);
		
		
		
		
		
		
		
	}
}

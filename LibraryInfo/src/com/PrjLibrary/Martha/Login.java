package com.PrjLibrary.Martha;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Login extends JFrame  {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Login Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(61, 42, 65, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LibraryId");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(61, 100, 89, 17);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtName.setBounds(169, 39, 104, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		getDBInstance();
		final Connection con=getConnection();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Nam,Id,user,LibId;
				Nam=txtName.getText().toString();
				Id=txtId.getText().toString();
				user="";
				LibId="";
				if(Nam.length()<=0 || Id.length()<=0) {
			    	
			    	JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
			    }
			    
			    
			    if(Nam.length()>0 || Id.length()>0){
			      try{
			         Statement st = con.createStatement();
			         
			         ResultSet rs =st.executeQuery("SELECT * FROM libraryData.members WHERE (Name='"+Nam+"' AND LibraryId='"+Id+"')");
			         while(rs.next()){
			                
			                user=rs.getString("Name");
			                LibId=rs.getString("LibraryId");
			                
			         }
				           
			        
			        
			      
			         
			         
			         if (user.equals(Nam)&&LibId.equals(Id)){
			        	 JOptionPane.showMessageDialog(null,"Welcome "+Nam +  "\n" + "  " + "Choose a book","_"+Nam,JOptionPane.INFORMATION_MESSAGE);
			        	
			        	Selection next=new Selection();
			        	next.setVisible(true);
			        	 
			        	 
			        	 
					         }
			      
			         
			        
			          else if(user!=(Nam) &&(LibId!=(Id)) ){
			        	 JOptionPane.showMessageDialog(null,"Wrong Id or Name \n Register First");
			        	 
			        	 SignUp next=new SignUp();
							next.setVisible(true);
			         }
			      }
			      catch(SQLException e){
			      }
			      }
				
			    
		}	
			});
		btnLogin.setBounds(61, 159, 104, 23);
		contentPane.add(btnLogin);
		
		
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(217, 159, 86, 23);
		contentPane.add(btnNewButton);
		
		txtId = new JPasswordField();
		txtId.setBounds(169, 99, 98, 20);
		contentPane.add(txtId);
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


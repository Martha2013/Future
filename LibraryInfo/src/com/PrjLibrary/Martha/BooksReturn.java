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

public class BooksReturn extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookName;
	private JTextField txtName;
	private JTextField txtDate;
	private JTextField txtId;
	private JTextField txtAuthor;
	private JTextField txtLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksReturn frame = new BooksReturn();
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
	public BooksReturn() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setTitle("Return Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Are You Returning the Book?Enter your details");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(49, 11, 331, 24);
		contentPane.add(lblNewLabel);
		
		getDBInstance();
		Connection con=getConnecton();
		
		final Statement st=con.createStatement();
		
		JButton btnYes = new JButton("YES");
		btnYes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String BookNam,Nam,Dat,Book,BId,Author,Locate;
				BId=txtId.getText().toString();
				Nam=txtName.getText().toString();
				Dat=txtDate.getText().toString();
				BookNam=txtBookName.getText().toString();
				Author=txtAuthor.getText().toString();
				Locate=txtLocation.getText().toString();
				Book="";
				
				if(BookNam.length()<0 ||Dat.length()<0){
					JOptionPane.showMessageDialog(null,"Please Enter the Book Name and Date taken");
				}
				if(BookNam.length()>0 && BId.length()>0){
					try{
				         
				         
				         ResultSet rs =st.executeQuery("SELECT * FROM libraryData.bookstaken WHERE (BookNames='"+BookNam+"')");
				         while(rs.next()){
				                
				                Book=rs.getString("BookNames");
				               // LibId=rs.getString("LibraryId");
				                
				         }
				         if (Book.equals(BookNam)){
				        	 st.execute("DELETE FROM bookstaken WHERE (BookNames='"+BookNam+"')");
				        	 st.executeUpdate("INSERT INTO booksinstore(BookName,Author,BookId,Location) values('"+BookNam+"','"+Author+"','"+BId+"','"+Locate+"')");
				        	 st.executeUpdate("INSERT INTO booksreturned(BookName,Date) values('"+BookNam+"','"+Dat+"')");
				        	 
				        	 JOptionPane.showMessageDialog(null,"You returned" + "  " +  BookNam + " " + "book on" +" "+ Dat + " ");	 
				        	 
				     }
				         else if(Book!=(BookNam)) {
				        	 JOptionPane.showMessageDialog(null,"Wrong Book Name");
				        	 
				        	
				         }
				      
					}
				         
				         catch(SQLException e){
				         }
					System.exit(0);
				         }
				}
			
			
		});
		
			
		
		btnYes.setBounds(131, 361, 63, 23);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNo.setBounds(247, 361, 63, 23);
		contentPane.add(btnNo);
		
		txtBookName = new JTextField();
		txtBookName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtBookName.setBounds(216, 46, 89, 20);
		contentPane.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtName.setBounds(216, 93, 89, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDate.setBounds(216, 240, 89, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		
		
		txtId = new JTextField();
		txtId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtId.setBounds(216, 139, 89, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblBookid = new JLabel("BookId");
		lblBookid.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBookid.setBounds(101, 142, 46, 14);
		contentPane.add(lblBookid);
		
		JLabel lblName = new JLabel("Your Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(93, 96, 78, 14);
		contentPane.add(lblName);
		
		JLabel lblBookname = new JLabel("BookName");
		lblBookname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBookname.setBounds(93, 46, 78, 20);
		contentPane.add(lblBookname);
		
		JLabel lblDateTaken = new JLabel("Date");
		lblDateTaken.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDateTaken.setBounds(101, 243, 56, 14);
		contentPane.add(lblDateTaken);
		
		JLabel lblNewLabel_1 = new JLabel("Author");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(101, 190, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(216, 187, 86, 20);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLocation.setBounds(101, 296, 93, 17);
		contentPane.add(lblLocation);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(216, 294, 86, 20);
		contentPane.add(txtLocation);
		txtLocation.setColumns(10);
	}

	private Connection getConnecton() {
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


package com.PrjLibrary.Martha;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
					frame.setVisible(true);
					frame.setTitle("Welcome Page");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Welcome Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BooksReturn other=new BooksReturn();
					other.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		});
		btnNewButton.setBounds(10, 166, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New Book");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewBook next=new NewBook();
				next.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(143, 102, 107, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrow");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login next =new  Login();
				next.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(274, 166, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(65, 102, 60, -18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Library Book Data System");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		lblNewLabel_2.setForeground(new Color(148, 0, 211));
		lblNewLabel_2.setBounds(76, 38, 337, 39);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp newUser=new SignUp();
				newUser.setVisible(true);
			}
		});
		btnNewUser.setBounds(10, 228, 101, 23);
		contentPane.add(btnNewUser);
	}
}

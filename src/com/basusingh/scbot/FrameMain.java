package com.basusingh.scbot;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class FrameMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain frame = new FrameMain();
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
	public FrameMain() {
		setTitle("vvvv");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Threads");
		lblNewLabel.setBounds(21, 29, 85, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(116, 26, 127, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Thread Delay");
		lblNewLabel_1.setBounds(21, 69, 85, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 66, 127, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Proxies");
		lblNewLabel_2.setBounds(21, 113, 85, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 110, 127, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(265, 109, 89, 23);
		contentPane.add(btnNewButton);
		
		ButtonGroup radioGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("IP, Username, Password");
		rdbtnNewRadioButton.setBounds(21, 157, 183, 23);
		radioGroup.add(rdbtnNewRadioButton);
		JRadioButton rdbtnUsernamePasswordIp = new JRadioButton("Username, Password, IP");
		rdbtnUsernamePasswordIp.setBounds(21, 183, 183, 23);
		radioGroup.add(rdbtnUsernamePasswordIp);
		
		contentPane.add(rdbtnNewRadioButton);
		contentPane.add(rdbtnUsernamePasswordIp);
		
	}
}

package com.basusingh.scbot;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.Button;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static Button mStartButton;
	static Button mStopButton;
	static Stream rnhObj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rnhObj = new Stream();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
					mStartButton.addActionListener(new ActionListener() {

					    @Override
					    public void actionPerformed(ActionEvent e) {
							rnhObj.ConfigHub();
							rnhObj.StartHub();
							rnhObj.doTask();
					    }
					});
					mStopButton.addActionListener(new ActionListener() {

					    @Override
					    public void actionPerformed(ActionEvent e) {
					    	rnhObj.stopTask();
					    }
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
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
		
		mStartButton = new Button("Start");
		mStartButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		mStartButton.setBackground(Color.GREEN);
		mStartButton.setBounds(286, 350, 116, 33);
		contentPane.add(mStartButton);
		
		mStopButton = new Button("Stop");
		mStopButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		mStopButton.setBackground(new Color(255, 69, 0));
		mStopButton.setBounds(423, 350, 116, 33);
		contentPane.add(mStopButton);
		
	}
}

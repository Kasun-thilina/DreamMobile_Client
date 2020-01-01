package com.dream.mobile.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.dream.mobile.rmi.interfaces.Questions;
import com.dream.mobile.rmi.interfaces.RemoteInterface;

public class AdminSignIn extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;
	static RemoteInterface localStub;

	/**
	 * Launch the application.
	 */
	public static void ShowWindow(RemoteInterface stub) {
		try {
			localStub = stub;	
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSignIn frame = new AdminSignIn();
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
	public AdminSignIn() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 472);
		contentPane = new JPanel();
		contentPane.setInheritsPopupMenu(true);
		contentPane.setForeground(Color.CYAN);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel HeadrPanel = new JPanel();
		HeadrPanel.setBounds(5, 5, 238, 62);
		HeadrPanel.setBackground(new Color(0, 0, 0, 80));

		JLabel lblSignup = new JLabel("Admin SignIn");
		lblSignup.setBounds(326, 26, 148, 29);
		lblSignup.setFont(new Font("Cambria", Font.BOLD, 17));
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel SideLable = new JLabel("");
		SideLable.setBounds(0, 0, 243, 433);
		SideLable.setForeground(new Color(250, 240, 230));
		SideLable.setBackground(new Color(139, 69, 19));
		SideLable.setIcon(new ImageIcon(AdminSignIn.class.getResource("/com/dream/mobile/resources/Image1.jpg")));

		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setBounds(280, 136, 107, 14);

		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(280, 203, 74, 29);

		tfUsername = new JTextField();
		tfUsername.setBounds(280, 162, 229, 29);
		tfUsername.setBackground(SystemColor.control);
		tfUsername.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfUsername.setColumns(10);

		tfPassword = new JTextField();
		tfPassword.setBounds(280, 244, 229, 29);
		tfPassword.setBackground(SystemColor.control);
		tfPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPassword.setColumns(10);

		JButton btnSignIn = new JButton("SignIn");
		btnSignIn.setBounds(280, 316, 238, 35);
		/*btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Successfully Registered", "InfoBox: " + "Registration Completed",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		;*/
		btnSignIn.setForeground(SystemColor.text);
		btnSignIn.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnSignIn.setBackground(new Color(0, 0, 0));
		contentPane.setLayout(null);
		HeadrPanel.setLayout(null);

		JLabel lblDreamMobile = new JLabel("Dream Mobile");
		lblDreamMobile.setLocation(49, 5);
		lblDreamMobile.setForeground(SystemColor.controlLtHighlight);
		lblDreamMobile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDreamMobile.setSize(154, 25);
		HeadrPanel.add(lblDreamMobile);
		contentPane.add(HeadrPanel);
		contentPane.add(SideLable);
		contentPane.add(lblSignup);
		contentPane.add(lblNewLabel);
		contentPane.add(tfUsername);
		contentPane.add(lblNewLabel_2);
		contentPane.add(tfPassword);
		contentPane.add(btnSignIn);

		// Action Listeners
		tfUsername.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repaint();

			}
		});

		tfPassword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repaint();

			}
		});

		btnSignIn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repaint();
				Boolean login=false;
				try {
					login=localStub.adminLogin(tfUsername.getText(), tfPassword.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(login) {
					AdminUI.ShowWindow(localStub);
				}else {
					JOptionPane.showConfirmDialog(null,"Invalid UserName or Password","Query",JOptionPane.OK_OPTION);

				}

			}
		});

	}
}

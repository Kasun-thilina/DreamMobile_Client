package com.dream.mobile.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dream.mobile.rmi.interfaces.Questions;
import com.dream.mobile.rmi.interfaces.RemoteInterface;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class WelcomeUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserName;
	private final Action action = new SwingAction();
	static RemoteInterface stub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			// Getting the registry
			//Registry registry = LocateRegistry.getRegistry(60);
			String remoteHostName = "127.0.0.1";
	        int remotePort = 1091;
	        String connectLocation = "//" + remoteHostName + ":" + remotePort
	                + "/Server";

	        try {
	            System.out.println("Connecting to client at : " + connectLocation);
	            stub = (RemoteInterface) Naming.lookup(connectLocation);
	        } catch (MalformedURLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        } catch (RemoteException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        } catch (NotBoundException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
			// Looking up the registry for the remote object
			//stub = (RemoteInterface) registry.lookup("RemoteInterface");
			System.out.println("Sucess");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeUI mainFrame = new WelcomeUI();
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomeUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel LoginPanel = new JPanel();
		LoginPanel.setBackground(new Color(0, 0, 0, 90));
		LoginPanel.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 11));
		LoginPanel.setBounds(104, 96, 549, 334);
		contentPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		//Empty name validation text
		final JLabel lblError = new JLabel("Please Enter Your Name to Continue !");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblError.setForeground(Color.RED);
		lblError.setBounds(264, 111, 248, 25);
		lblError.setVisible(false);
		LoginPanel.add(lblError);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 750, 84);
		panel.setBackground(new Color(0, 0, 0, 80));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblDreamMobile = new JLabel("Dream Mobile");
		lblDreamMobile.setForeground(UIManager.getColor("Button.highlight"));
		lblDreamMobile.setBackground(new Color(0, 0, 0, 20));
		lblDreamMobile.setHorizontalAlignment(SwingConstants.CENTER);
		lblDreamMobile.setOpaque(true);
		lblDreamMobile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDreamMobile.setBounds(254, 6, 270, 47);
		panel.add(lblDreamMobile);

		JLabel BackroundLable = new JLabel("");
		BackroundLable.setBounds(0, 0, 750, 478);
		BackroundLable.setIcon(new ImageIcon(WelcomeUI.class.getResource("/com/dream/mobile/resources/image3.jpg")));
		contentPane.add(BackroundLable);

		// User name text label and text field
		JLabel lblUserName = new JLabel("Enter Your Name :");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUserName.setForeground(SystemColor.controlLtHighlight);
		lblUserName.setBounds(101, 85, 151, 33);
		LoginPanel.add(lblUserName);

		tfUserName = new JTextField();
		tfUserName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfUserName.setBounds(264, 85, 159, 27);
		LoginPanel.add(tfUserName);
		tfUserName.setColumns(10);
		
		tfUserName.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				repaint();
				
			}
		});

		// User Age label and age selector combo box
		JLabel lblAge = new JLabel("Enter Your Age :");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAge.setBounds(101, 130, 151, 33);
		LoginPanel.add(lblAge);

		JComboBox<String> cmbAge = new JComboBox<String>();
		cmbAge.setForeground(Color.black);
		cmbAge.setBackground(Color.white);
		cmbAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmbAge.setBounds(264, 131, 159, 33);
		cmbAge.addItem("Below 35");
		cmbAge.addItem("Above 35");
		final Object selectedItem = cmbAge.getSelectedItem();
		LoginPanel.add(cmbAge);

		cmbAge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		});

		JButton btnContinue = new JButton("Continue");
		
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinue.setBackground(SystemColor.textText);
		btnContinue.setForeground(SystemColor.text);
		btnContinue.setBounds(311, 237, 112, 33);
		LoginPanel.add(btnContinue);

		JLabel LoginLable = new JLabel("Login");
		LoginLable.setForeground(SystemColor.text);

		LoginLable.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		LoginLable.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLable.setBounds(222, 6, 136, 34);
		LoginPanel.add(LoginLable);
		
		JButton btnAdmin = new JButton("Admin Login");
		btnAdmin.setForeground(SystemColor.menu);
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdmin.setBackground(Color.WHITE);
		btnAdmin.setBounds(101, 238, 112, 33);
		LoginPanel.add(btnAdmin);
		
		
		//On Click Listners
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
				String name = tfUserName.getText();
				if (!(tfUserName.getText().isEmpty())) {
					lblError.setVisible(false);
					if (selectedItem == "Below 35") {
						YoungUI.ShowWindow(name,stub);
					} else {

					}
				}
				else {
					lblError.setVisible(true);
				}

			}
		});
		
		btnAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				repaint();
				AdminSignIn.ShowWindow(stub);
			}
		});
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}

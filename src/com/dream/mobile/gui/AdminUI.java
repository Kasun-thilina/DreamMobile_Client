package com.dream.mobile.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dream.mobile.rmi.interfaces.RemoteInterface;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpRequest.BodyPublishers;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.dream.mobile.rmi.interfaces.Analytics;

public class AdminUI extends JFrame {

	private JPanel contentPane;
	static RemoteInterface localStub;
	// HTTP Client for API Connection
	private final static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	Image image = null;
	Image scaledImage = null;
	JLabel contentLabel = new JLabel("Loading");
	JPanel viewPanel = new JPanel();
	private JTable table;

	public static void main(String[] args) {
		// Getting the registry
		// Registry registry = LocateRegistry.getRegistry(60);
		String remoteHostName = "127.0.0.1";
		int remotePort = 1091;
		String connectLocation = "//" + remoteHostName + ":" + remotePort + "/Server";

		try {
			System.out.println("Connecting to client at : " + connectLocation);
			localStub = (RemoteInterface) Naming.lookup(connectLocation);
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
		// stub = (RemoteInterface) registry.lookup("RemoteInterface");
		System.out.println("Sucess");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
					AdminUI frame = new AdminUI();
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
	public AdminUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 42));
		lblNewLabel.setBounds(625, 48, 364, 45);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 231, 729);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnViewDatabase = new JButton("View Database");
		btnViewDatabase.setForeground(Color.WHITE);
		btnViewDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		btnViewDatabase.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnViewDatabase.setBounds(31, 227, 163, 27);
		panel.add(btnViewDatabase);

		JLabel lblButton_2 = new JLabel("Button 3");
		lblButton_2.setForeground(Color.WHITE);
		lblButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblButton_2.setBounds(71, 512, 88, 14);
		panel.add(lblButton_2);

		JLabel lblNewLabel_3 = new JLabel("Dream Mobile");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(new Color(0, 0, 0, 90));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel_3.setBounds(0, 0, 231, 176);
		panel.add(lblNewLabel_3);

		JButton button_1 = new JButton("New button");
		button_1.setForeground(Color.WHITE);
		button_1.setIcon(new ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		button_1.setBounds(0, 489, 231, 60);
		panel.add(button_1);

		JLabel lblButton_1 = new JLabel("Button2");
		lblButton_1.setForeground(Color.WHITE);
		lblButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblButton_1.setBounds(71, 375, 88, 19);
		panel.add(lblButton_1);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		btnNewButton.setBounds(0, 211, 231, 60);
		btnNewButton.setBackground(new Color(0, 0, 0, 90));
		panel.add(btnNewButton);

		JButton button = new JButton("New button");
		button.setIcon(new ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		button.setBounds(0, 352, 231, 60);
		panel.add(button);

		
		viewPanel.setBounds(278, 133, 905, 512);
		viewPanel.setBackground(Color.BLACK);
		contentPane.add(viewPanel);

		contentLabel.setForeground(Color.WHITE);
		contentLabel.setBounds(10, 28, 905, 512);
		viewPanel.add(contentLabel);
		viewPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(210, 0, 1240, 177);
		lblNewLabel_2.setIcon(new ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		lblNewLabel_2.setBackground(Color.BLACK);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Admin Panel");
		lblNewLabel_1.setBounds(566, 56, 307, 42);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));

		lblNewLabel_1.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel_1.setBackground(new Color(0, 0, 0, 20));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLUE);
		progressBar.setBounds(390, 269, 146, 20);
		viewPanel.add(progressBar);

		progressBar.setStringPainted(true);

		
		setUpTable();
		
		btnViewDatabase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getChart();
			}
		});
	}

	private void getChart() {
		String quickChartURL = "";// chartConnection();
		try {
			quickChartURL = localStub.getChart();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			URL url = new URL(quickChartURL);
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scaledImage = image.getScaledInstance(contentLabel.getWidth(), contentLabel.getHeight(), Image.SCALE_SMOOTH);
		contentLabel.setIcon(new ImageIcon(scaledImage));
		// JLabel lblimage = new JLabel(new ImageIcon(image));
	}
	
	private void setUpTable() {
		String data[][] = { };
		String column[] = { "ID", "User Name", "Preferred OS","Price Range","Preferred RAM","Preferred Screen Size","Preferred Back Camera",
				"Preferred Front Camers","Preferred Storage Capacity","Micro Sd","Preferred BRand","Other Features"};
		
		DefaultTableModel model=new DefaultTableModel(data,column) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// All cells made to non editable
				return false;
			}
			
		};
		table = new JTable(model);
		table.setBounds(0, 10, 905, 512);
		final TableColumnModel columnModel = table.getColumnModel();
	    for (int column2 = 0; column2 < table.getColumnCount(); column2++) {
	        int width = 100; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column2);
	            Component comp = table.prepareRenderer(renderer, row, column2);
	            width = Math.max(comp.getPreferredSize().width +10 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column2).setPreferredWidth(width);
	    }
	
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		viewPanel.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 905, 512);
		viewPanel.add(scrollPane);
		
		Object[] row= new Object[12];
		ArrayList<Analytics> list=new ArrayList<Analytics>();
		try {
			list= localStub.getAnalytics();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.toString());
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getId();
			row[1]=list.get(i).getName();
			row[2]=list.get(i).getOS();
			row[3]=list.get(i).getPrice();
			row[4]=list.get(i).getRam();
			row[5]=list.get(i).getScreen();
			row[6]=list.get(i).getBackCamera();
			row[7]=list.get(i).getFrontCamera();
			row[8]=list.get(i).getStorage();
			row[9]=list.get(i).getMicroSd();
			row[10]=list.get(i).getBrand();
			row[11]=list.get(i).getOther();
			model.addRow(row);
		}
	}

	private class chart extends SwingWorker<String, String> {

		@Override
		protected String doInBackground() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void process(List<String> chunks) {

		}

		@Override
		protected void done() {
			// TODO Auto-generated method stub
			super.done();
		}
	}

	private void fetchData() {

	}
}

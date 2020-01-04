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
	JLabel lblChart = new JLabel("Loading Chart");
	JLabel lblTable = new JLabel("Loading Table");
	JLabel lblInfo = new JLabel("Loading Info");
	
	JPanel contentPanel = new JPanel();
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

		JLabel lblMainHeader = new JLabel("Admin Panel");
		lblMainHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainHeader.setFont(new Font("Cooper Std Black", Font.BOLD | Font.ITALIC, 42));
		lblMainHeader.setBounds(625, 48, 364, 45);
		contentPane.add(lblMainHeader);

		JPanel sidePanel = new JPanel();
		sidePanel.setForeground(Color.WHITE);
		sidePanel.setBounds(0, 0, 231, 729);
		sidePanel.setBackground(Color.BLACK);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);

		JLabel lblHeading = new JLabel("Dream Mobile");
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setBackground(new Color(0, 0, 0, 90));
		lblHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblHeading.setBounds(0, 0, 231, 176);
		sidePanel.add(lblHeading);

		ImageIcon BG = new ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg"));

		JButton btnViewChart = new JButton("View Analyzed Data");
		btnViewChart.setForeground(Color.BLACK);
		// btnNewButton.setIcon(new
		// ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		btnViewChart.setBounds(0, 211, 231, 60);
		btnViewChart.setBackground(Color.GREEN);
		sidePanel.add(btnViewChart);

		JButton btnViewDB = new JButton("View Database");
		btnViewDB.setForeground(Color.BLACK);
		// btnViewDB.setIcon(new
		// ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		btnViewDB.setBounds(0, 352, 231, 60);
		sidePanel.add(btnViewDB);

		JButton btnAboutUs = new JButton("About The developers");
		btnAboutUs.setForeground(Color.BLACK);
		// button_1.setIcon(new
		// ImageIcon(AdminUI.class.getResource("/com/dream/mobile/resources/image9.jpg")));
		btnAboutUs.setBounds(0, 489, 231, 60);
		sidePanel.add(btnAboutUs);

		// Setting the size for content
		contentPanel.setBounds(278, 133, 1000, 512);
		lblChart.setBounds(0, 0, 1000, 512);
		lblTable.setBounds(0, 0, 1000, 512);
		lblInfo.setBounds(0, 0, 1000, 512);

		contentPanel.setBackground(Color.BLACK);
		contentPane.add(contentPanel);
		lblChart.setForeground(Color.WHITE);
		contentPanel.add(lblChart);
		contentPanel.add(lblTable);
		contentPanel.add(lblInfo);

		lblChart.setVisible(false);
		lblTable.setVisible(false);
		lblInfo.setVisible(false);

		contentPanel.setLayout(null);

		JLabel bgLabel = new JLabel("");
		bgLabel.setBounds(210, 0, 1240, 177);
		bgLabel.setIcon(BG);
		bgLabel.setBackground(Color.BLACK);
		contentPane.add(bgLabel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(278, 654, 996, 38);
		bottomPanel.setLayout(null);
		contentPane.add(bottomPanel);

		bottomPanel.setVisible(false);

		JButton btnOsPref = new JButton("OS Preferences");
		btnOsPref.setBounds(6, 6, 117, 29);
		bottomPanel.add(btnOsPref);

		JButton btnPricePref = new JButton("Price Preferences");
		btnPricePref.setBounds(116, 6, 138, 29);
		bottomPanel.add(btnPricePref);

		JButton btnCameraPref = new JButton("Camera Preferences");
		btnCameraPref.setBounds(249, 6, 148, 29);
		bottomPanel.add(btnCameraPref);

		//
		ArrayList<String> labelNames = new ArrayList<String>();
		ArrayList<Integer> chartValues = new ArrayList<Integer>();
		btnViewChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblChart.setVisible(true);
				lblTable.setVisible(false);
				lblInfo.setVisible(false);
				bottomPanel.setVisible(true);

				lblChart.setText("Chart display");

			}
		});

		btnOsPref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelNames.clear();
				chartValues.clear();
				
				labelNames.add("IOs");
				labelNames.add("Android");		
				try {
					SwingWorker<Void, Void> worker=new SwingWorker<Void, Void>(){
						@Override
						protected Void doInBackground() throws Exception {
							chartValues.add(localStub.getItemCount("OS", "Ios"));
							chartValues.add(localStub.getItemCount("OS", "Android"));
							return null;
						}

						@Override
						protected void done() {
							super.done();
							loadChart(labelNames, chartValues, "OS Preferences of Users","bar");
							System.out.println("sucess");
						}
						
					};
					worker.execute();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPricePref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelNames.clear();
				chartValues.clear();
				
				labelNames.add("10000-20000");
				labelNames.add("20000-30000");		
				labelNames.add("30000-40000");		
				labelNames.add("40000-60000");		
				labelNames.add("60000 or above");		


				try {
					SwingWorker<Void, Void> worker=new SwingWorker<Void, Void>(){
						@Override
						protected Void doInBackground() throws Exception {
							chartValues.add(localStub.getItemCount("amount", "10000-20000"));
							chartValues.add(localStub.getItemCount("amount", "20000-30000"));
							chartValues.add(localStub.getItemCount("amount", "30000-40000"));
							chartValues.add(localStub.getItemCount("amount", "40000-60000"));
							chartValues.add(localStub.getItemCount("amount", "60000 or above"));


							return null;
						}

						@Override
						protected void done() {
							super.done();
							loadChart(labelNames, chartValues, "Affordable Price Range of Users","pie");
							System.out.println("sucess");
						}
						
					};
					worker.execute();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCameraPref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelNames.clear();
				chartValues.clear();
				
				labelNames.add("8MP");
				labelNames.add("12MP");		
				labelNames.add("16MP");		
				labelNames.add("20MP+");		
				try {
					SwingWorker<Void, Void> worker=new SwingWorker<Void, Void>(){
						@Override
						protected Void doInBackground() throws Exception {
							chartValues.add(localStub.getItemCount("back_camera", "8MP"));
							chartValues.add(localStub.getItemCount("back_camera", "12MP"));
							chartValues.add(localStub.getItemCount("back_camera", "16MP"));
							chartValues.add(localStub.getItemCount("back_camera", "20MP+"));

							return null;
						}

						@Override
						protected void done() {
							super.done();
							loadChart(labelNames, chartValues, "Main Camera Preferences of Users","bar");
							System.out.println("sucess");
						}
						
					};
					worker.execute();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnViewDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblChart.setIcon(null);
				lblChart.setVisible(false);
				lblTable.setVisible(true);
				lblInfo.setVisible(false);
				bottomPanel.setVisible(false);

				setUpTable();
			}
		});

		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblChart.setIcon(null);
				lblChart.setVisible(false);
				lblTable.setVisible(false);
				lblInfo.setVisible(true);
				bottomPanel.setVisible(false);

				lblChart.setBackground(Color.white);

			}
		});
	}

	private void loadChart(ArrayList<String> labelNames, ArrayList<Integer> chartValues, String heading,String chartType) {
		String quickChartURL = "";// chartConnection();
		try {
			quickChartURL = localStub.getChart(labelNames, chartValues, heading,chartType);
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
		scaledImage = image.getScaledInstance(lblChart.getWidth(), lblChart.getHeight(), Image.SCALE_SMOOTH);
		lblChart.setIcon(new ImageIcon(scaledImage));
		// JLabel lblimage = new JLabel(new ImageIcon(image));
	}

	private void setUpTable() {
		String data[][] = {};
		String column[] = { "ID", "User Name", "Preferred OS", "Price Range", "Preferred RAM", "Preferred Screen Size",
				"Preferred Back Camera", "Preferred Front Camers", "Preferred Storage Capacity", "Micro Sd",
				"Preferred BRand", "Other Features" };

		DefaultTableModel model = new DefaultTableModel(data, column) {

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
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column2).setPreferredWidth(width);
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		lblTable.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 905, 512);
		lblTable.add(scrollPane);

		Object[] row = new Object[12];
		ArrayList<Analytics> list = new ArrayList<Analytics>();
		try {
			list = localStub.getAnalytics();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.toString());
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getName();
			row[2] = list.get(i).getOS();
			row[3] = list.get(i).getPrice();
			row[4] = list.get(i).getRam();
			row[5] = list.get(i).getScreen();
			row[6] = list.get(i).getBackCamera();
			row[7] = list.get(i).getFrontCamera();
			row[8] = list.get(i).getStorage();
			row[9] = list.get(i).getMicroSd();
			row[10] = list.get(i).getBrand();
			row[11] = list.get(i).getOther();
			model.addRow(row);
		}
	}

	/**
	 * 
	 * @author KASUN THILINA
	 * Swing Worker Class to fetch data in a background thread
	 */
	private class getCounts extends SwingWorker<String, String> {

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

package com.dream.mobile.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dream.mobile.rmi.interfaces.RemoteInterface;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckResults extends JFrame {

	private JPanel contentPane;
	static RemoteInterface stub;
	JLabel lblImg = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void ShowWindow() {
		String remoteHostName = "192.168.43.236";
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckResults frame = new CheckResults();
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
	public CheckResults() {
		setBounds(100, 100, 752, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 736, 434);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("Check Your Results");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setBackground(new Color(255, 255, 255));
		lblHeader.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 22));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, 736, 79);
		layeredPane.add(lblHeader);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resultPanel.setBackground(UIManager.getColor("Button.disabledShadow"));
		resultPanel.setBounds(54, 79, 635, 344);
		layeredPane.add(resultPanel);
		resultPanel.setLayout(null);
		
		JLabel lblLoading = new JLabel("Loading Data.Please Wait...");
		lblLoading.setBounds(275, 7, 135, 14);
		resultPanel.add(lblLoading);
		
		lblImg.setBounds(0, 0, 635, 363);
		resultPanel.add(lblImg);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 736, 79);
		label.setOpaque(true);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label.setBackground(new Color(0,0,0,80));
		layeredPane.add(label);
		
		JLabel BackroundLable = new JLabel("");
		BackroundLable.setIcon(new ImageIcon(CheckResults.class.getResource("/com/dream/mobile/resources/image7.jpg")));
		BackroundLable.setBounds(0, 0, 736, 434);
		layeredPane.add(BackroundLable);
		fetchData();
	}

	private void fetchData() {
		String types[]= {"OS,ram,back_camera,brand"};
		
		//getting answers based on question ID to an array
		ArrayList<String> osAnswers=new ArrayList<String>();
		ArrayList<String> ramAnswers=new ArrayList<String>();
		ArrayList<String> cameraAnswers=new ArrayList<String>();
		ArrayList<String> brandAnswers=new ArrayList<String>();
		
		try {
			osAnswers=stub.getOnlyAnswers(1);
			ramAnswers=stub.getOnlyAnswers(3);
			cameraAnswers=stub.getOnlyAnswers(5);
			brandAnswers=stub.getOnlyAnswers(9);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		


		HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
		map.put("OS", osAnswers);
		map.put("ram", ramAnswers);
		map.put("camera", cameraAnswers);
		map.put("brand", brandAnswers);

		/*try {
			for(int i=0;i<=types.length;i++) {
				ArrayList<String> result=stub.getAnalytics(types[i]);
				for(int j=0;j<=result.size();j++) {
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		String url = "https://quickchart.io/chart?width=500&height=300&c={type:'bar',data:{labels:['January','February','March','April', 'May'], datasets:[{label:'Dogs',data:[50,60,70,180,190]},{label:'Cats',data:[100,200,300,400,500]}]}}";
		
		URL quickChartURL = null;
		try {
			quickChartURL = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedImage c = ImageIO.read(quickChartURL);
			lblImg.setIcon(new ImageIcon(c));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}



}


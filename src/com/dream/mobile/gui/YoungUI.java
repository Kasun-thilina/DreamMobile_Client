package com.dream.mobile.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.dream.mobile.rmi.interfaces.Questions;
import com.dream.mobile.rmi.interfaces.RemoteInterface;


import javax.swing.JTextPane;

public class YoungUI extends JFrame {

	private JPanel contentPane;
	final JLabel QuestionLabel = new JLabel("");
	final JButton nextButton = new JButton("Next");
	final JButton previousButton = new JButton("Previous");
	int index = 0;
	boolean finish = false;
	static Questions questions;
	static ArrayList<String> displayQuestions = new ArrayList<String>();
	static ArrayList<String> Answer_01 = new ArrayList<String>();
	static ArrayList<String> Answer_02 = new ArrayList<String>();
	static ArrayList<String> Answer_03 = new ArrayList<String>();
	static ArrayList<String> Answer_04 = new ArrayList<String>();
	static ArrayList<String> Answer_05 = new ArrayList<String>();
	static ArrayList<String> selectedAnswers = new ArrayList<String>();
	static RemoteInterface localStub;

	JRadioButton rdoOptionOne = new JRadioButton("");
	JRadioButton rdoOptionTwo = new JRadioButton("");
	JRadioButton rdoOptionThree = new JRadioButton("");
	JRadioButton rdoOptionFour = new JRadioButton("");
	JRadioButton rdoOptionFive = new JRadioButton("");
	ButtonGroup radioGroup = new ButtonGroup();
	JButton btnCheckResults = new JButton("Check Results");
	static JLabel lblUserName = new JLabel("Welcome User! Please enter your Dream Mobile Specifications");

	/**
	 * Launch the application.
	 */
	public static void ShowWindow(String name,RemoteInterface stub) {
		try {
			localStub=stub;
			// Calling the remote method using the obtained object
			List<Questions> list = (List) localStub.getQuestions();
			for (Questions q : list) {
				displayQuestions = q.getQuestions();
				Answer_01 = q.getAnswer_01();
				Answer_02 = q.getAnswer_02();
				Answer_03 = q.getAnswer_03();
				Answer_04 = q.getAnswer_04();
				Answer_05 = q.getAnswer_05();
			}
			selectedAnswers.add(name);
			lblUserName.setText("Welcome " + name + " ! Please enter your Dream Mobile Specifications");
			// System.out.println(list);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoungUI youngUI = new YoungUI();
					youngUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YoungUI() {

		setBounds(100, 100, 623, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// setting up the first question
		QuestionLabel.setText("1) " + displayQuestions.get(0));
		rdoOptionOne.setText(Answer_01.get(0));
		rdoOptionTwo.setText(Answer_02.get(0));
		rdoOptionThree.setVisible(false);
		rdoOptionFour.setVisible(false);
		rdoOptionFive.setVisible(false);

		// Grouping the radio buttons

		JLabel WelcomeLable = new JLabel();
		String name = WelcomeLable.getName();

		btnCheckResults.setBackground(new Color(255, 102, 153));
		btnCheckResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				resultsFrame resultPage = new resultsFrame();
//				resultPage.setVisible(true);
				CheckResults.ShowWindow();
				
				saveToDataBase();
			}
		});
		btnCheckResults.setBounds(103, 418, 401, 23);
		contentPane.add(btnCheckResults);
		btnCheckResults.setVisible(false);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 0, 0, 80));
		panel_1.setBounds(0, 0, 750, 84);
		contentPane.add(panel_1);

		JLabel lblTitle = new JLabel("Dream Mobile");
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBackground(new Color(0, 0, 0, 20));
		lblTitle.setBounds(165, 6, 270, 47);
		panel_1.add(lblTitle);

		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(125, 62, 502, 16);
		panel_1.add(lblUserName);

		JPanel QuestionPanel = new JPanel();
		QuestionPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		QuestionPanel.setBounds(103, 125, 401, 263);
		contentPane.add(QuestionPanel);
		QuestionPanel.setLayout(null);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(130, 16, 0, 0);
		QuestionPanel.add(horizontalBox);

		previousButton.setBounds(50, 234, 89, 23);
		QuestionPanel.add(previousButton);

		QuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		QuestionLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		QuestionLabel.setBounds(27, 30, 349, 29);
		QuestionPanel.add(QuestionLabel);

		nextButton.setBounds(276, 234, 89, 23);
		QuestionPanel.add(nextButton);

		JPanel PanelAnswers = new JPanel();
		PanelAnswers.setBounds(16, 58, 360, 164);
		QuestionPanel.add(PanelAnswers);
		PanelAnswers.setLayout(null);

		radioGroup.add(rdoOptionOne);
		rdoOptionOne.setBounds(6, 32, 141, 23);
		rdoOptionOne.setActionCommand("A");
		PanelAnswers.add(rdoOptionOne);

		radioGroup.add(rdoOptionTwo);
		rdoOptionTwo.setBounds(159, 32, 195, 23);
		rdoOptionTwo.setActionCommand("B");
		PanelAnswers.add(rdoOptionTwo);

		radioGroup.add(rdoOptionThree);
		rdoOptionThree.setBounds(6, 82, 141, 23);
		rdoOptionThree.setActionCommand("C");
		PanelAnswers.add(rdoOptionThree);

		radioGroup.add(rdoOptionFour);
		rdoOptionFour.setBounds(159, 82, 216, 23);
		rdoOptionFour.setActionCommand("D");
		PanelAnswers.add(rdoOptionFour);

		radioGroup.add(rdoOptionFive);
		rdoOptionFive.setBounds(91, 130, 141, 23);
		rdoOptionFive.setActionCommand("E");
		PanelAnswers.add(rdoOptionFive);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(386, 0, 17, 263);
		QuestionPanel.add(scrollBar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(YoungUI.class.getResource("/com/dream/mobile/resources/image5.jpg")));
		lblNewLabel.setBounds(0, 0, 617, 494);
		contentPane.add(lblNewLabel);

		
		/**
		 * Action Receiver- Next Button
		 */
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(nextButton)) {
					if (radioGroup.getSelection() != null) {
						getAnswer(radioGroup);
						swapQuestion(nextButton);
					}
				}
			}

		});
		
		
		/**
		 * Action Receiver- Previous Button
		 */
		 
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(previousButton)) {
					// clearing the
					swapQuestion(previousButton);
				}
			}
		});
/**
 * Save results to database
 */
		btnCheckResults.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveToDataBase();
				dispose();
			}
		});

	}
/**
 * 
 * @param btn
 * 
 * Handling the next and previous question displaying with a single function
 */
	 
	private void swapQuestion(JButton btn) {
		try {
			if (finish) {
				btnCheckResults.setVisible(true);
				
			}
			if (btn == nextButton) {
				if (index < 10) {
					index++;
					QuestionLabel.setText(index + 1 + ") " + displayQuestions.get(index));
					rdoOptionOne.setText(Answer_01.get(index));
					rdoOptionTwo.setText(Answer_02.get(index));
					if (Answer_03.get(index).equalsIgnoreCase("null")) {
						rdoOptionThree.setVisible(false);
					} else {
						rdoOptionThree.setVisible(true);
						rdoOptionThree.setText(Answer_03.get(index));
					}
					if (Answer_04.get(index).equalsIgnoreCase("null")) {
						rdoOptionFour.setVisible(false);
					} else {
						rdoOptionFour.setVisible(true);
						rdoOptionFour.setText(Answer_04.get(index));
					}
					if (Answer_05.get(index).equalsIgnoreCase("null")) {
						rdoOptionFive.setVisible(false);
					} else {
						rdoOptionFive.setVisible(true);
						rdoOptionFive.setText(Answer_05.get(index));
					}
					if (index + 1 == displayQuestions.size()) {
						nextButton.setText("Finish");
						finish = true;
					}
				}
			} else if (btn == previousButton) {
				if (index != 0) {
					index--;
					QuestionLabel.setText(index + 1 + ") " + displayQuestions.get(index));
					rdoOptionOne.setText(Answer_01.get(index));
					rdoOptionTwo.setText(Answer_02.get(index));
					if (Answer_03.get(index).equalsIgnoreCase("null")) {
						rdoOptionThree.setVisible(false);
					} else {
						rdoOptionThree.setVisible(true);
						rdoOptionThree.setText(Answer_03.get(index));
					}
					if (Answer_04.get(index).equalsIgnoreCase("null")) {
						rdoOptionFour.setVisible(false);
					} else {
						rdoOptionFour.setVisible(true);
						rdoOptionFour.setText(Answer_04.get(index));
					}
					if (Answer_05.get(index).equalsIgnoreCase("null")) {
						rdoOptionFive.setVisible(false);
					} else {
						rdoOptionFive.setVisible(true);
						rdoOptionFive.setText(Answer_05.get(index));
					}
					nextButton.setText("Next");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
/**
 * Get  answers to a array list based on the radio button selection
 * @param radioGroup
 */
	private void getAnswer(ButtonGroup radioGroup) {
		String answer = radioGroup.getSelection().getActionCommand();
		if (answer == "A") {
			selectedAnswers.add(Answer_01.get(index));
		} else if (answer == "B") {
			selectedAnswers.add(Answer_02.get(index));
		} else if (answer == "C") {
			selectedAnswers.add(Answer_03.get(index));
		} else if (answer == "D") {
			selectedAnswers.add(Answer_04.get(index));
		} else {
			selectedAnswers.add(Answer_05.get(index));
		}
		radioGroup.clearSelection();
	}
/**
 * Save data to a database
 */
	private void saveToDataBase() {
		try {
			String result = (String) localStub.saveAnswer(selectedAnswers);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

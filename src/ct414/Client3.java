package ct414;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import java.awt.Color;

public class Client3 {

	private JFrame assessmentFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client3 window = new Client3();
					window.assessmentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		assessmentFrame = new JFrame();
		assessmentFrame.setBounds(100, 100, 780, 510);
		assessmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		assessmentFrame.getContentPane().setLayout(null);
		
		JLabel assessmentLabel = new JLabel("Assignment 1");
		assessmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		assessmentLabel.setBounds(12, 13, 149, 36);
		assessmentFrame.getContentPane().add(assessmentLabel);
		
		JLabel closingTimeLabel = new JLabel("Closing Time: 12:00");
		closingTimeLabel.setBounds(173, 27, 133, 16);
		assessmentFrame.getContentPane().add(closingTimeLabel);
		
		JButton submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		});
		submitButton.setBounds(633, 13, 117, 35);
		assessmentFrame.getContentPane().add(submitButton);
		
		JPanel questionsPanel = new JPanel();
		questionsPanel.setBounds(12, 62, 738, 388);
		questionsPanel.setLayout(new GridLayout(4, 0, 0, 0));
		assessmentFrame.getContentPane().add(questionsPanel);
		
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(null);
		questionsPanel.add(questionPanel);
		
		JLabel label_1 = new JLabel("Q1. Why is the sky blue?");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(12, 0, 714, 29);
		questionPanel.add(label_1);
		
		JRadioButton answer1Button = new JRadioButton("Yes");
		answer1Button.setBounds(8, 35, 350, 25);
		questionPanel.add(answer1Button);
		
		JRadioButton answer2Button = new JRadioButton("Maybe");
		answer2Button.setBounds(362, 38, 350, 25);
		questionPanel.add(answer2Button);
		
		JRadioButton answer3Button = new JRadioButton("No");
		answer3Button.setBounds(8, 65, 350, 25);
		questionPanel.add(answer3Button);
		
		JRadioButton answer4Button = new JRadioButton("None of the above");
		answer4Button.setBounds(362, 65, 350, 25);
		questionPanel.add(answer4Button);
		
		JPanel horizontalLine = new JPanel();
		horizontalLine.setBackground(Color.DARK_GRAY);
		horizontalLine.setBounds(0, 0, 738, 2);
		questionPanel.add(horizontalLine);
		
		ButtonGroup answers = new ButtonGroup();
	}
}

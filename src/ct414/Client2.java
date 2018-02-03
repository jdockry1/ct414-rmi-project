package ct414;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Client2 {

	private JFrame listAssessmentsFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client2 window = new Client2();
					window.listAssessmentsFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listAssessmentsFrame = new JFrame();
		listAssessmentsFrame.setBounds(100, 100, 454, 508);
		listAssessmentsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listAssessmentsFrame.getContentPane().setLayout(null);

		
		JLabel messageLabel = new JLabel("No available assessments");
		messageLabel.setBounds(134, 250, 156, 16);
		listAssessmentsFrame.getContentPane().add(messageLabel);
		
		JLabel assessmentsLabel = new JLabel("Assessments");
		assessmentsLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		assessmentsLabel.setBounds(134, 13, 171, 41);
		listAssessmentsFrame.getContentPane().add(assessmentsLabel);
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(12, 67, 412, 381);
		listAssessmentsFrame.getContentPane().add(listPanel);
		listPanel.setLayout(new GridLayout(10, 2, 5, 5));
		
		JButton assessmentButton = new JButton("Assessment 1");
		listPanel.add(assessmentButton);
	}
}

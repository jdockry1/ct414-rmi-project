package ct414;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {

	private JFrame frame;
	private JTextField idField;
	private JTextField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 285, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		loginLabel.setBounds(85, 20, 121, 34);
		frame.getContentPane().add(loginLabel);
		
		idField = new JTextField();
		idField.setBounds(25, 67, 213, 34);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		loginButton.setBounds(80, 161, 97, 25);
		frame.getContentPane().add(loginButton);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(25, 114, 213, 34);
		frame.getContentPane().add(passField);
		
		JLabel messageLabel = new JLabel("<html>Enter your student ID and password <br/>to login</html>");
		messageLabel.setBounds(25, 196, 213, 44);
		frame.getContentPane().add(messageLabel);
	}
}

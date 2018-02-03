package ct414;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ExamClient {
	
	private static ExamServer examServer;
	
	private int accessToken;
	private int studentID;
	private MCQAssessment assessment;
	
	// Login GUI
	private JFrame loginFrame;
	private JTextField idField;
	private JTextField passField;
	// List Assessments GUI
	private JFrame listAssessmentsFrame;
	// Assessment GUI
	private JFrame assessmentFrame;
	
	private ExamClient() {
		LoginGUI();
	}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            ExamServer stub = (ExamServer) registry.lookup("ExamServer");
            
            examServer = stub;
            ExamClient examClient = new ExamClient();
            
            System.out.println("Client Connected");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    private void LoginGUI() {
    	loginFrame = new JFrame();
    	loginFrame.setBounds(100, 100, 285, 300);
    	loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	loginFrame.getContentPane().setLayout(null);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		loginLabel.setBounds(85, 20, 121, 34);
		loginFrame.getContentPane().add(loginLabel);
		
		final JLabel messageLabel = new JLabel("<html>Enter your student ID and password <br/>to login</html>");
		messageLabel.setBounds(25, 196, 213, 44);
		loginFrame.getContentPane().add(messageLabel);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(25, 67, 213, 34);
		loginFrame.getContentPane().add(idField);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(25, 114, 213, 34);
		loginFrame.getContentPane().add(passField);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					accessToken = examServer.login(Integer.parseInt(idField.getText()), passField.getText());
					studentID = Integer.parseInt(idField.getText());
					loginFrame.setVisible(false);
					ListAssessmentsGUI();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
					messageLabel.setText("<html>The id entered is invalid</html>");
				} catch (UnauthorizedAccess e) {
					messageLabel.setText(e.getReason());
				}
			}
		});
		loginButton.setBounds(80, 161, 97, 25);
		loginFrame.getContentPane().add(loginButton);
		
		loginFrame.setVisible(true);
	}
    
    private void ListAssessmentsGUI() {
		listAssessmentsFrame = new JFrame();
		listAssessmentsFrame.setBounds(100, 100, 454, 508);
		listAssessmentsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listAssessmentsFrame.getContentPane().setLayout(null);
		
		JLabel assessmentsLabel = new JLabel("Assessments");
		assessmentsLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		assessmentsLabel.setBounds(134, 13, 171, 41);
		listAssessmentsFrame.getContentPane().add(assessmentsLabel);
		
		List<String> summaries = new ArrayList<String>();
		
		try {
			summaries = examServer.getAvailableSummary(accessToken, studentID);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (UnauthorizedAccess e) {
			e.printStackTrace();
		} catch (NoMatchingAssessment e) {
			JLabel messageLabel = new JLabel("No available assessments");
			messageLabel.setBounds(134, 250, 156, 16);
			listAssessmentsFrame.getContentPane().add(messageLabel);
		}
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(12, 67, 412, 381);
		listAssessmentsFrame.getContentPane().add(listPanel);
		listPanel.setLayout(new GridLayout(10, 2, 5, 5));
		
		for(String summary : summaries) {
			final JButton assessmentButton = new JButton(summary);
			assessmentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String courseCode = assessmentButton.getText().substring(assessmentButton.getText().indexOf(':') + 2);
					courseCode = courseCode.substring(0, courseCode.indexOf(' '));
					try {
						assessment = (MCQAssessment) examServer.getAssessment(accessToken, studentID, courseCode);
						AssessmentGUI();
						listAssessmentsFrame.setVisible(false);
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (UnauthorizedAccess e) {
						e.printStackTrace();
					} catch (NoMatchingAssessment e) {
						e.printStackTrace();
					}
				}
			});
			listPanel.add(assessmentButton);
		}
		
		listAssessmentsFrame.setVisible(true);
	}
    
    public void AssessmentGUI() {
    	assessmentFrame = new JFrame();
		assessmentFrame.setBounds(100, 100, 780, 510);
		assessmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		assessmentFrame.getContentPane().setLayout(null);
		
		JLabel assessmentLabel = new JLabel(assessment.getTitle());
		assessmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		assessmentLabel.setBounds(12, 13, 149, 36);
		assessmentFrame.getContentPane().add(assessmentLabel);
		
		JLabel closingTimeLabel = new JLabel("Closing Time: " + assessment.getClosingDate());
		closingTimeLabel.setBounds(173, 27, 133, 16);
		assessmentFrame.getContentPane().add(closingTimeLabel);
		
		JButton submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					examServer.submitAssessment(accessToken, studentID, assessment);
					assessmentFrame.setVisible(false);
					ListAssessmentsGUI();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (UnauthorizedAccess e) {
					e.printStackTrace();
				} catch (NoMatchingAssessment e) {
					e.printStackTrace();
				}
			}
		});
		submitButton.setBounds(633, 13, 117, 35);
		assessmentFrame.getContentPane().add(submitButton);
		
		JPanel questionsPanel = new JPanel();
		questionsPanel.setBounds(12, 62, 738, 388);
		questionsPanel.setLayout(new GridLayout(4, 0, 0, 0));
		assessmentFrame.getContentPane().add(questionsPanel);

		for(int i = 0; i < 4; i++) {
			try {
				final MCQQuestion question = (MCQQuestion) assessment.getQuestion(i);
				final int j = i;
				
				JPanel questionPanel = new JPanel();
				questionPanel.setLayout(null);
				questionsPanel.add(questionPanel);
				
				JLabel questionLabel = new JLabel("Q" + question.getQuestionNumber() + ". " + question.getQuestionDetail());
				questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				questionLabel.setBounds(12, 0, 714, 29);
				questionPanel.add(questionLabel);
				
				ButtonGroup answers = new ButtonGroup();
				
				JRadioButton[] answerButtons = new JRadioButton[4];
				
				answerButtons[0] = new JRadioButton(question.getAnswerOptions()[0]);
				answerButtons[0].setBounds(8, 35, 350, 25);
				answerButtons[0].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							assessment.selectAnswer(j, 0);
						} catch (InvalidQuestionNumber e) {
							e.printStackTrace();
						} catch (InvalidOptionNumber e) {
							e.printStackTrace();
						}
					}
				});
				questionPanel.add(answerButtons[0]);
				answers.add(answerButtons[0]);
				
				answerButtons[1] = new JRadioButton(question.getAnswerOptions()[1]);
				answerButtons[1].setBounds(362, 38, 350, 25);
				answerButtons[1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							assessment.selectAnswer(j, 1);
						} catch (InvalidQuestionNumber e) {
							e.printStackTrace();
						} catch (InvalidOptionNumber e) {
							e.printStackTrace();
						}
					}
				});
				questionPanel.add(answerButtons[1]);
				answers.add(answerButtons[1]);
				
				answerButtons[2] = new JRadioButton(question.getAnswerOptions()[2]);
				answerButtons[2].setBounds(8, 65, 350, 25);
				answerButtons[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							assessment.selectAnswer(j, 2);
						} catch (InvalidQuestionNumber e) {
							e.printStackTrace();
						} catch (InvalidOptionNumber e) {
							e.printStackTrace();
						}
					}
				});
				questionPanel.add(answerButtons[2]);
				answers.add(answerButtons[2]);
				
				answerButtons[3] = new JRadioButton(question.getAnswerOptions()[3]);
				answerButtons[3].setBounds(362, 65, 350, 25);
				answerButtons[3].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							assessment.selectAnswer(j, 3);
						} catch (InvalidQuestionNumber e) {
							e.printStackTrace();
						} catch (InvalidOptionNumber e) {
							e.printStackTrace();
						}
					}
				});
				questionPanel.add(answerButtons[3]);
				answers.add(answerButtons[3]);
				
				if(question.getSelectedAnswer() != -1) {
					answerButtons[question.getSelectedAnswer()].setSelected(true);
				}
				
				JPanel horizontalLine = new JPanel();
				horizontalLine.setBackground(Color.DARK_GRAY);
				horizontalLine.setBounds(0, 0, 738, 2);
				questionPanel.add(horizontalLine);
			} catch (InvalidQuestionNumber e) {
				e.printStackTrace();
			}
		}
		
		assessmentFrame.setVisible(true);
    }
}

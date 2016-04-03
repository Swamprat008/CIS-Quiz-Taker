package automationFramework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserName;
	private JPasswordField passwordField;
	private JTextField documentName;
	private JTextField txtQz;
	WebDriver driver;
	Thread threadMain;
	TextArea textArea;
	static GUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setTitle("CIS Web Automation");
					String userName = System.getProperty("user.name");
					// setting path to chrome driver
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\"+userName+"\\Documents\\chromedriver.exe");

					frame.setVisible(true);

					// controller.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		UserName = new JTextField();
		UserName.setForeground(Color.BLACK);
		UserName.setBounds(72, 109, 146, 20);
		contentPane.add(UserName);
		UserName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(273, 109, 146, 20);
		contentPane.add(passwordField);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(72, 84, 146, 16);
		contentPane.add(lblUserName);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(273, 84, 146, 16);
		contentPane.add(lblNewLabel);

		JLabel lblCisWebPractice = new JLabel("CIS WEB Quiz Automation");
		lblCisWebPractice.setFont(new Font("Dialog", Font.BOLD, 17));
		lblCisWebPractice.setForeground(Color.BLACK);
		lblCisWebPractice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCisWebPractice.setBounds(72, 30, 347, 16);
		contentPane.add(lblCisWebPractice);

		documentName = new JTextField();
		documentName.setText("Quiz");
		documentName.setForeground(Color.BLACK);
		documentName.setHorizontalAlignment(SwingConstants.CENTER);
		documentName.setBounds(72, 199, 146, 20);
		contentPane.add(documentName);
		documentName.setColumns(10);

		JLabel lblSaveDocumentName = new JLabel("Save Document Name");
		lblSaveDocumentName.setForeground(Color.BLACK);
		lblSaveDocumentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveDocumentName.setBounds(72, 171, 146, 16);
		contentPane.add(lblSaveDocumentName);

		textArea = new TextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBounds(10, 322, 476, 241);
		contentPane.add(textArea);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// get username and password
				textArea.setText("");
				setTextArea("CIS WEB Practice Quiz Automation Started");
				String userName = UserName.getText();
				char[] input = passwordField.getPassword();
				String passwrd = new String(input);
				String quizName = txtQz.getText();
				String fileName = documentName.getText();

				File file = new File("C:\\Users\\Andrew\\Documents\\" + fileName + ".txt");

				if (!file.isFile()) {
					setTextArea("Missing file, please run CIS Web Practice Quiz Automation");
					driver.close();
				}

				// Create a new instance of the Chrome driver
				setTextArea("Starting Chrome Browser");
				driver = new ChromeDriver();

				// Create new instance of Controller class
				Controller controller = new Controller(driver, fileName, frame, quizName);

				threadMain = new Thread() {
					public void run() {
						setTextArea("Logging you in");
						controller.login(userName, passwrd);
					}
				};
				threadMain.start();

			}
		});
		startButton.setBackground(SystemColor.textHighlight);
		startButton.setForeground(Color.BLACK);
		startButton.setBounds(72, 266, 146, 26);
		contentPane.add(startButton);

		txtQz = new JTextField();
		txtQz.setForeground(Color.BLACK);
		txtQz.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtQz.setHorizontalAlignment(SwingConstants.CENTER);
		txtQz.setText("qz2");
		txtQz.setBounds(273, 199, 146, 20);
		contentPane.add(txtQz);
		txtQz.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Quiz HTML Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(273, 171, 146, 16);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Stop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				threadMain.interrupt();
				driver.close();

			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(273, 266, 146, 26);
		contentPane.add(btnNewButton);
	}

	public void setTextArea(String words) {
		textArea.append("\n" + words);
	}
}

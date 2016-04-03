package automationFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class Controller {
	
	WebDriver driver;
	String fileName;
	GUI gui;
	String quizName;

	
	public Controller(WebDriver driver, String fileName, GUI gui, String quizName){
		this.driver = driver;
		this.fileName = fileName;
		this.gui = gui;
		this.quizName = quizName;
		
	}
	
	public void login(String username, String passwrd) {

		// create new instance of login class
		Login login = new Login(driver);

		// if login is successful, continue
		if (login.WebLogin(username, passwrd)) {
			Quiz();
		} else {
			gui.setTextArea("Unable to login, please check username and password, then try again.");
		}
	}

	public void Quiz() {

		FindQuiz findquiz = new FindQuiz(driver);

		// if it found the quiz, continue
		if (findquiz.QuizPick(quizName)) {
			QuizFiller();
		}
	}
	
	public void QuizFiller() {
		QuizFill qf = new QuizFill(driver);
		RandomNumberGenerator randNum = new RandomNumberGenerator();
		gui.setTextArea("Answering questions, using text file.");
		try {

			for (;;) {
				int speed = randNum.RandomInteger() * 1000;
				gui.setTextArea("Acting like a human, answering question in " + speed / 1000 + " seconds");
				String answer = qf.QuizQA(fileName);
				
				if (answer == null) {
					gui.setTextArea("Answer missing from text file. Waiting 1.5 minutes for user to answer");
					try {
						Thread.sleep(90000);
						QuizFiller();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if (qf.QuizAFill(answer, speed)) {
					continue;
				} else {
					gui.setTextArea("Done!!");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

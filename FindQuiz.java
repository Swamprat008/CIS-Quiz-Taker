package automationFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindQuiz {

	WebDriver driver;

	public FindQuiz(WebDriver driver) {

		this.driver = driver;
	}

	public boolean QuizPick(String quizName) {

		try {

			WebElement quizLink = driver.findElement(By.name("quizzes"));
			quizLink.click();

			List<WebElement> pickQuiz = this.driver.findElements(By.name(quizName));
			pickQuiz.get(1).click();
			
			WebElement CheckBox = driver.findElement(By.name("agree"));
			CheckBox.click();
			
			WebElement startLink = driver.findElement(By.cssSelector("input[value='start the quiz.']"));
			startLink.click();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}

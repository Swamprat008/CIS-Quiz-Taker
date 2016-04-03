package automationFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	WebDriver driver;

	public Login(WebDriver driver) {

		this.driver = driver;
	}

	public boolean WebLogin(String usernm, String passwrd) {

		try {
			System.out.println(usernm + " " + " " +  passwrd);
			// setting webpage
			driver.get("http://cisweb.bristolcc.edu/~ik/cgi/bin/login.cgi?course=c260c");

			// Print a Log In message to the screen
			System.out.println("Successfully opened the website cisweb");

			// Logging in using username and password
			WebElement username = driver.findElement(By.name("login"));
			username.sendKeys(usernm);

			WebElement password = driver.findElement(By.name("passw"));
			password.sendKeys(passwrd);

			WebElement button = driver.findElement(By.name("hash"));
			button.submit();
			
			List<WebElement> incorrectLogin = driver.findElements(By.tagName("h1"));
			
			if(incorrectLogin.size() > 0){
				if(incorrectLogin.get(0).getText().equals("Incorrect login or password")){
					System.out.println("Wrong");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}

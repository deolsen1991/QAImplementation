package webimplementation;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.UUID;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Daniel Olsen
 */
public class WebImplementation {

    public static void main(String[] args) {
        //Using chrome of this test, so have to use seleniums chrome drivers.
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //initialize the driver and set the url
        WebDriver driver = new ChromeDriver();
        driver.get("http://demoqa.com/registration/");
        //loop 100 times, can bet set to anything or to loop indefinitely
        int x = 0;
        while (x < 100) {
            //get the elements that contain the first name, and last name text boxes and put a uuid in them
            WebElement firstName = driver.findElement(By.id("name_3_firstname"));
            //clears are here to ensure that some specific functionality doesn't break and to clear the boxes after every test
            firstName.clear();
            firstName.sendKeys(randomText());
            WebElement lastName = driver.findElement(By.id("name_3_lastname"));
            lastName.clear();
            lastName.sendKeys(randomText());
            //clicks a random checkbox based on the which number is generated, sometimes a box will not be checked, but this does not impact the testing
            Random rand = new Random();
            int n = rand.nextInt(3) + 1;
            System.out.println(x);
            switch (n) {
                case 1: {
                    WebElement checkbox = driver.findElement(By.xpath("//input[@name='checkbox_5[]' and @value='dance']"));
                    checkbox.click();
                }
                case 2: {
                    WebElement checkbox = driver.findElement(By.xpath("//input[@name='checkbox_5[]' and @value='reading']"));
                    checkbox.click();
                }
                case 3: {
                    WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[3]"));
                    checkbox.click();

                }
                default:
                    break;
            }
            //same thing as first name and last name
            WebElement userName = driver.findElement(By.id("username"));
            userName.clear();
            userName.sendKeys(randomText());
            //for passwords they must match to complete registeration, so we store the uuid in a string to be used twice
            String temp = randomText();
            WebElement password = driver.findElement(By.id("password_2"));
            password.clear();
            password.sendKeys(temp);
            WebElement passwordConfirm = driver.findElement(By.id("confirm_password_password_2"));
            passwordConfirm.clear();
            passwordConfirm.sendKeys(temp);
            WebElement email = driver.findElement(By.id("email_1"));
            email.clear();
            email.sendKeys(randomText() + "@test.com");
            WebElement phoneNumber = driver.findElement(By.id("phone_9"));
            phoneNumber.clear();
            phoneNumber.sendKeys(phoneGen());
            new Select(driver.findElement(By.id("dropdown_7"))).selectByVisibleText("United States");
            new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText("3");
            new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText("20");
            new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText("1992");
            WebElement description = driver.findElement(By.id("description"));
            description.clear();
            description.sendKeys(randomText());
            driver.findElement(By.name("pie_submit")).click();
            x++;
        }
        driver.close();

    }

    private static String randomText() {
        return UUID.randomUUID().toString();
    }

    private static String phoneGen() {
        while (true) {
            long numb = (long) (Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 10) {
                return String.valueOf(numb);
            }
        }
    }

}

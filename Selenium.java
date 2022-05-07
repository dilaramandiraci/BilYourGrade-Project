
// Importing All Necessary Items
import java.io.*;
import java.lang.Thread;

import javax.lang.model.element.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Selenium {

    public static void main(String[] args) {

        // Instantiate a SafariDriver class.
        WebDriver driver = new SafariDriver();
        driver.get("https://stars.bilkent.edu.tr/homepage/courses.php?DEPT=CS");
        int rowCount = driver.findElements(By.xpath("//*[@id='courseList']/child::*/child::tr")).size();
        for (int i = 1; i <= rowCount; i++) {
            int columnCount = driver
                    .findElements(By.xpath("//*[@id='courseList']/child::*/child::tr[" + i + "]/child::td")).size();
            for (int j = 1; j <= columnCount; j++) {
                WebElement element = driver.findElement(
                        By.xpath("//*[@id='courseList']/child::*/child::tr[" + i + "]/child::td[" + j + "]"));
                System.out.println(element.getText() + "  ");
                System.out.println("hi");
            }
            System.out.println();
        }
        driver.quit();
    }
}
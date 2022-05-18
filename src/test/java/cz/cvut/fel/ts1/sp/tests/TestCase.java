package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TestCase {

    private static WebDriver driver;
    private MainPage mainPage;

    @BeforeAll
    public static void init() {
        try {
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            driver = new ChromeDriver();
            Dimension dimension = new Dimension(1920, 1080);
            driver.manage().window().setSize(dimension);
            driver.get("http://localhost:8080/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void gotoMainPage(){
        driver.get("http://localhost:8080/");
    }

    @AfterAll
    public static void clean() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
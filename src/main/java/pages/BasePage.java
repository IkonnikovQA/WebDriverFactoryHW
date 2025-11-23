package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void clickElement(WebElement element) {
        waitForElement(element);
        element.click();
        logger.debug("Выполнен клик по элементу: {}", element);
    }

    protected void sendKeys(WebElement element, String text) {
        waitForElement(element);
        element.clear();
        element.sendKeys(text);
        logger.debug("Введен текст '{}' в элемент: {}", text, element);
    }
}


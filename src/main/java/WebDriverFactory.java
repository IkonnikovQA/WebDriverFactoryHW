import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static WebDriver create(String webDriverName) {
        return create(webDriverName, null);
    }

    public static WebDriver create(String webDriverName, Object options) {
        logger.info("Создание WebDriver для браузера: {}", webDriverName);

        BrowserType browserType = BrowserType.fromString(webDriverName);
        if (browserType == null) {
            throw new IllegalArgumentException("Неподдерживаемый браузер: " + webDriverName);
        }

        switch (browserType) {
            case CHROME:
                return createChromeDriver(options);
            case FIREFOX:
                return createFirefoxDriver(options);
            case EDGE:
                return createEdgeDriver(options);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + webDriverName);
        }
    }

    private static WebDriver createChromeDriver(Object options) {
        WebDriverManager.chromedriver().setup();
        if (options != null && options instanceof ChromeOptions) {
            logger.info("Использование предоставленных ChromeOptions");
            return new ChromeDriver((ChromeOptions) options);
        }
        return new ChromeDriver();
    }

    private static WebDriver createFirefoxDriver(Object options) {
        WebDriverManager.firefoxdriver().setup();
        if (options != null && options instanceof FirefoxOptions) {
            logger.info("Использование предоставленных FirefoxOptions");
            return new FirefoxDriver((FirefoxOptions) options);
        }
        return new FirefoxDriver();
    }

    private static WebDriver createEdgeDriver(Object options) {
        WebDriverManager.edgedriver().setup();
        if (options != null && options instanceof EdgeOptions) {
            logger.info("Использование предоставленных EdgeOptions");
            return new EdgeDriver((EdgeOptions) options);
        }
        return new EdgeDriver();
    }
}

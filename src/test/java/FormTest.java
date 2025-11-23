import pages.FormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FormTest {
    private static final Logger logger = LoggerFactory.getLogger(FormTest.class);
    private WebDriver driver;
    private FormPage formPage;
    private static final String FORM_URL = "https://otus.home.kartushin.su/form.html";

    @BeforeEach
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        logger.info("Запуск теста с браузером: {}", browserName);
        
        driver = WebDriverFactory.create(browserName);
        driver.manage().window().maximize();
        driver.get(FORM_URL);
        
        formPage = new FormPage(driver);
        logger.info("Переход на страницу формы: {}", FORM_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver закрыт");
        }
    }

    @Test
    public void testFormFillingAndSubmission() {
        AuthConfig.AuthCredentials credentials = AuthConfig.getAuthCredentials();
        
        String email = System.getProperty("email", "test@example.com");
        String confirmPassword = System.getProperty("confirmPassword", credentials.getPassword());
        String birthDate = System.getProperty("birthDate", "1990-01-01");
        String languageLevel = System.getProperty("languageLevel", "Intermediate");

        logger.info("Данные теста - Имя пользователя: {}, Email: {}, Дата рождения: {}, Уровень языка: {}",
                    credentials.getUsername(), email, birthDate, languageLevel);

        formPage.fillAllFields(credentials.getUsername(), email, credentials.getPassword(),
                              confirmPassword, birthDate, languageLevel);

        assertTrue(formPage.isPasswordMatch(), "Пароли должны совпадать");

        formPage.submitForm();

        String resultText = formPage.getResultText();
        assertNotNull(resultText, "Результат не должен быть null");
        assertFalse(resultText.isEmpty(), "Результат не должен быть пустым");

        assertTrue(resultText.contains(credentials.getUsername()), "Результат должен содержать имя пользователя");
        assertTrue(resultText.contains(email), "Результат должен содержать email");
        
        logger.info("Тест успешно завершен. Результат: {}", resultText);
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(FormPage.class);

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='confirm_password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@id='birthdate']")
    private WebElement birthDateField;

    @FindBy(xpath = "//select[@id='language_level']")
    private WebElement languageLevelField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='output']")
    private WebElement resultDiv;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsername(String username) {
        logger.info("Заполнение поля имени пользователя: {}", username);
        if (usernameField != null) {
            sendKeys(usernameField, username);
        } else {
            logger.warn("Поле имени пользователя не найдено на странице");
        }
    }

    public void fillEmail(String email) {
        logger.info("Заполнение поля email: {}", email);
        if (emailField != null) {
            sendKeys(emailField, email);
        } else {
            logger.warn("Поле email не найдено на странице");
        }
    }

    public void fillPassword(String password) {
        logger.info("Заполнение поля пароля");
        if (passwordField != null) {
            sendKeys(passwordField, password);
        } else {
            logger.warn("Поле пароля не найдено на странице");
        }
    }

    public void fillConfirmPassword(String password) {
        logger.info("Заполнение поля подтверждения пароля");
        if (confirmPasswordField != null) {
            confirmPasswordField.sendKeys(password);
            logger.info("Подтверждение пароля введено");
        } else {
            logger.warn("Поле подтверждения пароля не найдено на странице");
        }
    }

    public void fillBirthDate(String birthDate) {
        logger.info("Заполнение поля даты рождения");
        if (birthDateField != null) {
            birthDateField.sendKeys(birthDate);
            logger.info("Дата рождения введена: {}", birthDate);
        } else {
            logger.warn("Поле даты рождения не найдено на странице");
        }
    }

    public void selectLanguageLevel(String level) {
        logger.info("Выбор уровня знания языка: {}", level);
        if (languageLevelField != null) {
            waitForElement(languageLevelField);
            Select select = new Select(languageLevelField);
            try {
                select.selectByVisibleText(level);
                logger.info("Выбран уровень: {}", level);
            }
            catch (Exception e) {
                try {
                    select.selectByVisibleText("Средний");
                    logger.info("Выбран уровень: Средний");
                } catch (Exception e2) {
                    select.selectByIndex(2);
                    logger.info("Выбран уровень языка по индексу 2");
                }
            }
        } else {
            logger.warn("Поле выбора уровня знания языка не найдено на странице");
        }
    }

    public void submitForm() {
        logger.info("Отправка формы");
        if (submitButton != null) {
            clickElement(submitButton);
        } else {
            logger.warn("Кнопка отправки формы не найдена на странице");
        }
    }

    public String getResultText() {
        if (resultDiv != null) {
            waitForElement(resultDiv);
            String text = resultDiv.getText();
            logger.info("Текст результата: {}", text);
            return text;
        } else {
            logger.warn("Элемент результата не найден на странице");
            return "";
        }
    }

    public boolean isPasswordMatch() {
        logger.info("Проверка совпадения пароля и подтверждения");
        String passwordValue = passwordField.getAttribute("value");
        String confirmPasswordValue = confirmPasswordField.getAttribute("value");
        boolean passwordsMatch = passwordValue.equals(confirmPasswordValue);
        logger.info("Проверка паролей пройдена: пароли совпадают");
        return passwordsMatch;
    }

    public void fillAllFields(String username, String email, String password,
                              String confirmPassword, String birthDate, String languageLevel) {
        fillUsername(username);
        fillEmail(email);
        fillPassword(password);
        fillConfirmPassword(confirmPassword);
        if (birthDateField != null) {
            fillBirthDate(birthDate);
        }
        if (languageLevelField != null) {
            selectLanguageLevel(languageLevel);
        }
    }
}


package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;

// создаем драйвер
public RegistrationPage(WebDriver driver) {
     this.driver = driver;
    }
//    поле Имя на странице регистрации
    private By nameFieldForRegistration = By.xpath(".//div[label[text()='Имя']]//input");;

//    поле Email на странице регистрации
    private By emailFieldForRegistration= By.xpath(".//div[label[text()='Email']]//input");

//    поле Password на странице рагистрации
    private By passwordFieldForRegistration= By.xpath(".//div[label[text()='Пароль']]//input");

//    сообщение о некорректном пароле
    private By errorPassword =By.xpath("//p[text()='Некорректный пароль']");

//    кнопка Зарегистрироваться
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

//    кнопка Войти на страница регистрации
private By loginButtonRegistrationPage = By.xpath(".//a[text()='Войти']");

    @Step("Скролл вниз и нажимаем кнопку Войти на странице регистрации")
    public void clickLoginButtonRegistrationPage(){
       WebElement webLoginButton = driver.findElement(loginButtonRegistrationPage);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webLoginButton);

        driver.findElement(loginButtonRegistrationPage).click();
    }

    @Step("Заполняем поле Имя")
    public void setNameFieldForRegistration(){
        driver.findElement(nameFieldForRegistration).sendKeys("Nina");
    }

    @Step("Заполняем поле Email")
    public void setEmailFieldForRegistration(){
        String email = "Nina" + System.currentTimeMillis() + "@test.com";
        driver.findElement(emailFieldForRegistration).sendKeys(email);
    }

    @Step("Заполняем поле Пароль")
    public void setPasswordFieldForRegistration(){
        driver.findElement(passwordFieldForRegistration).sendKeys("1234567");
    }

    @Step("Заполняем поле Пароль коротким паролем")
    public void setShortPasswordFieldForRegistration(){
        driver.findElement(passwordFieldForRegistration).sendKeys("1234");
    }

    @Step("Нажимаем кнопку Зарегистрироваться")
    public void clickRegistrationButton(){
        driver.findElement(registrationButton).click();
    }

    @Step("Проверка видимости текста об ошибке Некорретный пароль")
    public boolean isErrorPasswordMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorPassword));
        boolean isErrorPasswordMessageVisible =  driver.findElement(errorPassword).isDisplayed();
        return isErrorPasswordMessageVisible;
    }
}

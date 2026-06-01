package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    // создаем драйвер
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //    поле для email на странице авторизации
    private By emailField = By.xpath("//input[contains(@class, 'input__textfield') and @name='name']");
    //    поле для password на странице авторизации
    private By passwordField = By.xpath("//input[contains(@class, 'input__textfield') and @name='Пароль']");
    //    заголовок Вход на странице авторизации
    private By loginTitle =By.xpath("//h2[text()='Вход']");

    //    кнопка Войти на странице авторизации
    private By loginButtonLoginPage = By.xpath(".//button[text()='Войти']");

    //    кнопка Регистрация на странице авторизации
    private By registrationButtonLoginPage = By.xpath("//a[contains(@class, 'Auth_link') and text()='Зарегистрироваться']");

    //    кнопка Восстановить пароль
    private By passwordRecoveryButton = By.xpath("//a[text()='Восстановить пароль']");

    @Step("Нажимаем в поле почты и вводим почту")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Нажимаем в поле пароля и вводим пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем кнопку Войти")
    public void clickLoginButton(){
        driver.findElement(loginButtonLoginPage).click();
    }

    @Step("Нажимаем кнопку Регистрация")
    public void clickRegistrationButtonLoginPage(){
        driver.findElement(registrationButtonLoginPage).click();
    }

    @Step("Скролл до Восстановить пароль и нажатие")
    public void clickPasswordRecoveryButton(){
        WebElement webPasswordRecovery = driver.findElement(passwordRecoveryButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webPasswordRecovery);

        driver.findElement(passwordRecoveryButton).click();
    }

    @Step("Проверка видимости заголовка  Вход")
    public boolean isLoginTitleVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        boolean isLoginTitleVisible =  driver.findElement(loginTitle).isDisplayed();
        return isLoginTitleVisible;
    }
}

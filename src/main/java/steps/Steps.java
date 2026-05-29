package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.*;

public class Steps {
    WebDriver driver;
    MainPage mainPage; // добавляем экземпляр MainPage
    LoginPage loginPage;
    RegistrationPage registrationPage;
    DashboardPage dashboardPage;
    PasswordRecoveryPage passwordRecoveryPage;



    public Steps(WebDriver driver, MainPage mainPage, LoginPage loginPage,
                 DashboardPage dashboardPage, RegistrationPage registrationPage, PasswordRecoveryPage passwordRecoveryPage) {
        this.driver = driver;
        this.mainPage = mainPage;
        this.loginPage = loginPage;
        this.dashboardPage = dashboardPage;
        this.registrationPage = registrationPage;
        this.passwordRecoveryPage = passwordRecoveryPage;
    }

    //    шаг для открытия сайта
    public void startOfWork() {
        mainPage.openSite();
    }

    @Step("Заполняем форму авторизации")
    public void fillEmailAndPassword(){
        loginPage.setEmail("test-data-SuperNina8@Yandex.ru");
        loginPage.setPassword("password");
        loginPage.clickLoginButton();
    }

    @Step("Заполняем форму регистрации")
    public void fillRegistrationForm(){
        registrationPage.setNameFieldForRegistration();
        registrationPage.setEmailFieldForRegistration();
        registrationPage.setPasswordFieldForRegistration();
    }

    @Step("Заполняем форму авторизации с коротким паролем")
    public void fillRegistrationFormWithShortPassword(){
        registrationPage.setNameFieldForRegistration();
        registrationPage.setEmailFieldForRegistration();
        registrationPage.setShortPasswordFieldForRegistration();
    }
}
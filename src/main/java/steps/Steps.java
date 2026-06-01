package steps;

import io.qameta.allure.Step;
import model.RegistrationData;
import model.TestUserData;
import org.openqa.selenium.WebDriver;
import pages.*;


public class Steps {
    private RegistrationData registrationData;

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


    @Step("Генерация случайных данных для регистрации (валидность пароля: {isPasswordValid})")
    public void generateRegistrationData(boolean isPasswordValid) {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();

        // Создаем уникальную почту
        String email = faker.name().firstName().toLowerCase() + System.currentTimeMillis() + "@yandex.ru";
        // Если флаг true — пароль длинный, если false — короткий (невалидный)
        String password = isPasswordValid ? faker.internet().password(6, 12) : faker.internet().password(3, 5);
        String name = faker.name().firstName();

        registrationData = new RegistrationData(email, password, name);
    }

    @Step("Заполняем форму регистрации сгенерированными данными")
    public RegistrationData  fillRegistrationFormWithGeneratedData() {
        registrationPage.setNameFieldForRegistration(registrationData.getName());
        registrationPage.setEmailFieldForRegistration(registrationData.getEmail());
        registrationPage.setPasswordFieldForRegistration(registrationData.getPassword());
        return registrationData;
    }


    @Step("Заполняем форму авторизации")
    public void fillEmailAndPassword(TestUserData testUser){
        loginPage.setEmail(testUser.getEmail());
        loginPage.setPassword(testUser.getPassword());
        loginPage.clickLoginButton();
    }

    public model.RegistrationData getRegistrationData() {
        return this.registrationData;
    }
}
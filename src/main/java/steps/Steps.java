package steps;

import api.UserHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.Map;

public class Steps {
    private Map<String, String> currentUserData;
    // Карта для хранения сгенерированных данных пользователя для UI
    private java.util.Map<String, String> registrationData;

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

    @Step("Создаем пользователя через API")
    public Map<String, String> createTestUserViaApi() {
        currentUserData = UserHelper.createUserAndGetToken();
        return currentUserData;
    }

    @Step("Удаляем пользователя через API")
    public void deleteTestUserViaApi() {
        if (currentUserData != null && currentUserData.get("accessToken") != null) {
            UserHelper.deleteUser(currentUserData.get("accessToken"));
        }
    }

    @Step("Генерация случайных данных для регистрации (валидность пароля: {isPasswordValid})")
    public void generateRegistrationData(boolean isPasswordValid) {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
        registrationData = new java.util.HashMap<>();

        // Создаем уникальную почту
        String email = faker.name().firstName().toLowerCase() + System.currentTimeMillis() + "@yandex.ru";
        // Если флаг true — пароль длинный, если false — короткий (невалидный)
        String password = isPasswordValid ? faker.internet().password(6, 12) : faker.internet().password(3, 5);
        String name = faker.name().firstName();

        registrationData.put("email", email);
        registrationData.put("password", password);
        registrationData.put("name", name);
    }

    @Step("Заполняем форму регистрации сгенерированными данными")
    public void fillRegistrationFormWithGeneratedData() {
        // Передаем строки в методы
        registrationPage.setNameFieldForRegistration(registrationData.get("name"));
        registrationPage.setEmailFieldForRegistration(registrationData.get("email"));
        registrationPage.setPasswordFieldForRegistration(registrationData.get("password"));
    }

    @Step("Удаляем пользователя, созданного через UI, используя API")
    public void deleteUiCreatedUserViaApi() {
        if (registrationData == null) return;

        try {
            // Логин через API под теми данными, которые только что ввели на UI
            String token = io.restassured.RestAssured.given()
                    .contentType(io.restassured.http.ContentType.JSON)
                    .baseUri("https://education-services.ru")
                    .body(registrationData) // отправляем карту с почтой и паролем
                    .post("/api/auth/login")
                    .path("accessToken");

            // Если токен получен  — удаляем пользователя
            if (token != null) {
                UserHelper.deleteUser(token);
            }
            // Игнорируем ошибку, если регистрация не состоялась (негативный тест)
        } catch (Exception e) {
        }
    }

    @Step("Заполняем форму авторизации")
    public void fillEmailAndPassword(){
        loginPage.setEmail(currentUserData.get("email"));
        loginPage.setPassword(currentUserData.get("password"));
        loginPage.clickLoginButton();
    }
}
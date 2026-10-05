import api.UserHelper;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.TestUserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTests extends BaseUITest {
    private TestUserData testUser;

    @Before
    public void setUpUser() {
        testUser = UserHelper.createUserAndGetToken();
        }

    @After
    public void tearDownUser() {
        if (testUser != null && testUser.getAccessToken() != null) {
            UserHelper.deleteUser(testUser.getAccessToken());
        }
    }

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт")
    @Description("Проверка, что можно войти через кнопку Войти в аккаунт на главной странице")
    public void LoginButtonOnMainPageTest() {
        steps.startOfWork();
        mainPage.clickLoginButton();
        steps.fillEmailAndPassword(testUser);

        assertTrue("Кнопки 'Оформить заказ' не видно", dashboardPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    @Description("Проверка, что можно войти через кнопку личный кабинет на главной странице")
    public void ProfileButtonOnMainPage() {
        steps.startOfWork();
        mainPage.clickProfileButton();
        steps.fillEmailAndPassword(testUser);

        assertTrue("Кнопки 'Оформить заказ' не видно", dashboardPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка, что можно войти через кнопку в форме регистрации")
    public void registrationButtonLoginPageTest(){
        steps.startOfWork();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationButtonLoginPage();
        registrationPage.clickLoginButtonRegistrationPage();
        steps.fillEmailAndPassword(testUser);

        assertTrue("Кнопки 'Оформить заказ' не видно", dashboardPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка, что можно войти через кнопку в форме восстановления пароля")
    public void  loginViaPasswordRecoveryButtonTest(){
        steps.startOfWork();
        mainPage.clickLoginButton();
        loginPage.clickPasswordRecoveryButton();
        passwordRecoveryPage.clickLoginLinkPasswordRecoveryPage();
        steps.fillEmailAndPassword(testUser);

        assertTrue("Кнопки 'Оформить заказ' не видно", dashboardPage.isCreateOrderButtonVisible());
    }
    }


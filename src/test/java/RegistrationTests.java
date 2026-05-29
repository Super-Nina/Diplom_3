import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseUITest{
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка, что можно зарегистрироваться с валидными данными")
    public void successfulRegistrationTest(){
        mainPage.openRegPage();
        steps.fillRegistrationForm();
        registrationPage.clickRegistrationButton();

        assertTrue("Заголовка  'Вход' не видно", loginPage.isLoginTitleVisible());
    }

    @Test
    @DisplayName("Ошибка при невалидном пароле")
    @Description("Проверка, что нельзя зарегистрироваться с паролем менее 6 символов")
    public void rejectRegistrationWithShortPassword() {
        mainPage.openRegPage();
        steps.fillRegistrationFormWithShortPassword();
        registrationPage.clickRegistrationButton();

        assertTrue("Сообщения о некорректном пароле не видно", registrationPage.isErrorPasswordMessageVisible());
    }

}

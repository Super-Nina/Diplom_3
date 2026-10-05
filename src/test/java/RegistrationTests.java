import api.UserHelper;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseUITest{
//    Удаление пользователя, созданного через API
    @After
    public void tearDownUser() {
        UserHelper.deleteUiCreatedUser(steps.getRegistrationData());
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка, что можно зарегистрироваться с валидными данными")
    public void successfulRegistrationTest(){
    // Генерируем валидные случайные данные
        steps.generateRegistrationData(true);

        mainPage.openRegPage();
    // Заполняем форму случайными данными
        steps.fillRegistrationFormWithGeneratedData();
        registrationPage.clickRegistrationButton();

        assertTrue("Заголовка  'Вход' не видно", loginPage.isLoginTitleVisible());
    }

    @Test
    @DisplayName("Ошибка при невалидном пароле")
    @Description("Проверка, что нельзя зарегистрироваться с паролем менее 6 символов")
    public void rejectRegistrationWithShortPassword() {
    // Генерируем невалидные данные (пароль от 3 до 5 символов)
        steps.generateRegistrationData(false);
        mainPage.openRegPage();
    // Заполняем форму
        steps.fillRegistrationFormWithGeneratedData();
        registrationPage.clickRegistrationButton();

        assertTrue("Сообщения о некорректном пароле не видно", registrationPage.isErrorPasswordMessageVisible());
    }

}

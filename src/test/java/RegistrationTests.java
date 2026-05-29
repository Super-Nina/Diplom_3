import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class AuthTests extends BaseUITest{
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка, что можно зарегистрироваться с валидными данными")
    public void successfulRegistrationTest(){
        mainPage.openRegPage();
        registrationPage.setNameFieldForRegistration();
        registrationPage.setEmailFieldForRegistration();
        registrationPage.setPasswordFieldForRegistration();

        registrationPage.clickRegistrationButton();
//        registrationPage.clickLoginButtonRegistrationPage();



    }

}

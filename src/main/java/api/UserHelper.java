package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.github.javafaker.Faker;
import model.CreateUserResponse;
import model.RegistrationData;
import model.TestUserData;
import model.UserModel;
import org.apache.http.HttpStatus;


public class UserHelper {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Создание пользователя через Faker (через API)")
    public static TestUserData createUserAndGetToken() {
        Faker faker = new Faker();

        // Генерируем случайные валидные данные
        String email = faker.name().firstName().toLowerCase() + System.currentTimeMillis() + "@yandex.ru";
        String password = faker.internet().password(6, 12);
        String name = faker.name().firstName();

        UserModel userRequestBody = new UserModel(email, password, name);

        // Отправляем запрос на регистрацию и десериализуем ответ в объект CreateUserResponse
        CreateUserResponse responseBody = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(userRequestBody)
                .post("/api/auth/register")
                .then()
                .statusCode(HttpStatus.SC_OK) // 🌟 КРИТИЧЕСКАЯ ПРОВЕРКА: Тест упадет здесь, если код не 200
                .extract()
                .as(CreateUserResponse.class);

        // Получаем токен из десериализованного объекта
        String accessToken = responseBody.getAccessToken();

        return new TestUserData(email, password, accessToken);
    }

    @Step("Удаление пользователя по токену (через API)")
    public static void deleteUser(String accessToken) {
        if (accessToken == null) return;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED); // проверка успешного удаления Пользователя
    }
    @Step("Логин пользователя через API для получения токена")
    public static String loginUserAndGetToken(RegistrationData registrationData) {
        UserModel loginRequestBody = new UserModel(
                registrationData.getEmail(),
                registrationData.getPassword(),
                null
        );

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(loginRequestBody)
                .post("/api/auth/login")
                .then()
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользователя, созданного через UI")
    public static void deleteUiCreatedUser(RegistrationData regData) {
        if (regData == null) return;
        try {
            // Вызываем метод логина, который объявлен чуть выше
            String token = loginUserAndGetToken(regData);
            if (token != null) {
                deleteUser(token);
            }
        } catch (Exception ignored) {
            // Игнорируем ошибку, если пользователь не зарегистрировался в негативном тесте
        }
    }
}


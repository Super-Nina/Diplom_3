package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

public class UserHelper {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Создание пользователя через Faker (через API)")
    public static Map<String, String> createUserAndGetToken() {
        Faker faker = new Faker();

        // Генерируем случайные валидные данные
        String email = faker.name().firstName().toLowerCase() + System.currentTimeMillis() + "@yandex.ru";
        String password = faker.internet().password(6, 12);
        String name = faker.name().firstName();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("name", name);

        // Отправляем запрос на регистрацию
        String accessToken = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(requestBody)
                .post("/api/auth/register")
                .then()
                .statusCode(200) // 🌟 КРИТИЧЕСКАЯ ПРОВЕРКА: Тест упадет здесь, если код не 200
                .extract()
                .path("accessToken");


        // Сохраняем всё в одну карту, чтобы передать
        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("accessToken", accessToken);

        return userData;
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
                .statusCode(202); // проверка успешного удаления Пользователя
    }
}


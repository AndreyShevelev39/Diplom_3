package ru.yandex.praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.config.Endpoints;
import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Создать пользователя через API")
    public Response createUser(User user) {
        return given().header("Content-type", "application/json")
                .body(user).post(Endpoints.BASE_URL + "/api/auth/register");
    }

    @Step("Удалить пользователя через API")
    public void deleteUser(String token) {
        if (token != null) {
            given().header("Authorization", token)
                    .delete(Endpoints.BASE_URL + "/api/auth/user");
        }
    }
}
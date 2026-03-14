package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.config.Endpoints;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка перехода на страницу логина после успешной регистрации")
    public void successfulRegistrationTest() {
        driver.get(Endpoints.REGISTER);
        RegisterPage registerPage = new RegisterPage(driver);
        String email = "ivan_" + System.currentTimeMillis() + "@yandex.ru";

        registerPage.register("Ivan", email, "password123");

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue("Не отображается кнопка входа после регистрации",
                loginPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации: короткий пароль")
    @Description("Проверка появления ошибки при вводе пароля меньше 6 символов")
    public void shortPasswordErrorTest() {
        driver.get(Endpoints.REGISTER);
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.register("Ivan", "test@ya.ru", "123");

        Assert.assertTrue("Ошибка некорректного пароля не появилась",
                registerPage.isPasswordErrorDisplayed());
    }
}
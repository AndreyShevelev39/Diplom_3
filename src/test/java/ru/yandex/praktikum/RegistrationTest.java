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
    public void successRegTest() {
        driver.get(Endpoints.REGISTER);
        new RegisterPage(driver).register("Ivan", "ivan_" + System.currentTimeMillis() + "@ya.ru", "password123");
        Assert.assertTrue(new LoginPage(driver).isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Ошибка: короткий пароль")
    @Description("Проверка появления ошибки при вводе пароля меньше 6 символов")
    public void shortPassRegTest() {
        driver.get(Endpoints.REGISTER);
        RegisterPage regPage = new RegisterPage(driver);
        regPage.register("Ivan", "test@ya.ru", "123");
        Assert.assertTrue(regPage.isPasswordErrorDisplayed());
    }
}
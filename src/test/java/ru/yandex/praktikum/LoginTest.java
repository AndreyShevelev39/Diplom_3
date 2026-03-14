package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.config.Endpoints;
import ru.yandex.praktikum.pageobject.*;

public class LoginTest extends BaseTest {
    private String email;
    private final String password = "password123";

    @Before
    public void prepareUser() {
        email = "user_" + System.currentTimeMillis() + "@ya.ru";
        driver.get(Endpoints.REGISTER);
        new RegisterPage(driver).register("Tester", email, password);
    }

    @Test
    @DisplayName("Вход через кнопку на главной")
    @Description("Успешная авторизация через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPage() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        new LoginPage(driver).login(email, password);
        Assert.assertTrue("Login failed", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    public void loginFromAccountButton() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinet();
        new LoginPage(driver).login(email, password);
        Assert.assertTrue("Login failed", mainPage.isUserLoggedIn());
    }
}
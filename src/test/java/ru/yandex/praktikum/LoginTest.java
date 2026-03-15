package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.clients.UserClient;
import ru.yandex.praktikum.config.Endpoints;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.pageobject.*;

public class LoginTest extends BaseTest {

    private User user;
    private String token;
    private UserClient userClient;

    @Before
    public void prepare() {
        userClient = new UserClient();
        user = new User("qa_tester_" + System.currentTimeMillis() + "@yandex.ru", "password123", "Andrey");

        var response = userClient.createUser(user);
        token = response.path("accessToken");
    }

    @After
    public void cleanup() {
        if (token != null) {
            userClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверка успешного входа через кнопку входа на главной странице")
    public void loginFromMainPage() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Вход не выполнен", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку перехода в личный кабинет в хедере")
    public void loginFromAccountButton() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickPersonalCabinet();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Вход не выполнен", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода к логину и входа со страницы регистрации")
    public void loginFromRegisterPage() {
        driver.get(Endpoints.REGISTER);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Вход не выполнен", new MainPage(driver).isUserLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода к логину и входа со страницы восстановления пароля")
    public void loginFromForgotPasswordPage() {
        driver.get(Endpoints.FORGOT_PASSWORD);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Вход не выполнен", new MainPage(driver).isUserLoggedIn());
    }
}
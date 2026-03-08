package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

public class RegistrationTest extends BaseTest {
    @Test
    public void successfulRegistration() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        String email = "ivan_" + System.currentTimeMillis() + "@yandex.ru";
        registerPage.register("Ivan", email, "password123");
        Assert.assertTrue("Page not redirected to login", new LoginPage(driver).isLoginButtonDisplayed());
    }

    @Test
    public void errorForShortPassword() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Ivan", "test@ya.ru", "123");
        Assert.assertTrue("Error message not displayed", registerPage.isPasswordErrorDisplayed());
    }
}
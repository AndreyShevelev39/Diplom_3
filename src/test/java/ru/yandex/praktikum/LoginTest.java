package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageobject.*;
import java.time.Duration;

public class LoginTest extends BaseTest {
    private String email;
    private final String password = "password123";

    @Before
    public void prepareUser() {
        email = "test_user_" + System.currentTimeMillis() + "@yandex.ru";
        driver.get("https://stellarburgers.education-services.ru/register");
        new RegisterPage(driver).register("Tester", email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));
    }

    @Test
    public void loginFromMainPage() {
        driver.get("https://stellarburgers.education-services.ru/");
        new MainPage(driver).clickLoginButton();
        new LoginPage(driver).login(email, password);
        boolean isLogged = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
        Assert.assertTrue("Login failed from main page", isLogged);
    }

    @Test
    public void loginFromPersonalAccount() {
        driver.get("https://stellarburgers.education-services.ru/");
        new MainPage(driver).clickPersonalCabinet();
        new LoginPage(driver).login(email, password);
        boolean isLogged = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
        Assert.assertTrue("Login failed from personal account", isLogged);
    }

    @Test
    public void loginFromRegisterPage() {
        driver.get("https://stellarburgers.education-services.ru/register");
        new RegisterPage(driver).clickLoginLink();
        new LoginPage(driver).login(email, password);
        boolean isLogged = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
        Assert.assertTrue("Login failed from register page", isLogged);
    }

    @Test
    public void loginFromForgotPasswordPage() {
        driver.get("https://stellarburgers.education-services.ru/forgot-password");
        new ForgotPasswordPage(driver).clickLoginLink();
        new LoginPage(driver).login(email, password);
        boolean isLogged = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
        Assert.assertTrue("Login failed from forgot password page", isLogged);
    }
}
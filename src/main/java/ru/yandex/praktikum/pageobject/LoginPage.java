package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailInput = By.name("name");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[contains(@class, 'button_button_type_primary')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Логин пользователем: {email}")
    public void login(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Проверить, отображается ли кнопка входа")
    public boolean isLoginButtonDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}
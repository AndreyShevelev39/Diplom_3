package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final By loginLink = By.xpath(".//a[@href='/login']");
    private final By passwordError = By.xpath(".//p[contains(@class, 'input__error')]");

    public RegisterPage(WebDriver driver) { this.driver = driver; }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() { driver.findElement(loginLink).click(); }

    @Step("Регистрация: {name}, {email}")
    public void register(String name, String email, String password) {
        driver.findElement(By.xpath("(.//input)[1]")).sendKeys(name);
        driver.findElement(By.xpath("(.//input)[2]")).sendKeys(email);
        driver.findElement(By.xpath(".//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath(".//button[contains(@class, 'button_button_type_primary')]")).click();
    }

    @Step("Проверить ошибку пароля")
    public boolean isPasswordErrorDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
}
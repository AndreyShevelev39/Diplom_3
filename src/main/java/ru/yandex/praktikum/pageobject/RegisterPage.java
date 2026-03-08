package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("(.//input)[1]");
    private final By emailInput = By.xpath("(.//input)[2]");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By registerButton = By.xpath(".//button[contains(@class, 'button_button_type_primary')]");
    private final By loginLink = By.xpath(".//a[@href='/login']");
    private final By passwordError = By.xpath(".//p[contains(@class, 'input__error')]");

    public RegisterPage(WebDriver driver) { this.driver = driver; }

    public void register(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registerButton).click();
    }
    public void clickLoginLink() { driver.findElement(loginLink).click(); }
    public boolean isPasswordErrorDisplayed() { return driver.findElements(passwordError).size() > 0; }
}
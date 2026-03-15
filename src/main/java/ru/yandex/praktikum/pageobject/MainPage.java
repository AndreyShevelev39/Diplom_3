package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[contains(@class, 'button_button_type_primary')]");
    private final By personalCabinetButton = By.xpath(".//a[@href='/account']");
    private final By checkoutButton = By.xpath(".//button[contains(text(),'\u041e\u0444\u043e\u0440\u043c\u0438\u0442\u044c')]");
    private final By constructorHeader = By.tagName("h1");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Дождаться загрузки главной")
    public void waitForLoad() { wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader)); }

    @Step("Нажать 'Войти в аккаунт'")
    public void clickLoginButton() { wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click(); }

    @Step("Нажать 'Личный Кабинет'")
    public void clickPersonalCabinet() { wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click(); }

    @Step("Перейти к разделу конструктора {index}")
    public void clickTab(int index) {
        driver.findElement(By.xpath("(//div[contains(@class, 'tab_tab')])[" + index + "]")).click();
    }

    @Step("Проверить активность таба {index}")
    public boolean isTabActive(int index) {
        By locator = By.xpath("(//div[contains(@class, 'tab_tab')])[" + index + "]");
        return wait.until(ExpectedConditions.attributeContains(locator, "class", "tab_tab_type_current"));
    }

    @Step("Проверить авторизацию")
    public boolean isUserLoggedIn() {
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}
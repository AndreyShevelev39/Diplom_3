package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Дождаться загрузки конструктора")
    public void waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }

    @Step("Нажать кнопку входа")
    public void clickLoginButton() { wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click(); }

    @Step("Нажать 'Личный Кабинет'")
    public void clickPersonalCabinet() { wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click(); }

    @Step("Перейти к разделу 'Соусы'")
    public void clickSauceTab() {
        driver.findElement(By.xpath("(//div[contains(@class, 'tab_tab')])[2]")).click();
    }

    @Step("Перейти к разделу 'Начинки'")
    public void clickFillingTab() {
        driver.findElement(By.xpath("(//div[contains(@class, 'tab_tab')])[3]")).click();
    }

    @Step("Перейти к разделу 'Булки'")
    public void clickBunTab() {
        driver.findElement(By.xpath("(//div[contains(@class, 'tab_tab')])[1]")).click();
    }

    @Step("Проверить активность таба по индексу {index}")
    public boolean isTabSelectedByIndex(int index) {
        By tabLocator = By.xpath("(//div[contains(@class, 'tab_tab')])[" + index + "]");

        try {
            return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/")).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
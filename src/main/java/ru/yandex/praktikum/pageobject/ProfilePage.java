package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By logoutButton = By.xpath(".//button[text()='\u0412\u044b\u0445\u043e\u0434']");

    private final By constructorLink = By.xpath(".//p[text()='\u041a\u043e\u043d\u0441\u0442\u0440\u0443\u043a\u0442\u043e\u0440']");

    private final By logo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");

    private final By profileText = By.xpath(".//p[contains(@class, 'Account_text')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать на кнопку 'Выход'")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажать на ссылку 'Конструктор'")
    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Проверить видимость данных профиля")
    public boolean isProfileInfoVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(profileText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
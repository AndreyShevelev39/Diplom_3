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
    private final By profileInfoText = By.xpath(".//p[contains(@class, 'Account_text')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Проверить отображение текста в профиле")
    public boolean isProfileVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileInfoText)).isDisplayed();
    }
}
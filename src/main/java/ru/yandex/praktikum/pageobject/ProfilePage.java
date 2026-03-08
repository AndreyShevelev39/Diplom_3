package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    private final By logoutButton = By.xpath(".//button[text()='\u0412\u044b\u0445\u043e\u0434']");
    private final By profileText = By.xpath(".//p[contains(@class, 'Account_text')]");

    public ProfilePage(WebDriver driver) { this.driver = driver; }

    public void clickLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public boolean isProfileInfoDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileText)).isDisplayed();
    }
}
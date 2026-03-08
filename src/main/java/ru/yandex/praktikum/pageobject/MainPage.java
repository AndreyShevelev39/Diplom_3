package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[contains(text(),'\u0412\u043e\u0439\u0442\u0438')]");
    private final By personalCabinetButton = By.xpath(".//a[@href='/account']");
    private final By checkoutButton = By.xpath(".//button[contains(text(),'\u041e\u0444\u043e\u0440\u043c\u0438\u0442\u044c')]");
    private final By bunTab = By.xpath(".//span[text()='\u0411\u0443\u043b\u043a\u0438']/..");
    private final By sauceTab = By.xpath(".//span[text()='\u0421\u043e\u0443\u0441\u044b']/..");
    private final By fillingTab = By.xpath(".//span[text()='\u041d\u0430\u0447\u0438\u043d\u043a\u0438']/..");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginButton() { wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click(); }
    public void clickPersonalCabinet() { wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click(); }
    public void clickSauceTab() { driver.findElement(sauceTab).click(); }
    public void clickFillingTab() { driver.findElement(fillingTab).click(); }
    public void clickBunTab() { driver.findElement(bunTab).click(); }
    public boolean isCheckoutButtonDisplayed() {
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).isDisplayed(); }
        catch (Exception e) { return false; }
    }
    public boolean isTabSelected(String tabName) {
        return driver.findElements(By.xpath("//div[contains(@class,'tab_tab_type_current')]//span[text()='" + tabName + "']")).size() > 0;
    }
}
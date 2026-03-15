package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.config.Endpoints;
import ru.yandex.praktikum.pageobject.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Раздел Соусы")
    public void switchToSauceTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(2);
        Assert.assertTrue(mainPage.isTabActive(2));
    }

    @Test
    @DisplayName("Раздел Начинки")
    public void switchToFillingTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(3);
        Assert.assertTrue(mainPage.isTabActive(3));
    }

    @Test
    @DisplayName("Раздел Булки")
    public void switchToBunTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(2);
        mainPage.clickTab(1);
        Assert.assertTrue(mainPage.isTabActive(1));
    }
}
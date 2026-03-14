package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.config.Endpoints;
import ru.yandex.praktikum.pageobject.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void switchToSauceTest() {
        driver.get(Endpoints.BASE_URL); // Гарантируем, что мы на главной
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickSauceTab();
        Assert.assertTrue("Раздел Соусы (индекс 2) не активен", mainPage.isTabSelectedByIndex(2));
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void switchToFillingTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickFillingTab();
        Assert.assertTrue("Раздел Начинки (индекс 3) не активен", mainPage.isTabSelectedByIndex(3));
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    public void switchToBunTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        // Сначала кликаем на Соусы (2), чтобы уйти с дефолтных Булок
        mainPage.clickSauceTab();
        // Возвращаемся на Булки (1)
        mainPage.clickBunTab();
        Assert.assertTrue("Раздел Булки (индекс 1) не активен", mainPage.isTabSelectedByIndex(1));
    }
}
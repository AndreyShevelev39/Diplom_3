package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.pageobject.MainPage;

public class ConstructorTest extends BaseTest {
    @Test
    public void constructorTabsSwitching() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        Assert.assertTrue("Sauce tab not selected", mainPage.isTabSelected("\u0421\u043e\u0443\u0441\u044b"));
        mainPage.clickFillingTab();
        Assert.assertTrue("Filling tab not selected", mainPage.isTabSelected("\u041d\u0430\u0447\u0438\u043d\u043a\u0438"));
        mainPage.clickBunTab();
        Assert.assertTrue("Bun tab not selected", mainPage.isTabSelected("\u0411\u0443\u043b\u043a\u0438"));
    }
}
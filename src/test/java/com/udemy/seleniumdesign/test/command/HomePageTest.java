package com.udemy.seleniumdesign.test.command;

import com.udemy.seleniumdesign.command.ElementValidator;
import com.udemy.seleniumdesign.command.HomePage;
import com.udemy.seleniumdesign.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeTest
    public void setHomePage() {
        this.homePage = new HomePage(driver);
    }

    @Test
    public void homePageTest() {
        this.homePage.goTo();
        this.homePage.getElementValidators()
                .stream()
                .parallel() //for performance improvement -> multiple threads validation in parallel
                .map(ev -> ev.validate())
                .forEach(b -> Assert.assertTrue(b));


//        for(ElementValidator ev: homePage.getElementValidators()){
//            boolean result = ev.validate();
//            Assert.assertTrue(result);
//        }
    }

    //same with data provider
    @Test(dataProvider = "getData", dependsOnMethods = "goTo")
    public void homePageTest2(ElementValidator elementValidator) {
        this.homePage.goTo();
        Assert.assertTrue(elementValidator.validate());
    }

    @DataProvider
    public Object[] getData() {
        return this.homePage.getElementValidators()
                .toArray();
    }
}

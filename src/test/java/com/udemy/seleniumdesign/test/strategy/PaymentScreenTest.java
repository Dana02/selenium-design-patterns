package com.udemy.seleniumdesign.test.strategy;

import com.udemy.seleniumdesign.strategy.PaymentOptionFactory;
import com.udemy.seleniumdesign.strategy.PaymentScreen;
import com.udemy.seleniumdesign.test.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;

public class PaymentScreenTest extends BaseTest {

    private PaymentScreen paymentScreen;

    @BeforeTest
    public void setPaymentScreen(){
        this.paymentScreen = new PaymentScreen(this.driver);
    }

    @Test(dataProvider = "getData")
    public void paymentTest(String option, Map<String, String> paymentDetails){
        this.paymentScreen.goTo();
        this.paymentScreen.getUserInformation().enterDetails("vinoth", "selvaraj", "udemy@gmail.com");
        this.paymentScreen.setPaymentOption(PaymentOptionFactory.get(option));
        this.paymentScreen.pay(paymentDetails);
        String orderNumber = this.paymentScreen.getOrder().placeOrder();

        System.out.println("Order Number : " + orderNumber);
    }

    @DataProvider
    public Object[][] getData(){

        Map<String, String> creditCard = Maps.newHashMap();
        creditCard.put("cc", "1231231231");
        creditCard.put("year", "2023");
        creditCard.put("cvv", "123");

        Map<String, String> netBanking = Maps.newHashMap();
        netBanking.put("bank", "WELLS FARGO");
        netBanking.put("account", "myaccount123");
        netBanking.put("pin", "999");

        return new Object[][]{
                {"CC", creditCard} ,
                {"NB", netBanking}
        };
    }

}

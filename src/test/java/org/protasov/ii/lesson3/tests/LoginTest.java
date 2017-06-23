package org.protasov.ii.lesson3.tests;

import org.protasov.ii.pageobjects.LoginPanel;
import org.protasov.ii.testbase.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "dataFromMethod", dataProviderClass = DataProviders.class)
    public void validLoginTest(String login, String password){

        Reporter.log("=====validLoginTest started=====", true);

            loadPage("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertTrue(LoginPanel.init().
                openLoginPanel().
                registerUser(login, password).
                pressEnterToLogin().isProfileNameVisible());
    }

    @Test(dataProvider = "dataFromCSV", dataProviderClass = DataProviders.class)
    public  void invalidLoginTest(String login, String password){

        Reporter.log("=====invalidLoginTest started=====", true);

        loadPage("https://jdi-framework.github.io/tests/index.htm");
        Assert.assertTrue(LoginPanel.init().
                openLoginPanel().
                registerUser(login, password).
                pressEnterToLogin().
                isLoginFailureMessageVisible());
    }

    @Test(enabled = false)
    public void grammarCheck(){

        Reporter.log("=====grammarTest started=====", true);

        loadPage("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertEquals(LoginPanel.init().openLoginPanel().
                registerUser("Иван", "1234").
                pressEnterToLogin().
                getFailureMessageText(), "* Login Failed");
    }

}

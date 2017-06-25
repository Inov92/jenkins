package org.protasov.ii.lesson3.tests;

import org.protasov.ii.pageobjects.LoginPanel;
import org.protasov.ii.testbase.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("This is test suit for checking authentication functionality")
public class LoginTest extends BaseTest {

    @Title("Positive login test")
    @Description("User try log in with correct login and password")
    @Severity(SeverityLevel.BLOCKER)
    @Step("=====validLoginTest started=====")
    @Test(dataProvider = "dataFromMethod", dataProviderClass = DataProviders.class)
    public void validLoginTest(String login, String password){

            loadPage("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertTrue(LoginPanel.init().
                openLoginPanel().
                registerUser(login, password).
                pressEnterToLogin().isProfileNameVisible());
    }
    @Title("Negative login test")
    @Description("User try log in with incorrect login and password")
    @Severity(SeverityLevel.CRITICAL)
    @Step("=====invalidLoginTest started=====")
    @Test(dataProvider = "dataFromCSV", dataProviderClass = DataProviders.class)
    public  void invalidLoginTest(String login, String password){

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

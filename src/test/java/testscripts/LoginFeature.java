package testscripts;

import org.testng.annotations.DataProvider;
import testutils.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LoginFeature extends TestBase {

    @Test (dataProvider = "TestDataProvider")
    public void SigninWithValidCredentials(String emailId,String password)
    {
        launchWebsite().login(emailId,password);
    }

    @Test (dataProvider = "TestDataProvider")
    public void SigninWithInvalidCredentials(String emailId,String password)
    {
        launchWebsite().invalidLogin(emailId,password);
    }
}

package testscripts;

import foundation.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LoginFeature extends TestBase {

    @Test
    public void SigninWithValidCredentials()
    {
        launchWebsite().login("lokeshg.mca@gmail.com","Password123");
    }

    @Test
    public void SigninWithInvalidCredentials()
    {
        launchWebsite().invalidLogin("lokeshg.mca1@gmail.com","Password123");
    }
}

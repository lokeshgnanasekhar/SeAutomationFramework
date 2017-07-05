package testscripts;

import testutils.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LocationFeature extends TestBase {
    @Test (dataProvider = "TestDataProvider")
    public void selectCarryoutStore(String emailid,String password,String zipcode)
    {
        launchWebsite().login(emailid,password)
                       .editLocation()
                       .selectCarryout(zipcode);
    }

    @Test (dataProvider = "TestDataProvider")
    public void selectDeliveryStore(String emailid,String password,String street,String zipcode)
    {
        launchWebsite().login(emailid,password)
                .editLocation()
                .selectDelivery(street,zipcode);
    }
}

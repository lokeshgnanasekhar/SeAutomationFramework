package testscripts;

import foundation.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LocationFeature extends TestBase {
    @Test
    public void selectCarryoutStore()
    {
        launchWebsite().login("lokeshg.mca@gmail.com","Password123")
                       .editLocation()
                       .selectCarryout("40220");
    }
}

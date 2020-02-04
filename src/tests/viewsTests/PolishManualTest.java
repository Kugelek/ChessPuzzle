package tests.viewsTests;

import org.junit.Assert;
import org.junit.Test;
import views.ManualFactory;
import views.UserManual;

public class PolishManualTest {
    ManualFactory fac = new ManualFactory();
    UserManual manu;

    @Test
    public void testGetManualText(){
        manu = fac.getManual("PL");
        Assert.assertNotNull(manu.getManualText());
    }
}

package tests.viewsTests;

import org.junit.Assert;
import org.junit.Test;
import views.ManualFactory;
import views.UserManual;

public class EnglishManualTest {
    ManualFactory fac = new ManualFactory();
    UserManual manu;

    @Test
    public void testGetManualText(){
        manu = fac.getManual("ENG");
        Assert.assertNotNull(manu.getManualText());
    }
}

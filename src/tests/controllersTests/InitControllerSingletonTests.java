package tests.controllersTests;

import controllers.InitControllerSingleton;
import org.junit.Assert;
import org.junit.Test;

public class InitControllerSingletonTests {

    @Test
    public void testInitControllerSingleton(){
      Assert.assertNotNull(InitControllerSingleton.getInstance());
    }

}

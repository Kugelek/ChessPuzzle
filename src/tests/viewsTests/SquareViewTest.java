package tests.viewsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import views.SquareView;
import static org.hamcrest.CoreMatchers.*;
import java.awt.*;

public class SquareViewTest {
    public SquareView sqv;

    @Before
    public void init(){
        this.sqv = new SquareView();
    }

    @Test
    public void testPaintSquareNotAttacked(){
        Color color = sqv.getBackground();
        sqv.paintSquareNotAttacked(1,1);
        Assert.assertThat(color, is(not(sqv.getBackground())));
    }

    @Test
    public void testPaintSquareAttacked(){
        Color color = sqv.getBackground();
        sqv.paintSquareAttacked(1,1);
        Assert.assertThat(color, is(not(sqv.getBackground())));
    }
    @Test
    public void testPutPiece(){
        Assert.assertNull(sqv.getIcon());
        sqv.putPiece('K');
        Assert.assertNotNull(sqv.getIcon());
    }
    @Test
    public void testRemovePiece(){
        Assert.assertNull(sqv.getIcon());
        sqv.putPiece('K');
        Assert.assertNotNull(sqv.getIcon());
        sqv.removePiece();
        Assert.assertNull(sqv.getIcon());
    }

}

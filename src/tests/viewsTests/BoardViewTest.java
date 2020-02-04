package tests.viewsTests;

import com.sun.deploy.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import views.BoardView;
import views.SquareView;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class BoardViewTest {
    public BoardView bv;

    @Before
    public void init(){
        bv = new BoardView();
        bv.initializeSquares();
    }

    @Test
    public void testInitializeSquares(){
        bv.initializeSquares();
        Assert.assertNotNull(bv.getSquare(5,5));
    }
    @Test
    public void testSetAllBackgrounds(){
        Color col = bv.getSquare(5,5).getBackground();
        bv.setAllBackgrounds();
        Assert.assertThat(col, is(not(bv.getSquare(5,5).getBackground())));
    }
    @Test
    public void testGetSquare(){
        Assert.assertNotNull(bv.getSquare(5,5));
    }

    @Test
    public void testGetSquares(){
        Assert.assertNotNull(bv.getSquares());
    }

    @Test
    public void testAddAllToPanel(){
        bv.addAllToPanel();
        Assert.assertNotNull(bv.getComponents());
    }

    @Test
    public void testMovePiece(){
        SquareView sqv1 = new SquareView();
        SquareView sqv2 = new SquareView();
        bv.movePiece('K',sqv1, sqv2);
        Assert.assertNotNull(sqv2.getIcon());
        Assert.assertNull(sqv1.getIcon());

    }
}

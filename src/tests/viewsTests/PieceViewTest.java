package tests.viewsTests;

import org.junit.Assert;
import org.junit.Test;
import views.PieceView;

public class PieceViewTest {
    public PieceView pieceView;

    @Test
    public void testPieceView(){
        pieceView = new PieceView("TEST",'S');
        Assert.assertNotNull(pieceView);
    }

    @Test
    public void testGetIconPath(){
        pieceView = new PieceView("TEST",'S');
        Assert.assertNotNull(pieceView.getIconPath());
    }
}

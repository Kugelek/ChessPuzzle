package tests.modelsTests;

import controllers.InitControllerSingleton;
import models.Board;
import models.King;
import models.Square;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SquareTest {
    public Square obj;


    @Before
    public void init(){
        obj = new Square(0,4);
    }



    @Test
    public void testSquare(){
        Assert.assertNotNull(obj);
    }

    @Test
    public void testGetX(){
        Assert.assertEquals(obj.getX(), 0);
    }

    @Test
    public void testGetY(){
        Assert.assertEquals(obj.getY(), 4);
    }

    @Test
    public void testGetCountAttacks(){
        Assert.assertEquals(obj.getCountAttacks(), 3);

        //po zmianie
        obj.updateCountAttacks(2);
        Assert.assertEquals(obj.getCountAttacks(), 1);

        //po głupiej zmianie
        obj.updateCountAttacks(18);
        Assert.assertEquals(obj.getCountAttacks(), -15);
        //po pozornej zmianie
        obj.updateCountAttacks(0);
        Assert.assertEquals(obj.getCountAttacks(), 3);

    }



    @Test
    public void testSetSpecial(){

        Assert.assertFalse(obj.isSpecial());

        obj.setSpecial();
        Assert.assertTrue(obj.isSpecial());
    }

    @Test
    public void testIsSpecial(){
        Assert.assertFalse(obj.isSpecial());
    }

    @Test
    public void testUnsetSpecial(){
        Assert.assertFalse(obj.isSpecial());
        obj.unsetSpecial();
        Assert.assertFalse(obj.isSpecial());

        //po ustawieniu na true
        obj.setSpecial();
        Assert.assertTrue(obj.isSpecial());
        obj.unsetSpecial();
        Assert.assertFalse(obj.isSpecial());
    }

    @Test
    public void testHasPiece(){
        //przed dodaniem
        Assert.assertNull(obj.getPiece());

        //po dodaniu figury
        Board board = new Board();
        obj.putPiece(new King('K',obj,board));
        Assert.assertTrue(obj.hasPiece());

    }
    @Test
    public void testGetPiece(){
        Assert.assertNull(obj.getPiece());


        Board board = new Board();
        obj.putPiece(new King('K',obj,board));
        Assert.assertNotNull(obj.getPiece());
    }
    @Test
    public void testPutPiece(){
        Assert.assertNull(obj.getPiece());

        Board board = new Board();
        obj.putPiece(new King('K',obj,board));

        Assert.assertNotNull(obj.getPiece());
    }

    @Test
    public void testRemovePiece(){
        //poczatkowo
        Assert.assertNull(obj.getPiece());
        //gdy figury nie bylo
        obj.removePiece();
        Assert.assertNull(obj.getPiece());

        //gdy figura byla
        Board board = new Board();
        obj.putPiece(new King('K',obj,board));
        Assert.assertNotNull(obj.getPiece());

        obj.removePiece();
        Assert.assertNull(obj.getPiece());
    }

}

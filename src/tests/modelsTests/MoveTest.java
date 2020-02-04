package tests.modelsTests;

import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveTest {
    public Move move;


    @Before
    public void Move(){
        Square sq1 = new Square(2, 5);
        Square sq2= new Square(3, 4);
        this.move = new Move(sq1,sq2);
    }


    @Test
    public void testFirstContructor(){
        Square sq1 = new Square(2, 5);
        Square sq2= new Square(3, 4);
        this.move = new Move(sq1,sq2);
        Assert.assertNotNull(move);
    }

    @Test
    public void testSecondContructor(){
        Square sq1 = new Square(2, 5);
        Board board = new Board();
        Piece piece = new King('K',sq1, board);
        this.move = new Move(piece, 6,7,2,1);
        Assert.assertNotNull(move);

    }


    @Test
    public void testGetXP(){
        Assert.assertEquals(move.getXP(), 2);

    }
    @Test
    public void testGetYP(){
        Assert.assertEquals(move.getYP(), 5);

    }
    @Test
    public void testGetXN(){
        Assert.assertEquals(move.getXN(), 3);

    }
    @Test
    public void testGetYN(){
        Assert.assertEquals(move.getYN(), 4);

    }

}

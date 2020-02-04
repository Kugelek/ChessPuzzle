package tests.modelsTests.PiecesTests;

import models.Board;
import models.Piece;
import models.Square;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PieceTest {

    public Piece piece;
    public Square square;
    public Board board;
    @Before
    public void init(){
        square = new Square(0,4);
        piece = new Piece('P',square, board);
    }
    @Test
    public void testPiece(){
        Assert.assertNotNull(piece);
    }
    @Test
    public void testGetSymbol(){
        Assert.assertEquals(piece.getSymbol(), 'P');
    }

    @Test
    public void testGetPosition() {
        Assert.assertNotNull(piece.getPosition());

        //po wynullowaniu pozycji
        piece.setPosition(null);
        Assert.assertNull(piece.getPosition());
    }


    @Test
    public void testSetPosition(){
        //przed setem
        Assert.assertEquals(piece.getPosition(),square);

        //po
        Square anotherSquare = new Square(2, 5);
        piece.setPosition(anotherSquare);
        Assert.assertEquals(piece.getPosition(), anotherSquare);
    }

    @Test
    public void testGetAttackedSquares(){

        //powinna istniec ale byc pusta
        Assert.assertNotNull(piece.getAttackedSquares());
        Assert.assertTrue(piece.getAttackedSquares().isEmpty());
    }

}

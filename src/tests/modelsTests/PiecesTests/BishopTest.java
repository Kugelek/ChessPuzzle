package tests.modelsTests.PiecesTests;

import models.Bishop;
import models.Board;
import models.Piece;
import models.Square;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BishopTest {

    public Bishop piece;
    public Square square;
    public Board board;
    @Before
    public void init(){
        square = new Square(0,4);
        piece = new Bishop('B',square, board);
    }

    @Test
    public void testGenerateAttackedSquares(){

        //brak pol na planszy
        Assert.assertTrue(piece.getAttackedSquares().isEmpty());
        piece.getAttackedSquares();
        Assert.assertTrue(piece.getAttackedSquares().isEmpty());


        //pola istnieją
        Board anotherBoard = new Board();
        anotherBoard.fillBoard();
        Square anotherSquare = new Square(6,6);




        Bishop anotherPiece = new Bishop('B', anotherSquare, anotherBoard);
        Assert.assertTrue(anotherPiece.getAttackedSquares().isEmpty());
        anotherPiece.generateAttackedSquares();
        Assert.assertFalse(anotherPiece.getAttackedSquares().isEmpty());



        //kilka testów dla spr reguł atakowanych pól szachowego Gońca

        //powinny byc zawarte
        Square sq1 = new Square(5,5);
        Square sq2 = new Square(2,2);
        //nie istnieja
        Square sq3 = new Square(9,9);
        Square sq4 = new Square(-9,-9);
        //nie powinny byc
        Square sq5 = new Square(7,2);
        Square sq6 = new Square(3,7);
        Square sq7 = new Square(4,1);


        Assert.assertTrue(helpTestMethod(sq1, anotherPiece));
        Assert.assertTrue(helpTestMethod(sq2, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq3, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq4, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq5, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq6, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq7, anotherPiece));
        System.out.println(anotherPiece.getAttackedSquares());
    }
    boolean helpTestMethod(Square sq, Piece piece){
        for(Square attSq : piece.getAttackedSquares()){
            if(attSq.getX() == sq.getX() && attSq.getY() == sq.getY())
                return true;
        }
        return false;
    }

    @Test
    public void testResetAttackedSquares(){
        Board anotherBoard = new Board();
        anotherBoard.fillBoard();
        Square anotherSquare = new Square(6,6);

        Bishop anotherPiece = new Bishop('B', anotherSquare, anotherBoard);
        anotherPiece.generateAttackedSquares();
        Assert.assertFalse(anotherPiece.getAttackedSquares().isEmpty());

        anotherPiece.resetAttackedSquares();
        Assert.assertTrue(anotherPiece.getAttackedSquares().isEmpty());
    }

    @Test
    public void testGetAttackedSquares(){
        Assert.assertTrue(piece.getAttackedSquares().isEmpty());
    }


}

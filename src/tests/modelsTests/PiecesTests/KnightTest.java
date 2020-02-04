package tests.modelsTests.PiecesTests;

import models.Knight;
import models.Board;
import models.Piece;
import models.Square;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnightTest {

    public Knight piece;
    public Square square;
    public Board board;
    @Before
    public void init(){
        square = new Square(0,4);
        piece = new Knight('S',square, board);
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




        Knight anotherPiece = new Knight('S', anotherSquare, anotherBoard);
        Assert.assertTrue(anotherPiece.getAttackedSquares().isEmpty());
        anotherPiece.generateAttackedSquares();
        Assert.assertFalse(anotherPiece.getAttackedSquares().isEmpty());



        //kilka testów dla spr reguł atakowanych pól szachowego Skoczka




        //nie powinny byc
        Square sq3 = new Square(9,9);
        Square sq4 = new Square(-9,-9);
        Square sqr3 = new Square(7, 7);
        Square sqr5 = new Square(7, 5);
        Square sqr6 = new Square(5, 7);
        Square sqr8 = new Square(5,6);
        Square sq5 = new Square(7,2);
        Square sq6 = new Square(3,7);
        Square sq7 = new Square(4,1);


        Square sq8 = new Square(7,8);
        Square sq9 = new Square(8,7);
        Square sq10 = new Square(8,5);
        Square sq11 = new Square(5,8);

//        //powinny byc
        Square sqr1 = new Square(5,4);
        Square sqr2 = new Square(4, 5);

        Square sqr4 = new Square(7, 4);

        Square sqr7 = new Square(4, 7);


        Assert.assertFalse(helpTestMethod(sq3, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq4, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq5, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq6, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq7, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq8, anotherPiece));
        Assert.assertFalse(helpTestMethod(sq9, anotherPiece));

        Assert.assertTrue(helpTestMethod(sqr1, anotherPiece));
        Assert.assertTrue(helpTestMethod(sqr2, anotherPiece));

        Assert.assertFalse(helpTestMethod(sqr3, anotherPiece));
        Assert.assertFalse(helpTestMethod(sqr5, anotherPiece));
        Assert.assertFalse(helpTestMethod(sqr6, anotherPiece));
        Assert.assertFalse(helpTestMethod(sqr8, anotherPiece));

        Assert.assertTrue(helpTestMethod(sqr4, anotherPiece));
        Assert.assertTrue(helpTestMethod(sqr7, anotherPiece));


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

        Knight anotherPiece = new Knight('S', anotherSquare, anotherBoard);
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

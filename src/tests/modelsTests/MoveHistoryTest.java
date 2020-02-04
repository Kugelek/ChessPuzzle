package tests.modelsTests;

import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import views.BoardView;

public class MoveHistoryTest {
    public MoveHistory mh;
    public Board board;
    public BoardView boardView;
    @Before
    public void init(){
        board = new Board();
        boardView = new BoardView();
        this.mh = new MoveHistory(board, boardView);
    }


    @Test
    public void testIsHistoryEmpty(){
        Assert.assertTrue(mh.isHistoryEmpty());
    }
    @Test
    public void testAddMove(){
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);

        Assert.assertTrue(mh.isHistoryEmpty());
        mh.addMove(new Move(piece,2,3,4,5 ));
        Assert.assertFalse(mh.isHistoryEmpty());
    }

    @Test
    public void testIsReturnableListEmpty(){
        Assert.assertTrue(mh.isReturnableListEmpty());
    }

    @Test
    public void testAddReturnableMove(){

        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);

        Assert.assertTrue(mh.isReturnableListEmpty());
        mh.addReturnableMove(new Move(piece,2,3,4,5 ));
        Assert.assertFalse(mh.isReturnableListEmpty());
    }

    @Test
    public void testRemoveFirstReturnableMove(){

        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);

        Assert.assertTrue(mh.isReturnableListEmpty());
        mh.addReturnableMove(new Move(piece,2,3,4,5 ));
        Assert.assertFalse(mh.isReturnableListEmpty());

        //usuwanie
        mh.removeFirstReturnableMove();
        Assert.assertTrue(mh.isReturnableListEmpty());

    }
    @Test
    public void testRemoveLastReturnableMove(){

        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);

        Assert.assertTrue(mh.isReturnableListEmpty());
        mh.addReturnableMove(new Move(piece,2,3,4,5 ));
        Assert.assertFalse(mh.isReturnableListEmpty());

        //usuwanie
        mh.removeLastReturnableMove();
        Assert.assertTrue(mh.isReturnableListEmpty());

    }

    @Test
    public void testGetLastReturnableMove(){

        Assert.assertNull(mh.getLastReturnableMove());

        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);
        mh.addReturnableMove(new Move(piece,2,3,4,5 ));


        Assert.assertNotNull(mh.getLastReturnableMove());
    }

    @Test
    public void testRemoveAllReturnableMoves(){
        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);


        mh.addReturnableMove(new Move(piece,2,3,4,5 ));
        mh.addReturnableMove(new Move(piece,3,4,5,6 ));
        mh.addReturnableMove(new Move(piece,4,5,6,7 ));
        Assert.assertFalse(mh.isReturnableListEmpty());

        //usuwanie
        mh.removeAllReturnableMoves();
        Assert.assertTrue(mh.isReturnableListEmpty());
    }

    @Test
    public void testGetReturnableMoves(){
        Assert.assertTrue(mh.getReturnableMoves().isEmpty());
        Assert.assertNotNull(mh.getReturnableMoves());
    }

    @Test
    public void testAddNewMove(){
        Square sq = new Square(2,3);
        Square sq2 = new Square(4,5);

        Assert.assertTrue(mh.isHistoryEmpty());
        mh.addNewMove(sq, sq2);
        Assert.assertFalse(mh.isHistoryEmpty());
    }

    @Test
    public void testRemoveAllMoves(){
        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);


        mh.addMove(new Move(piece,2,3,4,5 ));
        mh.addMove(new Move(piece,3,4,5,6 ));
        mh.addMove(new Move(piece,4,5,6,7 ));
        Assert.assertFalse(mh.isHistoryEmpty());

        //usuwanie
        mh.removeAllMoves();
        Assert.assertTrue(mh.isHistoryEmpty());
    }

    @Test
    public void testRemoveLastMove(){
        //inicjalizacja
        Square sq = new Square(2,3);
        Piece piece = new King('K', sq, board);

        Assert.assertTrue(mh.isHistoryEmpty());
        mh.addMove(new Move(piece,2,3,4,5 ));
        Assert.assertFalse(mh.isHistoryEmpty());

        //usuwanie
        mh.removeLastMove();
        Assert.assertTrue(mh.isHistoryEmpty());
    }
    @Test
    public void testGetAllMoves(){
        Assert.assertTrue(mh.getAllmoves().isEmpty());
    }

}

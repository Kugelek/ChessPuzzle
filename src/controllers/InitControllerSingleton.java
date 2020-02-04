package controllers;

import models.*;
import views.BoardView;

public class InitControllerSingleton {
    private static InitControllerSingleton instance = new InitControllerSingleton();

    private InitControllerSingleton(){

    }
    public static InitControllerSingleton getInstance(){
        return instance;
    }



    public void init(Board board, BoardView boardViewObject, BoardController bc){
        Piece king = new King('K', board.getBoardSquare(0,5), board);
        Piece queen = new Queen('Q', board.getBoardSquare(0,1),board);
        Piece rook = new Rook('R', board.getBoardSquare(0,2),board);
        Piece bishop = new Bishop('B', board.getBoardSquare(0,3),board);
        Piece knight = new Knight('S', board.getBoardSquare(0,4),board);

        board.getBoardSquare(0,5).putPiece(king);
        board.getBoardSquare(0,1).putPiece(queen);
        board.getBoardSquare(0,2).putPiece(rook);
        board.getBoardSquare(0,3).putPiece(bishop);
        board.getBoardSquare(0,4).putPiece(knight);

        board.addToPieces(king);
        board.addToPieces(queen);
        board.addToPieces(rook);
        board.addToPieces(bishop);
        board.addToPieces(knight);


        boardViewObject.getSquare(0,5).putPiece(king.getSymbol());
        boardViewObject.getSquare(0,1).putPiece(queen.getSymbol());
        boardViewObject.getSquare(0,2).putPiece(rook.getSymbol());
        boardViewObject.getSquare(0,3).putPiece(bishop.getSymbol());
        boardViewObject.getSquare(0,4).putPiece(knight.getSymbol());

        board.getBoardSquare(0,0).setSpecial();
        board.getBoardSquare(7,0).setSpecial();
        board.getBoardSquare(5,3).setSpecial();
        board.getBoardSquare(7,7).setSpecial();


        boardViewObject.addHighlightListenerToAllSquares(bc);
        board.setCountSpecialsAttacked();
        boardViewObject.printSpecials(board.getSpecialSquares());

    }
}

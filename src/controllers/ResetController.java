package controllers;

import models.*;
import views.BoardView;
import views.MoveHistoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResetController extends JButton {


    public ResetController(BoardController bc, Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
        this.setText("RESET");
        this.addActionListener(new ResetListener(bc, board, boardView, mh, area, areaReturnable));

    }

    class ResetListener implements ActionListener{
        BoardController bc;
        Board board;
        BoardView boardViewObject;
        MoveHistory mh;
        JTextArea area;
        JTextArea areaReturnable;

        public ResetListener(BoardController bc, Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
            this.bc = bc;
            this.board = board;
            this.boardViewObject = boardView;
            this.mh = mh;
            this.area = area;
            this.areaReturnable = areaReturnable;
        }


        public void actionPerformed(ActionEvent e){
            boardViewObject.unmarkAllSquares();
            this.cleanBoard();
            System.out.println(this.board.getSpecialSquares());
            this.randomSpecialSquares();
            this.randomPiecesInit();
            this.cleanHistory();


        }
        public void cleanHistory(){
            MoveHistoryView mhv = new MoveHistoryView(this.area, this.areaReturnable);
            this.mh.removeAllReturnableMoves();
            this.mh.removeAllMoves();
            mhv.printReturnableMoves(this.mh);
            mhv.printMoveHistory(this.mh);
        }
        public void randomSpecialSquares(){
            for(int i = 0; i < 4; i++){
                int x = (int)(Math.random() * 8.0);
                int y = (int)(Math.random() * 8.0);
                while(board.getBoardSquare(x,y).isSpecial()){
                    x =(int)(Math.random() * 8.0);
                    y = (int)(Math.random() * 8.0);
                }
                board.getBoardSquare(x,y).setSpecial();
            }

            System.out.println(this.board.getSpecialSquares());
            boardViewObject.printSpecials(board.getSpecialSquares());
        }

        public void randomPiecesInit(){
//
//            int[] xArr = new int[5];
//            int[] yArr = new int[5];
            //empty squares
            int x = (int)(Math.random() * 8);
            int y = (int)(Math.random() * 8);
            ArrayList<Integer> xArr = new ArrayList<Integer>();
            ArrayList<Integer> yArr = new ArrayList<Integer>();
            while(xArr.size() != 5){
                x = (int)(Math.random() * 8);
                y = (int)(Math.random() * 8);
                if(!board.getBoardSquare(x,y).isSpecial() && !board.getBoardSquare(x,y).hasPiece()){
                    xArr.add(x);
                    yArr.add(y);
                }
            }

//           xArr.forEach(el->System.out.println("XD" + el));
//           yArr.forEach(el->System.out.println("YD" + el));
            System.out.println(xArr + " " + yArr);

            Piece king = new King('K', board.getBoardSquare(xArr.get(0),yArr.get(0)), board);
            Piece queen = new Queen('Q', board.getBoardSquare(xArr.get(1), yArr.get(1)),board);
            Piece rook = new Rook('R', board.getBoardSquare(xArr.get(2), yArr.get(2)),board);
            Piece bishop = new Bishop('B', board.getBoardSquare(xArr.get(3),yArr.get(3)),board);
            Piece knight = new Knight('S', board.getBoardSquare(xArr.get(4),yArr.get(4)),board);

            board.getBoardSquare(xArr.get(0),yArr.get(0)).putPiece(king);
            board.getBoardSquare(xArr.get(1),yArr.get(1)).putPiece(queen);
            board.getBoardSquare(xArr.get(2),yArr.get(2)).putPiece(rook);
            board.getBoardSquare(xArr.get(3),yArr.get(3)).putPiece(bishop);
            board.getBoardSquare(xArr.get(4),yArr.get(4)).putPiece(knight);

            board.addToPieces(king);
            board.addToPieces(queen);
            board.addToPieces(rook);
            board.addToPieces(bishop);
            board.addToPieces(knight);


            boardViewObject.getSquare(xArr.get(0),yArr.get(0)).putPiece(king.getSymbol());
            boardViewObject.getSquare(xArr.get(1),yArr.get(1)).putPiece(queen.getSymbol());
            boardViewObject.getSquare(xArr.get(2),yArr.get(2)).putPiece(rook.getSymbol());
            boardViewObject.getSquare(xArr.get(3),yArr.get(3)).putPiece(bishop.getSymbol());
            boardViewObject.getSquare(xArr.get(4),yArr.get(4)).putPiece(knight.getSymbol());
            boardViewObject.addHighlightListenerToAllSquares(bc);
            board.setCountSpecialsAttacked();
            boardViewObject.printSpecials(board.getSpecialSquares());
        }



        public void cleanBoard(){

            //usuwanie pol specjalnych
            this.board.getSpecialSquares().forEach(specSquare -> {
                specSquare.unsetSpecial();
            });
            this.board.getSpecialSquares().removeAll(this.board.getSpecialSquares());
            this.boardViewObject.eraseSpecials();


            //usuwanie figur z modelu planszy
            this.board.getSquares().forEach(square ->{ square.removePiece();
            });
            this.board.getPieces().removeAll(this.board.getPieces());
            System.out.println(this.board.getPieces());


            this.boardViewObject.eraseSquares();
        }
    }

}


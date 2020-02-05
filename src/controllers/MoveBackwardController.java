package controllers;

import models.*;
import views.BoardView;
import views.MoveHistoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class MoveBackwardController extends JButton {


    public MoveBackwardController(Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
        this.setText("DO TYŁU");
        this.addActionListener(new MoveBackwardListener(board, boardView, mh, area, areaReturnable));
    }

    class MoveBackwardListener implements ActionListener {
        Board board;
        BoardView boardViewObject;
        MoveHistory mh;
        MoveHistoryView mhv;
        JTextArea area;
        JTextArea areaReturnable;
        public MoveBackwardListener(Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
            this.board = board;
            this.boardViewObject = boardView;
            this.mh = mh;
            this.area = area;
            this.areaReturnable = areaReturnable;

        }

        public void actionPerformed(ActionEvent e){

            boardViewObject.unmarkAllSquares();

            if(!this.mh.isHistoryEmpty()){
                MoveHistoryView mhv = new MoveHistoryView(this.area, this.areaReturnable);

                Move lastMove = this.mh.getLastMove();
                boardViewObject.movePiece(lastMove.getMovedPiece().getSymbol(), boardViewObject.getSquare(lastMove.getXN(), lastMove.getYN()),boardViewObject.getSquare(lastMove.getXP(), lastMove.getYP()));
                board.movePiece(board.getBoardSquare(lastMove.getXN(), lastMove.getYN()), board.getBoardSquare(lastMove.getXP(), lastMove.getYP()));
                this.board.setCountSpecialsAttacked();
                boardViewObject.printSpecials(board.getSpecialSquares());

                this.mh.removeLastMove();
                this.mh.addReturnableMove(lastMove);
                mhv.printMoveHistory(this.mh);
                mhv.printReturnableMoves(this.mh);

            }

        }


    }

}


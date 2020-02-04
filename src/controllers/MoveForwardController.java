package controllers;

import models.*;
import views.BoardView;
import views.MoveHistoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class MoveForwardController extends JButton {


    public MoveForwardController(Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
        this.setText("DO PRZODU");
        this.addActionListener(new MoveForwardListener(board, boardView, mh, area, areaReturnable));
    }

    class MoveForwardListener implements ActionListener {
        Board board;
        BoardView boardViewObject;
        MoveHistory mh;
        JTextArea area;
        JTextArea areaReturnable;

        public MoveForwardListener(Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
            this.board = board;
            this.boardViewObject = boardView;
            this.mh = mh;
            this.area = area;
            this.areaReturnable = areaReturnable;
        }

        public void actionPerformed(ActionEvent e){
            if(!this.mh.isReturnableListEmpty()){

                MoveHistoryView mhv = new MoveHistoryView(this.area, this.areaReturnable);

                Move lastMove = this.mh.getLastReturnableMove();

                boardViewObject.movePiece(lastMove.getMovedPiece().getSymbol(), boardViewObject.getSquare(lastMove.getXP(), lastMove.getYP()),boardViewObject.getSquare(lastMove.getXN(), lastMove.getYN()));
                board.movePiece(board.getBoardSquare(lastMove.getXP(), lastMove.getYP()), board.getBoardSquare(lastMove.getXN(), lastMove.getYN()));
                this.board.setCountSpecialsAttacked();
                boardViewObject.printSpecials(board.getSpecialSquares());


                this.mh.removeLastReturnableMove();
                this.mh.addMove(lastMove);
                mhv.printMoveHistory(this.mh);
                mhv.printReturnableMoves(this.mh);
            }

        }


    }

}



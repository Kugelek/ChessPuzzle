package controllers;

import models.*;
import views.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//
//AUTOR: JAKUB POBŁOCKI
//ZASTOSOWANE WZORCE PROJEKTOWE: Singleton, Fabryka
//
//


public class BoardController extends JFrame {

    Board board = new Board();
    BoardView boardViewObject = new BoardView();
    BoardController bc = this;
    MoveHistory moveHistory;
    MoveHistoryView mhv;

    public BoardController() {

        board.fillBoard();
        this.setLayout(new GridLayout(1,2));
        this.add(boardViewObject);

        JTextArea ruchy = new JTextArea(45, 20);
        JTextArea ruchyReturnable = new JTextArea(8, 20);
        moveHistory = new MoveHistory(board, boardViewObject);

        MoveForwardController mfc = new MoveForwardController(board, boardViewObject, moveHistory, ruchy, ruchyReturnable);
        MoveBackwardController mbc = new MoveBackwardController(board, boardViewObject, moveHistory, ruchy, ruchyReturnable);
        ResetController btnReset = new ResetController(bc, board, boardViewObject, moveHistory, ruchy, ruchyReturnable);
        JTextField fileName = new JTextField();
        SaveToFileController saver = new SaveToFileController(fileName, board, moveHistory);
        LoadFromFileController loader = new LoadFromFileController(fileName, board, boardViewObject,moveHistory, ruchy, ruchyReturnable );


        mhv = new MoveHistoryView(ruchy, ruchyReturnable);
        boardViewObject.addHighlightListenerToAllSquares(this);

        InitControllerSingleton initSingleObject = InitControllerSingleton.getInstance();
        initSingleObject.init(board, boardViewObject, this);


        CenterMenuSingleton centerMenu = CenterMenuSingleton.getInstance();
        centerMenu.setInitLayout();
        centerMenu.addMenuElements(btnReset, mbc, mfc, fileName, saver, loader, ruchy, ruchyReturnable);
        this.add(centerMenu);


        RadioMenu r = new RadioMenu();
        this.add(r);
        board.setCountSpecialsAttacked();
        boardViewObject.printSpecials(board.getSpecialSquares());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



   public class SquareHighlightController implements ActionListener {
        int i,j;
        public SquareHighlightController(int i,int j){
            this.i=i;this.j=j;
        }
        public void actionPerformed(ActionEvent e) {
            //jesli figura, zaznacz atakowane, zrob actionlistenery
            //jesli niefigura, wyswietl kordy i nic nie rob
            if(board.getBoardSquare(i, j).hasPiece()){
                //zaznacz atakowane
                boardViewObject.markAllAttacked(board.getBoardSquare(i,j).getPiece());
                boardViewObject.removeAllActionListeners();
                boardViewObject.addMoveListenerToAllSquares(bc, i, j);
            }
        }

    }

    public class SquareMoveController implements ActionListener {
        int i,j;
        int prevI, prevJ;
        public SquareMoveController(int i,int j, int prevI, int prevJ){
            this.i=i;
            this.j=j;
            this.prevI = prevI;
            this.prevJ = prevJ;
        }
        public void actionPerformed(ActionEvent e){
            //jeśli cel to specjalne pole, nic nie rob
            // jesli figura, potraktuj jako nową
            //else przenies  figure

            if(board.getBoardSquare(i, j).hasPiece() == false && board.getBoardSquare(i, j).isSpecial() == false) {
                moveHistory.addNewMove(board.getBoardSquare(prevI, prevJ), board.getBoardSquare(i, j));
                mhv.printMoveHistory(moveHistory);
                moveHistory.removeAllReturnableMoves();
                mhv.printReturnableMoves(moveHistory);

                boardViewObject.movePiece(board.getBoardSquare(prevI, prevJ).getPiece().getSymbol(), boardViewObject.getSquare(prevI, prevJ), boardViewObject.getSquare(i, j));
                board.movePiece(board.getBoardSquare(prevI, prevJ), board.getBoardSquare(i, j));
                board.setCountSpecialsAttacked();
                boardViewObject.printSpecials(board.getSpecialSquares());
                boardViewObject.unmarkAllSquares();


                boardViewObject.removeAllActionListeners();
                boardViewObject.addHighlightListenerToAllSquares(bc);
                boardViewObject.gameEnder(board.hasGameEnded());
            }else if(board.getBoardSquare(i, j).hasPiece()){
                boardViewObject.unmarkAllSquares();
                boardViewObject.markAllAttacked(board.getBoardSquare(i,j).getPiece());
                boardViewObject.removeAllActionListeners();
                boardViewObject.addMoveListenerToAllSquares(bc, i, j);
            }
        }
    }



    public static void main(String[] args) {
        JFrame f = new BoardController();
        f.setSize(800,600);
        f.setLocation(100,100);
        f.setVisible(true);

    }
}




package controllers;


import models.Board;
import models.MoveHistory;
import models.Square;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFileController extends JButton {


    public SaveToFileController(JTextField field, Board board, MoveHistory history){
        this.setText("SAVE");
        this.addActionListener(new SaveToFileListener(field, board, history));

    }

    class SaveToFileListener implements ActionListener {
        String fileName;
        JTextField field;
        Board board;
        MoveHistory history;
        public SaveToFileListener(JTextField field, Board board, MoveHistory history){
            this.field = field;
            this.board = board;
            this.history = history;
        }

        public void actionPerformed(ActionEvent e){

            this.fileName = this.field.getText();


            try{
                this.saveToFile();
            }catch(Exception ex){
                System.out.println(" ERROR "+e);
            }

            this.stringifyBoardState();
            this.stringifyHistory();
        }

        public void saveToFile() throws FileNotFoundException{

            PrintWriter zapis = new PrintWriter(this.fileName);

            String boardAsString = String.join(" ", this.stringifyBoardState());
            zapis.println(boardAsString);
            zapis.println(this.stringifyHistory());

            zapis.close();

        }

        public ArrayList<String> stringifyBoardState(){

            ArrayList<String> boardChars = new ArrayList<String>();
            for(Square sq : board.getSquares()){
                if(sq.hasPiece()){
                    boardChars.add(Character.toString(sq.getPiece().getSymbol()));
                }else if(sq.isSpecial()){
                    boardChars.add("X");
                }else{
                    boardChars.add("0");
                }
            }

           return boardChars;
        }

        public String stringifyHistory(){
            ArrayList<String> allHistoryRows = new ArrayList<String>();
            ArrayList<String> moveParts = new ArrayList<String>();
            this.history.getAllmoves().forEach(move -> {
                moveParts.add(Character.toString(move.getMovedPiece().getSymbol()));
                moveParts.add(Integer.toString(move.getXP()));
                moveParts.add(Integer.toString(move.getYP()));
                moveParts.add(Integer.toString(move.getXN()));
                moveParts.add(Integer.toString(move.getYN()));
                String stringifiedParts = String.join(" ", moveParts);
                allHistoryRows.add(stringifiedParts);
                moveParts.removeAll(moveParts);
            });


            allHistoryRows.add("END");

            this.history.getReturnableMoves().forEach(move -> {
                moveParts.add(Character.toString(move.getMovedPiece().getSymbol()));
                moveParts.add(Integer.toString(move.getXP()));
                moveParts.add(Integer.toString(move.getYP()));
                moveParts.add(Integer.toString(move.getXN()));
                moveParts.add(Integer.toString(move.getYN()));
                String stringifiedParts = String.join(" ", moveParts);
                allHistoryRows.add(stringifiedParts);
                moveParts.removeAll(moveParts);
            });

            return String.join("\n", allHistoryRows);
        }



    }

}

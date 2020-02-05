package views;


import models.Move;
import models.MoveHistory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MoveHistoryView {
    JTextArea area;
    JTextArea areaReturnable;
    public MoveHistoryView(JTextArea area, JTextArea areaReturnable ){
        this.area = area;
        this.areaReturnable = areaReturnable;
    }

    public void printMoveHistory(MoveHistory moveHistory){
        String allMovesStringified = "";
        ArrayList<String> allRows = new ArrayList<String>();



        moveHistory.getAllmoves().forEach(move -> {
            allRows.add(Character.toString(move.getMovedPiece().getSymbol()));
            allRows.add("(");
            allRows.add(Integer.toString(move.getXP()));
            allRows.add(Integer.toString(move.getYP()));
            allRows.add(") -> (");
            allRows.add(Integer.toString(move.getXN()));
            allRows.add(Integer.toString(move.getYN()));
            allRows.add(")");
            allRows.add("\n");
        });


        allMovesStringified = String.join(" ", allRows);
        this.area.setText(allMovesStringified);
    }


    public void printReturnableMoves(MoveHistory moveHistory){
        String allMovesStringified = "";
        ArrayList<String> allRows = new ArrayList<String>();

        moveHistory.getReturnableMoves().forEach(move -> {
            allRows.add(Character.toString(move.getMovedPiece().getSymbol()));
            allRows.add("(");
            allRows.add(Integer.toString(move.getXP()));
            allRows.add(Integer.toString(move.getYP()));
            allRows.add(") -> (");
            allRows.add(Integer.toString(move.getXN()));
            allRows.add(Integer.toString(move.getYN()));
            allRows.add(")");
            allRows.add("\n");
        });


        allMovesStringified = String.join(" ", allRows);
        this.areaReturnable.setText(allMovesStringified);
        this.areaReturnable.setForeground(Color.BLUE);


    }
}

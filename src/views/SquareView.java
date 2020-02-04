package views;

import models.Piece;

import javax.swing.*;
import java.awt.*;

public class SquareView extends JButton {




    public void paintSquareNotAttacked(int i, int j){
        if((i+j) % 2 == 0)
            this.setBackground(new Color(80, 80, 80));
        else
            this.setBackground(new Color(230, 230, 230));
    }

    public void paintSquareAttacked(int i, int j){
        if((i+j) % 2 == 0)
            this.setBackground(new Color(60, 60, 180));
        else
            this.setBackground(new Color(80, 110, 240));
    }

    public void putPiece(char symbol){
        PieceView pv = new PieceView("white", symbol);
        this.setIcon(new ImageIcon(pv.getIconPath()));
    }
    public void removePiece(){
        this.setIcon(null);
    }

}

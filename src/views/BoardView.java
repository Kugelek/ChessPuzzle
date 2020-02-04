package views;


import controllers.BoardController;
import models.Board;
import models.Piece;
import models.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardView extends JPanel {
    SquareView squares[][] = new SquareView[8][8];




    public void gameEnder(boolean isEnd){
        if(isEnd){
            this.removeAllActionListeners();
        }
    }


    public void printSpecials(ArrayList<Square> specials){
        specials.forEach(specialSquare ->{
            squares[specialSquare.getX()][specialSquare.getY()].setText(Integer.toString(specialSquare.getCountAttacks()));
            squares[specialSquare.getX()][specialSquare.getY()].setForeground(Color.red);
            squares[specialSquare.getX()][specialSquare.getY()].setFont(new Font("Arial", Font.PLAIN, 25));

        });
    }

    public void eraseSpecials(){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                this.squares[i][j].setText("");
            }
    }

    public BoardView(){
        this.setLayout(new GridLayout(8,8));
        this.initializeSquares();
        this.setAllBackgrounds();
        this.addAllToPanel();
    }

    public void markAllAttacked(Piece piece){
        piece.getAttackedSquares().forEach(el->{
            this.squares[el.getX()][el.getY()].paintSquareAttacked(el.getX(), el.getY());
        });
    }

    public void unmarkAllSquares(){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                this.squares[i][j].paintSquareNotAttacked(i,j);
            }
    }

    public void movePiece(char symbol, SquareView curr, SquareView next){
        next.putPiece(symbol);
        curr.removePiece();
    }

    //TODO  funkcja apply() najlepiej z jakas lambdą dobrze rozkminioną

    public void initializeSquares(){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                this.squares[i][j] = new SquareView();
            }
    }
    public void eraseSquares(){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                this.squares[i][j].removePiece();
            }
    }


    public void setAllBackgrounds(){
        int i, j;

        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                if((i+j) % 2 == 0)
                    squares[i][j].setBackground(new Color(80, 80, 80));
                else
                    squares[i][j].setBackground(new Color(230, 230, 230));
            }
    }

    public void addAllToPanel(){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                this.add(squares[i][j]);
            }
    }

    public void addHighlightListenerToAllSquares(BoardController bc){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){
                squares[i][j].addActionListener(bc.new SquareHighlightController(i, j));
            }

    }

    public void addMoveListenerToAllSquares(BoardController bc, int prevI, int prevJ){
        int i, j;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++){

                squares[i][j].addActionListener(bc.new SquareMoveController(i, j, prevI, prevJ));
            }

    }




    public void removeAllActionListeners(){
        int i, j;

        for (i=0;i<8;i++)
            for (j=0;j<8;j++)
               for( ActionListener al : squares[i][j].getActionListeners() ) {
                   squares[i][j].removeActionListener( al );
                }
   }


    public SquareView getSquare(int x, int y){
        return this.squares[x][y];
    }

    public SquareView[][] getSquares(){
        return this.squares;
    }
}

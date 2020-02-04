package models;

import java.util.ArrayList;

public class Piece {
    char symbol;
    String fullName;
    Square position;
    Board allSquares;
    ArrayList<Square> attackedSquares = new ArrayList<Square>();

    public Piece(char sym, Square sq, Board allSq){
        this.symbol = sym;
        this.position = sq;
        this.allSquares = allSq;
    }
    public char getSymbol(){
        return this.symbol;
    }
    public void generateAttackedSquares(){

    }
    public void resetAttackedSquares(){
    }
    public Square getPosition(){
        return this.position;
    }
    public void setPosition(Square sq){
        this.position = sq;
    }
    public ArrayList<Square> getAttackedSquares(){
        return this.attackedSquares;
    }


}


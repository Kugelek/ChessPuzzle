package models;


import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(char sym, Square sq, Board allSq){
        super(sym, sq, allSq);
    }

    @Override
    public void generateAttackedSquares(){
        int myX = position.getX();
        int myY = position.getY();


        boolean flag = true;
        for(int i = myY+1; (i < 8) && flag; i++){
            flag = this.addAttackedSquare(myX, i);
        }
        flag = true;
        for(int i = myY-1; (i >= 0) && flag; i--){
            flag = this.addAttackedSquare(myX, i);
        }
        flag = true;
        for(int i = myX+1; i < 8 && flag; i++){
            flag = this.addAttackedSquare(i, myY);
        }
        flag = true;
        for(int i = myX-1; i >= 0 && flag; i--){
            flag = this.addAttackedSquare(i, myY);
        }


    }

    public void resetAttackedSquares(){
        this.attackedSquares.removeAll(attackedSquares);
    }

    public ArrayList<Square> getAttackedSquares(){
        return this.attackedSquares;
    }

    public boolean addAttackedSquare(int x, int y){
        //weryfikuj czy pole istnienie, jeśli tak, to weryfikuj czy nie jest figurą/polem specjalnym
        if(x >= 0 && x < 8 && y >= 0 && y < 8){
            Square sq = allSquares.getBoardSquare(x, y);
            if(!sq.hasPiece()){
                this.attackedSquares.add(sq);
                return true;
            }

            return false;
        }
        return false;

    }


}
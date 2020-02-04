package models;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    //szachownica ma 64pola
    ArrayList<Square> boardSquares = new ArrayList<Square>(64);
    ArrayList<Piece> pieces = new ArrayList<Piece>(5);
    ArrayList<Square> specialSquares = new ArrayList<>();
    ArrayList<Square> allAttackedSquares = new ArrayList<>();
    public Board(){

    }

    public boolean hasGameEnded(){
        if(this.specialSquares.isEmpty())
            return false;
        for(Square specSquare : specialSquares){
            if(specSquare.getCountAttacks() != 0)
                return false;
        }
        return true;
    }

    public ArrayList<Square> getSquares(){
        return this.boardSquares;
    }

    public void addToPieces(Piece piece){
        this.pieces.add(piece);
    }
// TODO: dobra tu trzeba przekminic bo to nie ma tak hop siup
    public void setCountSpecialsAttacked(){
        this.allAttackedSquares.removeAll(this.allAttackedSquares);
        pieces.forEach(piece -> {
            piece.resetAttackedSquares();
            piece.generateAttackedSquares();
            this.allAttackedSquares.addAll(piece.getAttackedSquares());
        });

      //  this.allAttackedSquares.forEach(el-> System.out.println("[ "+ el.getX()+" "+el.getY()+" ]"));
       // System.out.println(this.allAttackedSquares);

      specialSquares.forEach(specSquare -> {
          //  System.out.println(this.allAttackedSquares.contains(specSquare));
          specSquare.updateCountAttacks(Collections.frequency(this.allAttackedSquares, specSquare));
            System.out.println(Collections.frequency(this.allAttackedSquares, specSquare));
        });

    }

    public ArrayList<Square> getSpecialSquares(){
        boardSquares.forEach(el ->{
            if(el.isSpecial)
                this.specialSquares.add(el);
        });
        return this.specialSquares;
    }

    public ArrayList<Piece> getPieces(){
        return this.pieces;
    }

    public void movePiece(Square curr, Square next){
        next.putPiece(curr.getPiece());
        next.getPiece().setPosition(next);
        curr.removePiece();
    }

    public void addSquare(int x, int y){
        boardSquares.add(new Square(x, y));
    }

    public void fillBoard(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.addSquare(i, j);
    }

    public Square getBoardSquare(int x, int y){
        return this.boardSquares.get(x*8+y);
    }

    public void showContent(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                System.out.println(this.getBoardSquare(i, j).x + " " + this.getBoardSquare(i, j).y + this.getBoardSquare(i, j).getPiece()+ "\n");
    }


}

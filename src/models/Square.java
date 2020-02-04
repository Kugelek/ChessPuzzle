package models;

public class Square {
    int x;
    int y;
    Piece pieceOnThisSquare;
    int countAttacksNeededToEnd;
    boolean isSpecial;


    public void updateCountAttacks(int currCountAttacks){
        this.countAttacksNeededToEnd = 3 - currCountAttacks;
    }
    public int getCountAttacks(){
        return this.countAttacksNeededToEnd;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Square(int xCoord, int yCoord){
        this.x = xCoord;
        this.y = yCoord;
        this.pieceOnThisSquare = null;
        this.countAttacksNeededToEnd = 3;
        this.isSpecial = false;
    }

    public void setSpecial(){
        this.isSpecial = true;
    }

    public void unsetSpecial(){
        this.isSpecial = false;
    }

    public boolean isSpecial(){
        return this.isSpecial;
    }


    public boolean hasPiece(){
        if(this.pieceOnThisSquare == null)
            return false;
        else return true;
    }

    public Piece getPiece(){
        if(this.hasPiece())
            return this.pieceOnThisSquare;
        else return null;
    }

    public Square(int xCoord, int yCoord, Piece piece){
        this.x = xCoord;
        this.y = yCoord;
        this.pieceOnThisSquare = piece;
    }

    public void putPiece(Piece newPiece){
        this.pieceOnThisSquare = newPiece;

    }
    public void removePiece(){
        this.pieceOnThisSquare = null;
    }
}

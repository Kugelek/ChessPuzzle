package models;

public class Move {
    int xPrevious;
    int yPrevious;
    int xNext;
    int yNext;
    Piece movedPiece;


    public Move(Square curr, Square next){
        this.movedPiece = curr.getPiece();
        this.xPrevious = curr.getX();
        this.yPrevious = curr.getY();
        this.xNext = next.getX();
        this.yNext = next.getY();
    }

    public Move(Piece piece, int xp, int yp, int xn, int yn){
        this.movedPiece = piece;
        this.xPrevious = xp;
        this.yPrevious = yp;
        this.xNext = xn;
        this.yNext = yn;
    }

    public int getXP(){
        return this.xPrevious;
    }
    public int getYP(){
        return this.yPrevious;
    }
    public int getXN(){
        return this.xNext;
    }
    public int getYN(){
        return this.yNext;
    }
    public Piece getMovedPiece(){
        return this.movedPiece;
    }

}

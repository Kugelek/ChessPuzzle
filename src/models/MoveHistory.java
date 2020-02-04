package models;

import views.BoardView;

import java.util.ArrayList;

public class MoveHistory {
    ArrayList<Move> allMoves = new ArrayList<Move>();

    ArrayList<Move> returnableMoves = new ArrayList<Move>();

    Board board;
    BoardView boardView;

    public void addMove(Move move){
        this.allMoves.add(move);
    }

    public boolean isReturnableListEmpty(){
        if(this.returnableMoves.isEmpty())
            return true;
        else return false;
    }
    public void addReturnableMove(Move move){
        this.returnableMoves.add(move);
    }

    public void removeFirstReturnableMove(){
        this.returnableMoves.remove(0);
    }

    public void removeLastReturnableMove(){
        this.returnableMoves.remove(this.returnableMoves.size() - 1);
    }
    public Move getLastReturnableMove(){
        if(!this.returnableMoves.isEmpty())
            return this.returnableMoves.get(this.returnableMoves.size() - 1);
        return null;
    }



    public void removeAllReturnableMoves(){
        this.returnableMoves.removeAll(this.returnableMoves);
    }

    public ArrayList<Move>  getReturnableMoves(){
        return this.returnableMoves;
    }



    public MoveHistory(Board board, BoardView boardView){
        this.board = board;
        this.boardView = boardView;
    }

    public void addNewMove(Square curr, Square next){
        this.allMoves.add(new Move(curr, next));
    }

    public void removeAllMoves(){
        this.allMoves.removeAll(this.allMoves);
    }

    public void removeLastMove(){
        this.allMoves.remove(this.allMoves.size() - 1);
    }
    public Move getLastMove(){
        return this.allMoves.get(this.allMoves.size() - 1);
    }

    public ArrayList<Move> getAllmoves(){
        return this.allMoves;
    }

    public boolean isHistoryEmpty(){
        if(this.allMoves.isEmpty())
            return true;
        else return false;
    }
}

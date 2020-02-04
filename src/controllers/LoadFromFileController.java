package controllers;


import models.*;
import views.BoardView;
import views.MoveHistoryView;
import views.SquareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import java.util.stream.IntStream;

public class LoadFromFileController extends JButton {


    public LoadFromFileController(JTextField field, Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
        this.setText("LOAD");
        this.addActionListener(new LoadFromFileListener(field, board, boardView, mh, area, areaReturnable));

    }

    class LoadFromFileListener implements ActionListener {

        String fileName;
        JTextField field;
        Board board;
        BoardView boardViewObject;
        MoveHistory mh;
        JTextArea area;
        JTextArea areaReturnable;
        char[] boardSymbols = new char[70];
        List<String> history;
        ArrayList<Piece> pieces = new ArrayList<Piece>();

        public LoadFromFileListener(JTextField field, Board board, BoardView boardView, MoveHistory mh, JTextArea area, JTextArea areaReturnable){
            this.field = field;
            this.board = board;
            this.boardViewObject = boardView;
            this.mh = mh;
            this.area = area;
            this.areaReturnable = areaReturnable;


        }

        public void actionPerformed(ActionEvent e){

            this.fileName = this.field.getText();


            try{
                this.loadFromFile();
            }catch(Exception ex){
                System.out.println(" ERROR "+e);
            }
            this.cleanHistory();
            this.cleanBoard();
            this.loadBoard();
            this.loadSpecials();
            this.loadHistory();
        }


        public void loadFromFile() throws IOException {

//            Scanner odczyt = new Scanner(new File(this.fileName));
//            String text = odczyt.nextLine();

            List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);

            this.boardSymbols = lines.get(0).replace(" ", "").toCharArray();
            lines.remove(0);
            this.history = lines;
            System.out.println(this.boardSymbols);
            System.out.println(lines);

        }

        public int[] indexOf(char[] arr, char val) {
            int index = IntStream.range(0, arr.length).filter(i -> arr[i] == val).findFirst().orElse(-1);
            int[] coords = new int[2];
            coords[0] = index / 8;
            coords[1] = index % 8;
            return coords;
        }

        public void loadSpecials(){
            int[] indexes = IntStream.range(0, this.boardSymbols.length).filter(i -> this.boardSymbols[i] == 'X').toArray();
            int[] firstCoords = new int[4];
            int[] secondCoords = new int[4];
            for(int i = 0; i < 4; i++){
                firstCoords[i] = indexes[i] / 8;
                secondCoords[i] = indexes[i] % 8;
                board.getBoardSquare(firstCoords[i],secondCoords[i]).setSpecial();
            }
            board.setCountSpecialsAttacked();
            boardViewObject.printSpecials(board.getSpecialSquares());
        }

        public Piece getPiece(char symbol){
            for(Piece piece : this.pieces){
                if(piece.getSymbol() == symbol)
                    return piece;
            }
            return null;
        }

        public void loadHistory(){
            System.out.println(this.history);
            String str = String.join("", this.history);
            String[] arr = str.split("END");
            char[] allMoves = arr[0].replaceAll(" ", "").toCharArray();

            char[] returnableMoves = arr[1].replaceAll(" ", "").toCharArray();


            System.out.println(allMoves[3] + " XD "+returnableMoves[5]);
            System.out.println(allMoves[2] + " XD "+returnableMoves[6]);
            System.out.println(allMoves[1] + " XD "+returnableMoves[7]);
            System.out.println(returnableMoves.length);
            System.out.println(allMoves.length);
            for(int i = 0; i < allMoves.length; i=i+5){
                this.mh.addMove(new Move(this.getPiece(allMoves[i]), Character.getNumericValue(allMoves[i+1]), Character.getNumericValue(allMoves[i+2]), Character.getNumericValue(allMoves[i+3]), Character.getNumericValue(allMoves[i+4])));
            }
            for(int i = 0; i < returnableMoves.length; i=i+5){
                this.mh.addReturnableMove(new Move(this.getPiece(returnableMoves[i]), Character.getNumericValue(returnableMoves[i+1]), Character.getNumericValue(returnableMoves[i+2]), Character.getNumericValue(returnableMoves[i+3]), Character.getNumericValue(returnableMoves[i+4])));
            }
              //        this.history.forEach(move -> {
//                if(!move.equals("END")) {
//                    char[] letters = move.replaceAll(" ", "").toCharArray();
//                    if (this.history.contains("END")) {
//                        this.mh.addMove(new Move(this.getPiece(letters[0]), letters[1], letters[2], letters[3], letters[4]));
//                    }else {
//                        this.mh.addReturnableMove(new Move(this.getPiece(letters[0]), letters[1], letters[2], letters[3], letters[4]));
//                    }
//                }
              //  this.history.remove(move);
      //      });



//            for(String move : this.history){
//                if(move == "END")
//                    break;
//                char[] letters = move.replaceAll(" ", "").toCharArray();
//                this.mh.addMove(new Move(this.getPiece(letters[0]),letters[1], letters[2],letters[3],letters[4]));
//                this.history.remove(move);
//            }
//            for(String returnableMove : this.history){
//                char[] letters = returnableMove.replaceAll(" ", "").toCharArray();
//                this.mh.addReturnableMove(new Move(this.getPiece(letters[0]),letters[1], letters[2],letters[3],letters[4]));
//                this.history.remove(returnableMove);
//
//            }
//

            System.out.println(this.mh.getAllmoves());
           MoveHistoryView mhv = new MoveHistoryView(this.area, this.areaReturnable);
           mhv.printMoveHistory(this.mh);
           mhv.printReturnableMoves(this.mh);





//            moveHistory.removeAllReturnableMoves();
//            mhv.printReturnableMoves(moveHistory);

        }



        public void loadBoard(){
            int[] indexes;

            indexes = this.indexOf(this.boardSymbols, 'K');
            Piece king = new King('K', board.getBoardSquare(indexes[0],indexes[1]), board);
            board.getBoardSquare(indexes[0],indexes[1]).putPiece(king);
            board.addToPieces(king);
            boardViewObject.getSquare(indexes[0],indexes[1]).putPiece(king.getSymbol());
            this.pieces.add(king);

            indexes = this.indexOf(this.boardSymbols, 'Q');
            Piece queen = new Queen('Q', board.getBoardSquare(indexes[0],indexes[1]),board);
            board.getBoardSquare(indexes[0],indexes[1]).putPiece(queen);
            board.addToPieces(queen);
            boardViewObject.getSquare(indexes[0],indexes[1]).putPiece(queen.getSymbol());
            this.pieces.add(queen);

            indexes = this.indexOf(this.boardSymbols, 'R');
            Piece rook = new Rook('R', board.getBoardSquare(indexes[0],indexes[1]),board);
            board.getBoardSquare(indexes[0],indexes[1]).putPiece(rook);
            board.addToPieces(rook);
            boardViewObject.getSquare(indexes[0],indexes[1]).putPiece(rook.getSymbol());
            this.pieces.add(rook);

            indexes = this.indexOf(this.boardSymbols, 'B');
            Piece bishop = new Bishop('B', board.getBoardSquare(indexes[0],indexes[1]),board);
            board.getBoardSquare(indexes[0],indexes[1]).putPiece(bishop);
            board.addToPieces(bishop);
            boardViewObject.getSquare(indexes[0],indexes[1]).putPiece(bishop.getSymbol());
            this.pieces.add(bishop);

            indexes = this.indexOf(this.boardSymbols, 'S');
            Piece knight = new Knight('S', board.getBoardSquare(indexes[0],indexes[1]),board);
            board.getBoardSquare(indexes[0],indexes[1]).putPiece(knight);
            board.addToPieces(knight);
            boardViewObject.getSquare(indexes[0],indexes[1]).putPiece(knight.getSymbol());
            this.pieces.add(knight);

        }

        public void cleanHistory(){
            MoveHistoryView mhv = new MoveHistoryView(this.area, this.areaReturnable);
            this.mh.removeAllReturnableMoves();
            this.mh.removeAllMoves();
            mhv.printReturnableMoves(this.mh);
            mhv.printMoveHistory(this.mh);
        }

        public void cleanBoard(){

            //usuwanie pol specjalnych
            this.board.getSpecialSquares().forEach(specSquare -> {
                specSquare.unsetSpecial();
            });
            this.board.getSpecialSquares().removeAll(this.board.getSpecialSquares());
            this.boardViewObject.eraseSpecials();


            //usuwanie figur z modelu planszy
            this.board.getSquares().forEach(square ->{ square.removePiece();
            });
            this.board.getPieces().removeAll(this.board.getPieces());
            System.out.println(this.board.getPieces());


            this.boardViewObject.eraseSquares();
        }





    }

}

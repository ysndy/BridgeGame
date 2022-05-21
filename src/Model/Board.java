package Model;

import Model.Cells.*;
import Util.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private Cell[][] board;

    public Board() {
        //board 초기화 (map 파일 번역)

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\82107\\IdeaProjects\\BridgeGame\\src\\default.map"));

            ArrayList<ArrayList<Cell>> board = new ArrayList<>();
            // 시작 셀
            int x=0, y=0, x_max=0;
            Cell cell = null;
            board.add(new ArrayList<Cell>());
            board.get(0).add(new StartCell());
            String startStr = scanner.nextLine();

            switch (getNext(startStr)){

                case 'U': y--; break;
                case 'D': y++; break;
                case 'R': x++; break;

            }

            while (scanner.hasNextLine()) {

                String str = scanner.nextLine();

                switch (getType(str)) {

                    case 'C': cell = new DefaultCell(); break;
                    case 'B': cell = new BridgeStartCell(); break;
                    case 'b': cell = new BridgeEndCell(); break;
                    case 'H': cell = new ToolCell(new Card("Hammer", 2)); break;
                    case 'S': cell = new ToolCell(new Card("Saw", 3)); break;
                    case 'P': cell = new ToolCell(new Card("Philips Driver", 1)); break;
                    case 'E': cell = new EndCell(); break;

                }

                if(y<0){
                    board.add(0, new ArrayList<Cell>());
                    y=0;

                }else if(y==board.size()) {
                    board.add(new ArrayList<Cell>());

                }else {

                }
                try {
                    board.get(y).add(x, cell);
                }catch (IndexOutOfBoundsException e){
                   for(int i=board.get(y).size(); i<x; i++){
                       board.get(y).add(null);
                   }
                   board.get(y).add(x, cell);
                }

                //System.out.println("y="+y+", x="+x);
                if(getType(str)=='B') board.get(y).add(new BridgeCell());
                switch (getNext(str)){

                    case 'U': y--; break;
                    case 'D': y++; break;
                    case 'R': x++; break;
                    case 'E': x_max = x; continue;

                }

            }

            this.board = new Cell[board.size()][x_max+1];

            for(int i=0; i<board.size();i++){

                for(int j=0; j<board.get(i).size();j++){
                    this.board[i][j] = board.get(i).get(j);
                    board.get(i).get(j).setPosition(new Position(i, j));
                }

            }

//            for(int i=0; i<this.board.length; i++){
//                for(int j=0; j<this.board[i].length; j++){
//                    if(this.board[i][j]!=null) System.out.print("*");
//                    else System.out.print(" ");
//                }
//                System.out.println();
//            }

            //if(this.board[1][0] instanceof EndCell) System.out.println("true");

        }catch(FileNotFoundException e){}

    }

    private char getNext(String str){
        String[] tmp = str.split(" ");
        return tmp[tmp.length-1].charAt(0);
    }

    private char getType(String str){
        String[] tmp = str.split(" ");
        return tmp[0].charAt(0);
    }

    public Cell[][] getBoard(){
        return board;
    }

    public Cell getStartCell(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] instanceof StartCell) return board[i][j];
            }
        }
        return null;
    }

    public Cell getPieceCell(Piece piece){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] != null) {
                    ArrayList<Piece> pieces = board[i][j].getPieces();

                    for(int k=0; k<pieces.size(); k++){
                        if(pieces.get(k).equals(piece)) return board[i][j];
                    }

                }
            }

        }
        return null;
    }

    public Cell getRightCell(Cell cell){
        try {
            return board[cell.getPosition().getY()][cell.getPosition().getX() + 1];
        }catch (ArrayIndexOutOfBoundsException e){
            return cell;
        }
    }

    public Cell getUpCell(Cell cell){
        try {
            return board[cell.getPosition().getY() - 1][cell.getPosition().getX()];
        }catch (ArrayIndexOutOfBoundsException e){
            return cell;
        }
    }

    public Cell getLeftCell(Cell cell){
        try {
            return board[cell.getPosition().getY()][cell.getPosition().getX() - 1];
        }catch (ArrayIndexOutOfBoundsException e){
            return cell;
        }
    }

    public Cell getDownCell(Cell cell){
        try {
            return board[cell.getPosition().getY() + 1][cell.getPosition().getX()];
        }catch (ArrayIndexOutOfBoundsException e){
            return cell;
        }
    }

}

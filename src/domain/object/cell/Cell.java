package domain.object.cell;

import domain.Data;
import domain.object.Piece;

import java.util.ArrayList;

// 테스트할 것. 말이 정상적으로 도착하는지

public class Cell {

    protected ArrayList<Piece> pieces;
    private Position position;
    private int rank;

    public char getType() {
        return type;
    }

    private char type;

    public char getBackDirection() {
        return backDirection;
    }

    private char backDirection;

    public Cell(char backDirection, char type){
        pieces = new ArrayList<>();
        this.backDirection = backDirection;
        this.type = type;
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public void arrive(Piece piece){
        pieces.add(piece);
    }

    public void leave(Piece piece){
        pieces.remove(piece);
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public boolean[] playerNumberOfPieces(){

        boolean[] pn = new boolean[Data.PLAYER_MAX];
        for(int i=0; i<pieces.size(); i++){
            pn[pieces.get(i).getPlayer().getNumber()] = true;
        }
        return pn;
    }

}

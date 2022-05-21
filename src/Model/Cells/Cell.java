package Model.Cells;
import Model.Piece;
import Util.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {

    protected ArrayList<Piece> pieces;
    private Position position;

    public Cell(){
        pieces = new ArrayList<>();
    }
//
//    public void pass(Piece piece){
//
//    }

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

}
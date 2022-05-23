package Model.Cells;

import Model.Piece;

public class EndCell extends Cell implements Arriving, Passing {


    public EndCell(char backDirection, char type) {
        super(backDirection, type);
    }

    @Override
    public void arrive(Piece piece) {
        piece.getPlayer().setEnd(true);
    }

    @Override
    public void pass(Piece piece) {
        piece.getPlayer().setEnd(true);
    }
}

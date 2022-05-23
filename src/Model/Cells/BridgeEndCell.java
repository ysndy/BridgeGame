package Model.Cells;

import Model.Piece;

public class BridgeEndCell extends Cell implements Arriving {

    public BridgeEndCell(char backDirection, char type) {
        super(backDirection, type);
    }

    @Override
    public void arrive(Piece piece) {
    }
}

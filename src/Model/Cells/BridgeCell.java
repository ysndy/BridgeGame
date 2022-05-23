package Model.Cells;

import Model.Piece;

public class BridgeCell extends Cell implements Passing {

    public BridgeCell(char backDirection, char type) {
        super(backDirection, type);
    }

    @Override
    public void pass(Piece piece) {
        piece.getPlayer().addBridgeCardCnt();
    }
}

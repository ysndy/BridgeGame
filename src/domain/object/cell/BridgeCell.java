package domain.object.cell;

import domain.object.Piece;

public class BridgeCell extends Cell implements Passing {

    public BridgeCell(int rank, char type) {
        super(rank, type);
    }

    @Override
    public void pass(Piece piece) {
        piece.getPlayer().addBridgeCardCnt();
    }
}

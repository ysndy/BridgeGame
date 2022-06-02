package domain.object.cell;

import domain.object.Piece;

public class EndCell extends Cell implements On, Passing {

    public EndCell(int rank, char type) {
        super(rank, type);
    }

    private int rank=1;
    @Override
    public void on(Piece piece) {
        piece.getPlayer().setEnd(true);

        switch (rank++){
            case 1:
                piece.getPlayer().addScore(7);
                break;
            case 2:
                piece.getPlayer().addScore(3);
                break;
            case 3:
                piece.getPlayer().addScore(1);
                break;
            case 4:
                break;
        }

    }

    @Override
    public void pass(Piece piece) {
        piece.getPlayer().setEnd(true);
    }
}

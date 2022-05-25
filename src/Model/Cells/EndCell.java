package Model.Cells;

import Model.Piece;

public class EndCell extends Cell implements On, Passing {

    private int rank = 1;

    public EndCell(char backDirection, char type) {
        super(backDirection, type);
    }

    @Override
    public void on(Piece piece) {
        piece.getPlayer().setEnd(true);

        switch (rank++){
            case 1:
                piece.getPlayer().addScore(7);
                piece.getPlayer().setRank(1);
                break;
            case 2:
                piece.getPlayer().addScore(3);
                piece.getPlayer().setRank(2);
                break;
            case 3:
                piece.getPlayer().addScore(1);
                piece.getPlayer().setRank(3);
                break;
            case 4:
                piece.getPlayer().setRank(4);
                break;
        }

    }

    @Override
    public void pass(Piece piece) {
        piece.getPlayer().setEnd(true);
    }
}

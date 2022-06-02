package domain.object.cell;

import domain.object.Piece;

public interface Passing extends Response {
    void pass(Piece piece);
}

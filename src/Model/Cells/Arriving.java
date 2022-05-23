package Model.Cells;

import Model.Piece;

public interface Arriving extends Responsive{

    // 라운드 종료 시
    public abstract void arrive(Piece piece);

}

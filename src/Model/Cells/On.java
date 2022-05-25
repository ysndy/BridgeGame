package Model.Cells;

import Model.Piece;

public interface On extends Responsive{

    // 라운드 종료 시
    public abstract void on(Piece piece);

}

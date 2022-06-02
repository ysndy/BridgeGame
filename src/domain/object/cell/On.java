package domain.object.cell;

import domain.object.Piece;

public interface On extends Response {

    // 라운드 종료 시
    void on(Piece piece);

}

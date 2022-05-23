package Controller;

import Model.Cells.Cell;
import Model.Model;
import Model.Board;

public abstract class Controller {

    Model model;

    protected Controller(Model model){
        this.model = model;
    }

    public abstract int input_playerNo();
    public abstract boolean input_isMove();
    public abstract String input_command(int movableCnt, Cell curCell);



}

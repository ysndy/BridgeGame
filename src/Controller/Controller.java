package Controller;

import Model.Cells.Cell;
import Model.Model;
import Model.Board;
import View.View;

public abstract class Controller {

    Model model = new Model();
    View view;

    protected Controller(Model model){
        this.model = model;
    }

    public abstract int input_playerNo();
    public abstract boolean input_isMove(int number);
    public abstract String input_command(int movableCnt, Cell curCell);

    public static void main(String[] args) {

    }

}

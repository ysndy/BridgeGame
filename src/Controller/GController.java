package Controller;

import Model.Cells.Cell;
import Model.Model;

public class GController extends Controller{

    public GController(Model model) {
        super(model);
    }

    @Override
    public int input_playerNo() {
        return 0;
    }

    @Override
    public boolean input_isMove(int number) {
        return false;
    }

    @Override
    public String input_command(int movableCnt, Cell curCell) {
        return null;
    }
}

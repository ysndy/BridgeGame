package Controller;

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
    public boolean input_isMove() {
        return false;
    }

    @Override
    public String input_command() {
        return null;
    }
}

package Controller;

import Model.Model;

public class CController extends Controller{

    public CController(Model model) {
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

        //알파벳을 잘 입력했는지 검사

        //이동가능횟수와 일치하는지 검사

        //실제 이동 가능한지 검사

        return null;
    }
}

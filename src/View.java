import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer {

    Model model;
    Controller controller;
    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
    }
    @Override
    public abstract void update(Observable o, Object arg);

    //플레이어 숫자 입력
    public abstract int input_playerNumber();
    protected boolean input_playerNumber_check(int input){
        if (input >= 2 && input <= 4) return true;
        return false;
    }

    //게임 모드 입력
    public abstract char input_gameMode();
    protected boolean input_gameMode_check(char input){
        if (input == controller.COMMAND_GUI || input == controller.COMMAND_CLI) return true;
        return false;
    }

    //행동 입력
    public abstract char input_turnMethod();
    protected boolean input_turnMethod_check(char input){
        if (input == 'G' || input == 'S' || input == 'g' || input == 's') {
            return true;
        }
        return false;
    };

    //이동 경로 입력
    public abstract String input_command();
    protected boolean input_command_check(String command) {

        //입력 값 맞는지 검사
        Character[] inputable = {'u', 'd', 'r', 'l', 'U', 'D', 'R', 'L'};
        ArrayList arrayList = new ArrayList(List.of(inputable));
        Cell cell = model.getBoard().getPieceCell(model.getTurn().getPlayer().getPiece());
        if (command.length() != model.getTurn().getMovableCnt()) {
            model.setWarningMsg("명령어 길이 불일치");
            return false;
        }
        for (int i = 0; i < command.length(); i++) {
            if (!arrayList.contains(command.charAt(i))) {
                model.setWarningMsg("명령어 유효성 검사 오류");
                return false;
            }
        }

        //이동 가능한지 검사
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            //뒤로 이동 불가모드일 경우
            if (model.isBackMode() && (cell.getBackDirection() == c)) {
                model.setWarningMsg("뒤로 이동 불가모드");
                return false;
            }

            //end셀일 경우
            if (cell instanceof EndCell) return true;

            switch (c) {
                case 'u':
                case 'U':
                    cell = model.getBoard().getUpCell(cell);
                    break;
                case 'd':
                case 'D':
                    cell = model.getBoard().getDownCell(cell);
                    break;
                case 'l':
                case 'L':
                    cell = model.getBoard().getLeftCell(cell);
                    break;
                case 'r':
                case 'R':
                    cell = model.getBoard().getRightCell(cell);
                    break;
            }
            if (cell == null) {
                model.setWarningMsg("i=" + i + ", c=" + c + ", 이동 가능 셀 없음");
                return false;
            }

        }

        return true;

    }

    //맵 넘버 입력
    public abstract int input_mapNumber(int mapNumber);
    protected boolean input_mapNumber_check(int input, int mapNumber){
        if (input < mapNumber && input >= 0) return true;
        return false;
    }


}

package ui;

import domain.*;
import domain.object.Player;
import domain.object.cell.Cell;
import domain.object.cell.EndCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer {

    Model model;
    Controller controller;
    private final String REQUEST_DEFAULT = "명령어를 올바르게 입력하세요";
    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
    }
    @Override
    public void update(Observable o, Object arg){

        print_init();
        if (model.getState() == Model.STATE_INIT) {
        } else if (model.getState() == Model.STATE_PLAY) {
            print_map();
            print_playerInfo(model.getPlayers());
            print_turnInfo();

        } else if (model.getState() == Model.STATE_END) {
            print_score(model.getPlayers());
        }

        print_helpMsg();
        print_warningMsg();
        print_end();

    }

    protected void print_init(){};
    protected abstract void print_playerInfo(Player[] players);
    protected abstract void print_turnInfo();
    protected abstract void print_score(Player[] players);
    protected abstract void print_map();
    protected abstract void print_helpMsg();
    protected abstract void print_warningMsg();
    protected void print_end(){};

    public int input_playerNumber() throws IOException {
        int playerNumber = input_playerNumber_logic();
        input_playerNumber_test(playerNumber);
        return playerNumber;
    }
    public char input_gameMode() throws IOException {
        char gameMode = input_gameMode_logic();
        input_gameMode_test(gameMode);
        return gameMode;
    }
    public String input_command() throws IOException {
        String command = input_command_logic();
        input_command_test(command);
        return command;
    }
    public char input_turnMethod() throws IOException {
        char turnMethod = input_turnMethod_logic();
        input_turnMethod_test(turnMethod);
        return turnMethod;
    }
    public int input_mapNumber(int mapNumber) throws IOException {
        int number = input_mapNumber_logic(mapNumber);
        input_mapNumber_test(number, mapNumber);
        return number;
    }

    public abstract Integer input_playerNumber_logic();
    public abstract Character input_gameMode_logic();
    public abstract String input_command_logic();
    public abstract Character input_turnMethod_logic();
    public abstract Integer input_mapNumber_logic(int mapNumber);

    protected void input_playerNumber_test (int input) throws IOException {
        if (!(input >= 2 && input <= 4)) throw new IOException(REQUEST_DEFAULT);
    }
    protected void input_gameMode_test(char input) throws IOException {
        if (!(input == 'G' || input == 'C' || input == 'g' || input == 'c')) throw new IOException(REQUEST_DEFAULT);
    }
    protected void input_turnMethod_test(char input) throws IOException{
        if (!(input == 'G' || input == 'S' || input == 'g' || input == 's')) throw new IOException(REQUEST_DEFAULT);
    }
    protected void input_command_test(String command) throws IOException{

        //입력 값 맞는지 검사
        Character[] inputable = {'u', 'd', 'r', 'l', 'U', 'D', 'R', 'L'};
        ArrayList arrayList = new ArrayList(List.of(inputable));
        Cell cell = model.getBoard().getPieceCell(model.getTurn().getPlayer().getPiece());
        if (command.length() != model.getTurn().getMovableCnt()) {
            throw new IOException("명령어 길이 불일치");
        }
        for (int i = 0; i < command.length(); i++) {
            if (!arrayList.contains(command.charAt(i))) {
                throw new IOException("명령어 유효성 검사 오류");
            }
        }

        //이동 가능한지 검사
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            //뒤로 이동 불가모드일 경우
            if (model.backMode.get() && (cell.getBackDirection() == c)) {
                throw new IOException("뒤로 이동 불가모드");
            }

            //end셀일 경우
            if (cell instanceof EndCell) return;

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
                throw new IOException("이동 가능 셀 없음");
            }

        }

    }
    protected void input_mapNumber_test(int input, int mapNumber) throws IOException{
        if (!(input < mapNumber && input >= 0)) throw new IOException(REQUEST_DEFAULT);
    }


}

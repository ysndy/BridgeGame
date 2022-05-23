package Controller;

import Model.Cells.Cell;
import Model.Model;
import Model.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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
    public String input_command(int movableCnt, Cell curCell) {

        while(true){

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
                System.out.println("명령어를 다시 입력해주세요");
                if(input_command_check(movableCnt, command, curCell)) return command;

            }catch (IOException e){
                return null;
            }
        }

        //알파벳을 잘 입력했는지 검사

        //이동가능횟수와 일치하는지 검사

        //실제 이동 가능한지 검사

    }

    private boolean input_command_check(int movableCnt, String command, Cell cell){

        //입력 값 맞는지 검사
        char[] inputable = {'u', 'd', 'r', 'l', 'U', 'D', 'R', 'L'};
        ArrayList arrayList = new ArrayList(Collections.singleton(inputable));
        if(command.length()!=movableCnt) return false;
        for(int i=0; i<command.length(); i++){
            if(!arrayList.contains(command.charAt(i))) return false;
        }

        //이동 가능한지 검사
        Board board = model.getBoard();
        for(int i=0; i<command.length(); i++){
            char c = command.charAt(i);

            //뒤로 이동 불가모드일 경우
            if(model.isBackMode()&&(cell.getBackDirection()==c)) return false;

            switch (c) {
                case 'u':
                case 'U':
                    cell = board.getUpCell(cell); break;
                case 'd':
                case 'D':
                    cell = board.getDownCell(cell); break;
                case 'l':
                case 'L':
                    cell = board.getLeftCell(cell); break;
                case 'r':
                case 'R':
                    cell = board.getRightCell(cell); break;
            }
            if(cell.equals(null)) return false;

        }

        return true;

    }

}

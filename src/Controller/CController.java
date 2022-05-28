package Controller;

import Model.Cells.Cell;
import Model.Cells.EndCell;
import Model.Model;
import Model.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CController extends Controller{

    public CController(Model model) {
        super(model);
    }

    @Override
    public int input_playerNo() {
        System.out.println("플레이어 숫자를 입력하세요");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(br.readLine());

        }catch (IOException e){
            return 0;
        }
    }

    @Override
    public boolean input_isMove(int number) {

        while(true){
            System.out.println("플레이어"+number+"의 차례입니다.\n쉬실려면 'S', 계속하시려면 'G'를 눌러주세요");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
                if(command.charAt(0)=='S') return false;
                if(command.charAt(0)=='G') return true;

            }catch (IOException e){
            }

        }
    }

    @Override
    public String input_command(int movableCnt, Cell curCell) {

        while(true){

            try {
                System.out.println("명령어를 입력해주세요");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
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
        Character[] inputable = {'u', 'd', 'r', 'l', 'U', 'D', 'R', 'L'};
        ArrayList arrayList = new ArrayList(List.of(inputable));
        if(command.length()!=movableCnt) {
            System.out.println("명령어 길이 불일치");
            return false;
        }
        for(int i=0; i<command.length(); i++){
            if(!arrayList.contains(command.charAt(i))) {
                System.out.println("명령어 유효성 검사 오류");
                return false;
            }
        }

        //이동 가능한지 검사
        Board board = model.getBoard();
        for(int i=0; i<command.length(); i++){
            char c = command.charAt(i);

            //뒤로 이동 불가모드일 경우
            if(model.isBackMode()&&(cell.getBackDirection()==c)) {
                System.out.println("뒤로 이동 불가모드");
                return false;
            }

            //end셀일 경우
            if(cell instanceof EndCell) return true;

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
            if(cell==null) {
                System.out.println("i="+i+", c="+c+", 이동 가능 셀 없음");

                return false;
            }

        }

        return true;

    }

}

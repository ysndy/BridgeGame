package View;

import Model.Cells.Cell;
import Model.Cells.DefaultCell;
import Model.Cells.EndCell;
import Model.Data;
import Model.Piece;
import Model.Cells.StartCell;
import Model.Model;

import java.util.ArrayList;

public class View {

    Model model;
    //인원 수를 입력하세요
    //지도 출력
    //(Model.Player.name)의 차례입니다. 쉬실려면 'S', 계속하시려면 'G'를 눌러주세요
    //만약 쉰다?
    //(Model.Player.name)의 차례입니다. 쉬실려면 'S', 계속하시려면 'G'를 눌러주세요
    //계속 한다?
    //주사위 결과: (1~6), 다리 카드 개수: (0~6), 이동 가능 칸수: (0~6)
    //이동 방향을 입력하세요. 왼쪽:L, 오른쪽:R, 위쪽:U, 아래쪽:D 예시: RRRDDD
    //잘못 입력?
    //이동 방향을 입력하세요. 왼쪽:L, 오른쪽:R, 위쪽:U, 아래쪽:D 예시: RRRDDD
    //잘 입력?
    //지도 출력

    public void request_pNum() {
        System.out.println("인원 수를 입력하세요");
    }

    public void request_howTurn() {

    }

    public void print_die(int number) {
        System.out.println("주사위 숫자는 [" + number + "] 입니다.");
    }

    public void print_movableCnt(int number) {
        System.out.println("이동 가능 횟수는 [" + number + "] 입니다.");
    }

    public void print_map() {
        Cell[][] map = model.getBoard().getMap();

        char[][] buffer = new char[map.length * 5][map[0].length * 5];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int y = i * 5;
                int x = j * 5;
                if (map[i][j].equals(null)) {
                    for (int n = 0; n < 5; n++) {
                        for (int l = 0; l < 5; l++) {
                            buffer[y + n][x + l] = ' ';
                        }
                    }
                } else {
                    ArrayList pieces = map[i][j].getPieces();
                    boolean[] playerChecked = new boolean[Data.PLAYER_MAX];
                    for (int k = 0; k < pieces.size(); k++) {
                        playerChecked[((Piece) pieces.get(k)).getPlayer().getNumber()] = true;
                    }


                    //1행
                    for (int k = 0; k < 5; k++) {
                        buffer[y][x + k] = '*';
                    }
                    //2행
                    y++;
                    buffer[y][x] = '*';

                    if (playerChecked[0]) buffer[y][x + 1] = '0';
                    else buffer[y][x + 1] = ' ';

                    buffer[y][x + 2] = ' ';

                    if (playerChecked[1]) buffer[y][x + 3] = '1';
                    else buffer[y][x + 3] = ' ';

                    buffer[y][x + 4] = '*';
                    //3행
                    y++;
                    if(map[i][j] instanceof StartCell){

                    }else if(map[i][j] instanceof EndCell){

                    }else if(map[i][j] instanceof DefaultCell){

                    }else {

                    }



                    //4행
                    y++;
                    buffer[y][x] = '*';

                    if (playerChecked[2]) buffer[y][x + 1] = '2';
                    else buffer[y][x + 1] = ' ';

                    buffer[y][x + 2] = ' ';

                    if (playerChecked[3]) buffer[y][x + 3] = '3';
                    else buffer[y][x + 3] = ' ';

                    buffer[y][x + 4] = '*';

                    //5행
                    y++;
                    for (int k = 0; k < 5; k++) {
                        buffer[y][x + k] = '*';
                    }
                }
            }
        }
    }
}

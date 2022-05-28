package View;

import Model.Cells.Cell;
import Model.Cells.DefaultCell;
import Model.Cells.EndCell;
import Model.Cells.StartCell;
import Model.*;

import java.util.ArrayList;
import java.util.Observable;

public class CLI extends View{

    public CLI(Model model) {
        super(model);
    }

    public void request_pNum() {
        System.out.println("인원 수를 입력하세요");
    }

    public void request_howTurn() {

    }

    public void print_item(Player[] players){

        for(int i=0; i<players.length; i++){
            Card[] cards = players[i].getCards();
            System.out.print("플레이어"+players[i].getNumber()+"의 카드 [");
            for(int j=0; j<cards.length; j++){
                System.out.print(cards[j].getName()+": "+cards[j].getCount()+"개 ");
            }
            System.out.print("Bridge: "+players[i].getBridgeCardCnt()+"개]");
            System.out.println();
        }
    }

    public void print_score(Player[] players){

        for(int i=0; i<players.length; i++){
            System.out.println("플레이어"+players[i].getNumber()+"의 점수는 "+players[i].getScore()+"입니다.");
        }

    }

    public void print_die(int number) {
        System.out.println("주사위 숫자는 [" + number + "] 입니다.");
    }

    public void print_movableCnt(int number) {
        System.out.println("이동 가능 횟수는 [" + number + "] 입니다.");
    }

    public void print_map() {
        Cell[][] map = model.getBoard().getMap();

        char[][] buffer = new char[map.length * 3][map[0].length * 5];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int y = i * 3;
                int x = j * 5;
                if (map[i][j]==null) {
                    for (int n = 0; n < 3; n++) {
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
//                    for (int k = 0; k < 5; k++) {
//                        buffer[y][x + k] = '*';
//                    }
                    //2행
                    //y++;
                    buffer[y][x] = '*';

                    if (playerChecked[0]) buffer[y][x + 1] = '0';
                    else buffer[y][x + 1] = '*';

                    buffer[y][x + 2] = '*';

                    if (playerChecked[1]) buffer[y][x + 3] = '1';
                    else buffer[y][x + 3] = '*';

                    buffer[y][x + 4] = '*';
                    //3행
                    y++;
                    if(map[i][j] instanceof StartCell){
                        buffer[y][x] = 'S';
                        buffer[y][x+1] = 'T';
                        buffer[y][x+2] = 'A';
                        buffer[y][x+3] = 'R';
                        buffer[y][x+4] = 'T';

                    }else if(map[i][j] instanceof EndCell){
                        buffer[y][x] = '*';
                        buffer[y][x+1] = 'E';
                        buffer[y][x+2] = 'N';
                        buffer[y][x+3] = 'D';
                        buffer[y][x+4] = '*';
                    }else if(map[i][j] instanceof DefaultCell){
                        buffer[y][x] = '*';
                        buffer[y][x+1] = ' ';
                        buffer[y][x+2] = ' ';
                        buffer[y][x+3] = ' ';
                        buffer[y][x+4] = '*';
                    }else {
                        buffer[y][x] = '*';
                        buffer[y][x+1] = ' ';
                        buffer[y][x+2] = map[i][j].getType();
                        buffer[y][x+3] = ' ';
                        buffer[y][x+4] = '*';
                    }



                    //4행
                    y++;
                    buffer[y][x] = '*';

                    if (playerChecked[2]) buffer[y][x + 1] = '2';
                    else buffer[y][x + 1] = '*';

                    buffer[y][x + 2] = '*';

                    if (playerChecked[3]) buffer[y][x + 3] = '3';
                    else buffer[y][x + 3] = '*';

                    buffer[y][x + 4] = '*';

                    //5행
//                    y++;
//                    for (int k = 0; k < 5; k++) {
//                        buffer[y][x + k] = '*';
//                    }
                }
            }
        }

        for(int i=0; i< buffer.length; i++){

            for(int j=0; j<buffer[i].length; j++){
                System.out.print(buffer[i][j]);
            }
            System.out.println();

        }

    }

    @Override
    public void print_help() {

    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("\033[H\033[2J");
        System.out.flush();

        print_map();
        print_item(model.getPlayers());


    }
}


import java.io.IOException;
import java.util.*;

public class CLI extends View {

    Scanner scanner;

    public CLI(Model model, Controller controller) {
        super(model, controller);
        scanner = new Scanner(System.in);
    }

    public void print_playerInfo(Player[] players) {

        for (int i = 0; i < players.length; i++) {
            //아이템 출력
            Card[] cards = players[i].getCards();
            System.out.print("플레이어" + players[i].getNumber() + "의 카드 [");
            for (int j = 0; j < cards.length; j++) {
                System.out.print(cards[j].getName() + ": " + cards[j].getCount() + "개 ");
            }
            System.out.print("Bridge: " + players[i].getBridgeCardCnt() + "개]");
            System.out.println();

        }
    }

    public void print_turnInfo() {
        System.out.println("현재 차례: 플레이어" + model.getTurn().getPlayer().getNumber());
        if(model.getTurn().getMovableCnt()!=null) System.out.println("이동 가능 횟수: " + model.getTurn().getMovableCnt());
    }

    public void print_score(Player[] players) {

        for (int i = 0; i < players.length; i++) {
            System.out.println("플레이어" + players[i].getNumber() + "의 점수는 " + players[i].getScore() + "입니다.");
        }

    }

    public void print_map() {
        Cell[][] map = model.getBoard().getMap();

        char[][] buffer = new char[map.length * 3][map[0].length * 5];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int y = i * 3;
                int x = j * 5;
                if (map[i][j] == null) {
                    for (int n = 0; n < 3; n++) {
                        for (int l = 0; l < 5; l++) {
                            buffer[y + n][x + l] = ' ';
                        }
                    }
                } else {
                    boolean[] playerChecked = map[i][j].playerNumberOfPieces();

                    //1행
                    buffer[y][x] = '*';

                    if (playerChecked[0]) buffer[y][x + 1] = '0';
                    else buffer[y][x + 1] = '*';

                    buffer[y][x + 2] = '*';

                    if (playerChecked[1]) buffer[y][x + 3] = '1';
                    else buffer[y][x + 3] = '*';

                    buffer[y][x + 4] = '*';

                    //2행
                    y++;
                    if (map[i][j] instanceof StartCell) {
                        buffer[y][x] = 'S';
                        buffer[y][x + 1] = 'T';
                        buffer[y][x + 2] = 'A';
                        buffer[y][x + 3] = 'R';
                        buffer[y][x + 4] = 'T';

                    } else if (map[i][j] instanceof EndCell) {
                        buffer[y][x] = '*';
                        buffer[y][x + 1] = 'E';
                        buffer[y][x + 2] = 'N';
                        buffer[y][x + 3] = 'D';
                        buffer[y][x + 4] = '*';
                    } else if (map[i][j] instanceof DefaultCell) {
                        buffer[y][x] = '*';
                        buffer[y][x + 1] = ' ';
                        buffer[y][x + 2] = ' ';
                        buffer[y][x + 3] = ' ';
                        buffer[y][x + 4] = '*';
                    } else {
                        buffer[y][x] = '*';
                        buffer[y][x + 1] = ' ';
                        buffer[y][x + 2] = map[i][j].getType();
                        buffer[y][x + 3] = ' ';
                        buffer[y][x + 4] = '*';
                    }

                    //3행
                    y++;
                    buffer[y][x] = '*';

                    if (playerChecked[2]) buffer[y][x + 1] = '2';
                    else buffer[y][x + 1] = '*';

                    buffer[y][x + 2] = '*';

                    if (playerChecked[3]) buffer[y][x + 3] = '3';
                    else buffer[y][x + 3] = '*';

                    buffer[y][x + 4] = '*';

                }
            }
        }

        for (int i = 0; i < buffer.length; i++) {

            for (int j = 0; j < buffer[i].length; j++) {
                if(j%5==0) System.out.print(" ");
                System.out.print(buffer[i][j]);
            }
            System.out.println();

        }

    }

    public void print_helpMsg() {
        if(model.getHelpMsg()!=null) System.out.println("도움말: " + model.getHelpMsg());
        model.setHelpMsgNull();
    }

    public void print_warningMsg() {
        if(model.getWarningMsg()!=null) System.out.println("경고: " + model.getWarningMsg());
        model.setWarningMsgNull();
    }

    @Override
    public void update(Observable o, Object arg) {
        //초기화
        System.out.println("\033[H\033[2J");
        System.out.flush();

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

    }

    @Override
    public int input_playerNumber() {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input_playerNumber_check(input)) return input;
                else throw new Exception();
            } catch (Exception e) {
                model.setWarningMsg("2~4 사이의 정수를 다시 입력해주세요");
            }
        }

    }

    @Override
    public char input_gameMode() {
        while (true) {
            try {
                char input = scanner.next().charAt(0);
                if (input_gameMode_check(input)) return input;
                else throw new Exception();
            } catch (Exception e) {
                model.setWarningMsg("G 또는 C를 다시 입력해주세요");
            }
        }
    }

    @Override
    public char input_turnMethod() {
        while (true) {
            try {
                char input = scanner.next().charAt(0);
                if (input_turnMethod_check(input)) return input;
                else throw new Exception();
            } catch (Exception e) {
                model.setWarningMsg("G 또는 S를 다시 입력해주세요");
            }
        }
    }

    @Override
    public String input_command() {
        while (true) {
            try {
                String command = scanner.next();
                if (input_command_check(command)) return command.toUpperCase(Locale.ROOT);
                else throw new IOException();

            } catch (IOException e) {
                model.setWarningMsg("올바른 명령어를 입력해주세요");
            }
        }
    }

    @Override
    public int input_mapNumber(int mapNumber) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input_mapNumber_check(input, mapNumber)) return input;
                else throw new IOException();

            } catch (IOException e) {
                model.setWarningMsg("올바른 명령어를 입력해주세요");
            }
        }


    }
}


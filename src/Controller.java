import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Controller {

    Model model;
    View view;
    final char COMMAND_GUI = 'G';
    final char COMMAND_CLI = 'C';

    public Controller() {

        init();
        play();
        end();
    }

    private void init() {

        Scanner scanner = new Scanner(System.in);
        model = new Model();
        view = new CLI(model, this);
        model.setState(Model.STATE_INIT);
        model.addObserver(view);

        //플레이어 세팅
        model.setHelpMsg("플레이어 숫자를 입력하세요 (2~4)");
        //model.setPlayers(scanner.nextInt());
        model.setPlayers(view.input_playerNumber());

        //보드 세팅
        File dir = new File("./map/");
        String[] fileNames = dir.list();
        String msg="map을 선택하세요\n";
        for(int i=0; i<fileNames.length; i++){
            msg+=(i+". "+fileNames[i]+"\n");
        }

        model.setHelpMsg(msg);
        model.getBoard().setMap(fileNames[view.input_mapNumber(fileNames.length)]);

        model.setHelpMsg("게임모드를 선택하세요 GUI: G, CUI: C");

        switch (view.input_gameMode()) {
            case COMMAND_GUI:
                view = new GUI(model, this);
                break;
        }

        //말 START에 위치
        model.setPiecesToStartCell();

        model.deleteObservers();
        model.addObserver(view);

    }

    private void play() {
        model.setState(Model.STATE_PLAY);
        ArrayList<Player> activePlayers;
        do {
            activePlayers = new ArrayList<Player>();
            Player[] players = model.getPlayers();
            for (int i = 0; i < players.length; i++) {
                if (!players[i].isEnd()) activePlayers.add(players[i]);
            }
            for (int i = 0; i < activePlayers.size(); i++) {

                model.createTurn(activePlayers.get(i));
                model.setHelpMsg("행동을 입력해주세요 G: 이동, S: 쉬기");

                char method = view.input_turnMethod();
                if (method == 'G' || method == 'g') {

                    //1. 주사위 굴림
                    Die die = new Die();
                    die.roll();
                    model.getTurn().setMovableCnt(die.getFaceValue());

                    if(model.getTurn().getMovableCnt()==0) continue;

                    //2. 이동 경로 입력
                    model.setHelpMsg("이동가능 횟수: "+model.getTurn().getMovableCnt()+" 이동 경로를 입력하세요 U/D/R/L");
                    String command = view.input_command();

                    //3. 이동
                    model.getTurn().move(command);

                    model.getTurn().setMovableCntNull();
                } else if (method == 'S' || method == 's') model.getTurn().stay();

                model.setWarningMsgNull();
                model.setHelpMsgNull();
            }
        } while (activePlayers.size() > 0);
    }

    private void end() {
        model.setState(Model.STATE_END);
        model.setHelpMsg("게임 종료");
    }


    public static void main(String[] args) {
        Controller controller = new Controller();

    }

}

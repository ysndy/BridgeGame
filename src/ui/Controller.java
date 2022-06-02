package ui;

import domain.Model;
import domain.object.Die;
import domain.object.Player;

import java.io.File;
import java.util.*;

public class Controller {

    Model model;
    View view;

    public Controller() {

        init();
        play();
        end();
    }

    private void init()  {

        model = new Model();
        view = new CLI(model, this);
        model.setState(Model.STATE_INIT);
        model.addObserver(view);

        //1. 플레이어 수 입력
        model.setHelpMsg("플레이어 수를 입력하세요 (2~4)");

        int playerNum;
        while(true){
            try {
                playerNum = view.input_playerNumber();
                break;
            } catch (Exception e) {
                model.setWarningMsg(e.getMessage());
            }
        }

        model.setPlayers(playerNum);

        //2. 보드 선택
        File dir = new File("./map/");
        String[] fileNames = dir.list();
        String msg = "map을 선택하세요\n";
        for (int i = 0; i < fileNames.length; i++) {
            msg += (i + ". " + fileNames[i] + "\n");
        }
        model.setHelpMsg(msg);
        int mapNumber;
        while(true){
            try {
                mapNumber = view.input_mapNumber(fileNames.length);
                break;
            } catch (Exception e) {
                model.setWarningMsg(e.getMessage());
            }
        }
        model.getBoard().setMap(fileNames[mapNumber]);

        //3. 게임 모드 선택
        model.setHelpMsg("게임모드를 선택하세요 GUI: G, CUI: C");
        char gameMode;
        while (true) {
            try {
                gameMode = view.input_gameMode();
                break;
            } catch (Exception e) {
                model.setWarningMsg(e.getMessage());
            }
        }
        if (gameMode == 'G' || gameMode == 'g') view = new GUI(model, this);
        model.deleteObservers();
        model.addObserver(view);

        //말 START에 위치
        model.setPiecesToStartCell();


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
            int activePlayerCnt = activePlayers.size();
            for (int i = 0; i < activePlayers.size(); i++) {
                if(activePlayerCnt==1) break;
                //4. 행동 입력
                model.createTurn(activePlayers.get(i));
                model.setHelpMsg("행동을 입력해주세요 G: 이동, S: 쉬기");
                char method;
                while (true) {
                    try {
                        method = view.input_turnMethod();
                        break;
                    } catch (Exception e) {
                        model.setWarningMsg(e.getMessage());
                    }
                }
                if (method == 'G' || method == 'g') {
                    //4.1. 이동
                    //주사위 굴림
                    Die die = new Die();
                    die.roll();
                    model.getTurn().setMovableCnt(die.getFaceValue());

                    if (model.getTurn().getMovableCnt() == 0) continue;

                    //4.1.1. 이동 경로 입력
                    model.setHelpMsg("이동가능 횟수: " + model.getTurn().getMovableCnt() + " 이동 경로를 입력하세요 U/D/R/L");
                    String command;

                    while (true) {
                        try {
                            command = view.input_command();
                            break;
                        } catch (Exception e) {
                            model.setWarningMsg(e.getMessage());
                        }
                    }

                    //이동
                    model.getTurn().move(command);
                    model.getTurn().setMovableCntNull();

                } else if (method == 'S' || method == 's') model.getTurn().stay(); //4.2. 쉼 //다리 카드 회수

                model.setWarningMsgNull();
                model.setHelpMsgNull();
                if(activePlayers.get(i).isEnd()) {
                    if(!model.backMode.get()) model.backMode.set(true);
                    activePlayerCnt--;
                }
            }
        } while (activePlayers.size() > 1);
    }

    private void end() {
        model.setState(Model.STATE_END);
        model.setHelpMsg("게임 종료");
    }


    public static void main(String[] args) {
        new Controller();
    }

}

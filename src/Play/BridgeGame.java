package Play;

import Controller.CController;
import Controller.Controller;
import Model.Cells.Cell;
import Model.Cells.On;
import Model.Cells.Passing;
import Model.Model;
import Model.Player;
import Model.Die;
import Model.Board;
import Model.Piece;
import View.View;

import java.util.ArrayList;

public class BridgeGame {

    Model model;
    Controller controller;
    View view;

    public BridgeGame(){

        init();
        play();
        end();

    }

    private void init(){
        model = new Model();
        controller = new CController(model);
        view = new View(model);

        //플레이어 숫자 세팅
        Player[] players = new Player[controller.input_playerNo()];
        for(int i=0; i<players.length; i++){
            players[i] = new Player(i);
        }
        model.setPlayers(players);

        //말 START에 위치
        Cell startCell = model.getBoard().getStartCell();
        for(int i=0; i< players.length; i++){
            startCell.arrive(players[i].getPiece());
        }

        //맵출력
        view.print_map();

    }

    private void play(){
        ArrayList<Player> activePlayers = new ArrayList<Player>();
        do {
            Player[] players = model.getPlayers();
            for (int i = 0; i < players.length; i++) {
                if (!players[i].isEnd()) activePlayers.add(players[i]);
            }
            for (int i = 0; i < activePlayers.size(); i++) {

                Player player = activePlayers.get(i);

                if (controller.input_isMove(player.getNumber())) {

                    //1. 주사위 굴림
                    Die die = new Die();
                    die.roll();
                    view.print_die(die.getFaceValue());

                    int movableCnt = die.getFaceValue()-player.getBridgeCardCnt();
                    //2. 이동가능 횟수 출력
                    view.print_movableCnt(movableCnt);

                    //3. 이동 경로 입력
                    Board board = model.getBoard();
                    Piece piece = player.getPiece();
                    Cell cell = board.getPieceCell(piece);
                    String command = controller.input_command(movableCnt, cell);

                    //4. 이동
                    char[] chars = command.toCharArray();
                    cell.leave(piece);

                    for(char c:chars){

                        switch (c) {

                            case 'U': cell = board.getUpCell(cell); break;
                            case 'D': cell = board.getDownCell(cell); break;
                            case 'R': cell = board.getRightCell(cell); break;
                            case 'L': cell = board.getLeftCell(cell); break;

                        }

                        if(cell instanceof Passing) ((Passing) cell).pass(piece);

                    }

                    //5. 도착
                    cell.arrive(piece);
                    if(cell instanceof On) ((On) cell).on(piece);

                } else {

                    player.removeBridge();

                }

                view.print_map();
                view.print_item(activePlayers);
                //플레이어의 아이템 개수 출력

            }
        }while(activePlayers.size()>0);
    }

    private void end(){

    }

    public static void main(String[] args) {
       BridgeGame bridgeGame = new BridgeGame();

    }

}

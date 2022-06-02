package domain;

import domain.object.Board;
import domain.object.Player;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Model extends Observable {

    private Board board;
    private Player[] players;
    public AtomicBoolean backMode;
    private Turn turn;
    private int state;
    private String helpMsg;
    private String warningMsg;
    public final static int STATE_INIT = 0;
    public final static int STATE_PLAY = 1;
    public final static int STATE_END = 2;

    public Model() {
        //보드 생성
        board = new Board();
        backMode = new AtomicBoolean(false);
    }

    public class Turn {

        private Player player;
        private Integer movableCnt;

        public Turn(Player player) {
            this.player = player;
            movableCnt = null;
        }

        public void stay() {
            player.removeBridge();
        }

        public void move(String command) {

            board.moveAPiece(player.getPiece(), command);
            setChanged();
            notifyObservers();

        }

        public void setMovableCnt(Integer dieNumber){
            if(dieNumber-player.getBridgeCardCnt()<0) movableCnt=0;
            else movableCnt = dieNumber-player.getBridgeCardCnt();

            setChanged();
            notifyObservers();
        }

        public void setMovableCntNull(){
            movableCnt = null;
        }

        public Player getPlayer() {
            return player;
        }

        public Integer getMovableCnt() {
            return movableCnt;
        }

    }

    public String getWarningMsg() {
        return warningMsg;
    }
    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg;
        setChanged();
        notifyObservers();
    }
    public void setWarningMsgNull(){
        warningMsg = null;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public String getHelpMsg() {
        return helpMsg;
    }
    public void setHelpMsg(String helpMsg) {
        this.helpMsg = helpMsg;
        setChanged();
        notifyObservers();
    }
    public void setHelpMsgNull(){
        helpMsg = null;
    }

    public Turn getTurn() {
        return turn;
    }
    public void createTurn(Player player) {
        turn = new Turn(player);
    }

    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(int playerNumber) {
        players = new Player[playerNumber];
        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player(i);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setPiecesToStartCell() {
        for (int i = 0; i < players.length; i++) {
            getBoard().getStartCell().arrive(players[i].getPiece());
        }

        setChanged();
        notifyObservers();

    }

}


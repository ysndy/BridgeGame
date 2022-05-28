package Model;

import Controller.*;

import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private Player[] players;
    private boolean backMode;

    public boolean isBackMode() {
        return backMode;
    }

    public void setBackMode(boolean backMode) {
        this.backMode = backMode;
    }

    public Model(){
        //보드 생성
        board = new Board();
        backMode = true;
    }

    public void setPlayers(Player[] players){
        this.players = players;
    }

    public Board getBoard(){
        return board;
    }

    public Player[] getPlayers(){
        return players;
    }
}

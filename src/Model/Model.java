package Model;

import Controller.*;

import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private Player[] players;

    public Model(){
        //보드 생성
        board = new Board();
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

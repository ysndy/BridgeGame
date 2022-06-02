package domain.object;

import domain.Data;

public class Player {
    public int getNumber() {
        return number;
    }

    private int number;
    private boolean isEnd;
    private Piece piece;
    private int bridgeCardCnt;
    private int score;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank;

    public Card[] getCards() {
        return cards;
    }

    private Card[] cards;

    public Player(int number){
        isEnd = false;
        piece = new Piece(this);
        bridgeCardCnt = 0;
        score = 0;
        cards = new Card[Data.CARDNAME.length];
        for(int i = 0; i< Data.CARDNAME.length; i++){
            cards[i] = new Card(Data.CARDNAME[i], Data.CARDSCORE[i]);
        }
        this.number = number;


    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;

    }

    public void addScore(int score){
        this.score+=score;
    }

    public int getScore(){
        return score;
    }

    public Piece getPiece(){
        return piece;
    }

    public void removeBridge(){
        if(bridgeCardCnt!=0) bridgeCardCnt--;
    }

    public int getBridgeCardCnt(){
        return bridgeCardCnt;
    }
    public void addBridgeCardCnt(){
        bridgeCardCnt++;
    }
}

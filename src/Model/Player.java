package Model;

public class Player {
    private String name;
    private boolean isEnd;
    private Piece piece;
    private int bridgeCardCnt;
    private Card[] cards;

    public Player(String name){
        isEnd = false;
        piece = new Piece(this);
        bridgeCardCnt = 0;
        cards = new Card[3];
        this.name = name;


    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public int getScore(){
        return 0;
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
}

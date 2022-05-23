package Model;

public class Player {
    public int getNumber() {
        return number;
    }

    private int number;
    private boolean isEnd;
    private Piece piece;
    private int bridgeCardCnt;

    public Card[] getCards() {
        return cards;
    }

    private Card[] cards;

    public Player(int number){
        isEnd = false;
        piece = new Piece(this);
        bridgeCardCnt = 0;
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
    public void addBridgeCardCnt(){
        bridgeCardCnt++;
    }
}

package domain.object.cell;

import domain.object.Card;
import domain.object.Piece;
import domain.object.Player;

public class ToolCell extends Cell implements On {

    public Card getCard() {
        return card;
    }

    private Card card;

    public ToolCell(Card card, int rank, char type){
        super(rank, type);
        this.card = card;
    }

    @Override
    public void on(Piece piece) {

        Player player = piece.getPlayer();
        Card[] cards = player.getCards();
        for(int i=0; i<cards.length; i++) {
            if (cards[i].getName().equals(card.getName())) cards[i].add();
        }
        player.addScore(card.getScore());
    }
}

package Model.Cells;

import Model.Card;
import Model.Piece;
import Model.Player;

public class ToolCell extends Cell implements On {

    Card card;

    public ToolCell(Card card, char backDirection, char type){
        super(backDirection, type);
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

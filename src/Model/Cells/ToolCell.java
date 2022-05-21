package Model.Cells;

import Model.Card;

public class ToolCell extends Cell implements Arriving {

    Card card;

    public ToolCell(Card card){
        this.card = card;
    }

    @Override
    public void arrive() {

    }
}

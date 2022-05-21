package Model.Cells;

public class BridgeEndCell extends Cell implements Arriving {

    @Override
    public void arrive() {
        for(int i=0; i<pieces.size(); i++){
            pieces.get(i).getPlayer().setEnd(true);
        }
    }
}

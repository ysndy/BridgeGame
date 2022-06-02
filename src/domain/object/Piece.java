package domain.object;

public class Piece {
    private Player player;

    public Piece(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}

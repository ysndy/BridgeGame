package domain.object;

public class Card {

    private String name;
    private int score;
    private int count;

    public Card(String name, int score){
        this.name = name;
        this.score = score;

    }

    public void add(){
        count++;
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getCount() {
        return count;
    }
}

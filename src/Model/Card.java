package Model;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

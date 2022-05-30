public class Die {

    private int faceValue;

    public void roll(){
        faceValue = (int)(Math.random()*6)+1;
    }

    public int getFaceValue(){
        return faceValue;
    }


    public static void main(String[] args) {
        Die die = new Die();
        die.roll();
        System.out.println(die.getFaceValue());
    }
}

package Model;

public class Die {

    private int faceValue;

    public void roll(){
        faceValue = (int)Math.random()*6;
    }

    public int getFaceValue(){
        return faceValue;
    }

}

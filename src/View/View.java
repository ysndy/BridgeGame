package View;

import Model.*;
import Model.Cells.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer{

    Model model;
    String help;

    public View(Model model){
        this.model = model;
    }

    public abstract void print_item(Player[] players);

    public abstract void print_score(Player[] players);

    public abstract void print_die(int number);

    public abstract void print_movableCnt(int number);

    public abstract void print_map();

    public abstract void print_help();

}

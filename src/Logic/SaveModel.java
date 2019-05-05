package Logic;

import model.*;

import java.util.List;

public class SaveModel {
    int Type;
    public List<ICrosser> left;
    public List<ICrosser> right;
    public List<ICrosser> Boat;
    int score;
    boolean isLeft ;
    SHIP choosenShip;
    FARMER choosenFarmer;
    HERBANIMAL choosenHerbAnimal;
    CARNANIMAL choosenCarnAnimal;
    PLANTS choosenPlants;
    public SaveModel(int type, List<ICrosser> left, List<ICrosser> right, List<ICrosser> boat, int score, boolean isLeft,
                     SHIP choosenShip, FARMER choosenFarmer,
                     HERBANIMAL choosenHerb, CARNANIMAL choosenCarn, PLANTS choosenPlants) {
        Type = type;
        this.left = left;
        this.right = right;
        Boat = boat;
        this.score = score;
        this.isLeft = isLeft;
        this.choosenShip = choosenShip;
        this.choosenFarmer = choosenFarmer;
        this.choosenHerbAnimal = choosenHerb;
        this.choosenCarnAnimal = choosenCarn;
        this.choosenPlants = choosenPlants;
    }

}

package Logic;

import java.util.List;

public class SaveModel {
    int Type;
    public List<ICrosser> left;
    public List<ICrosser> right;
    public List<ICrosser> Boat;
    int score;
    boolean isLeft ;

    public SaveModel(int type, List<ICrosser> left, List<ICrosser> right, List<ICrosser> boat, int score, boolean isLeft) {
        Type = type;
        this.left = left;
        this.right = right;
        Boat = boat;
        this.score = score;
        this.isLeft = isLeft;
    }

}

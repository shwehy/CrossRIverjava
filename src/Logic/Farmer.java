package Logic;

import model.FARMER;
import sample.ICrosser;

import java.awt.image.BufferedImage;

public class Farmer implements ICrosser {

    @Override
    public boolean canSail() {
        return true;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public int getEatingRank() {
        return 5;
    }

    @Override
    public void setLabelToBeShown(String label) {

    }

    @Override
    public String getLabelToBeShown() {
        return null;
    }
}

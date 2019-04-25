package Logic;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ICrosser {
    public boolean canSail();
    public double getWeight();

    public int eatRank();
    public BufferedImage[] getImages();
    public  ICrosser makeCopy();
    public void setLabelToBeShown(String label);
    public String getLabelToBeShown();

}

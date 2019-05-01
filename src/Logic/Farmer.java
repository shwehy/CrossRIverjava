package Logic;


import java.awt.image.BufferedImage;

public class Farmer implements ICrosser {
    int rank;
    public double weight;
    boolean CanS ;
    String label;
    BufferedImage x[];

    private Farmer instance;

    public Farmer (int rank , double weight ){
        this.rank = rank;
        this.weight = weight;
    }

    @Override
    public boolean canSail() {
        return true;
    }

    @Override
    public double getWeight() {
        System.out.println("Wight: "+weight);
        return this.weight;
    }

    @Override
    public int eatRank() {
        return rank;
    }

    @Override
    public BufferedImage[] getImages() {
        return new BufferedImage[0];
    }



    @Override
    public void setLabelToBeShown(String label) {
        this.label = label;
    }

    @Override
    public String getLabelToBeShown() {
        return "Farmer";
                //this.label;
    }


    public ICrosser makeCopy() {
        instance.rank = rank;
        instance.weight = getWeight();
        instance.label = getLabelToBeShown();
        instance.CanS = true;
        instance.getImages();
        return instance;

    }
}

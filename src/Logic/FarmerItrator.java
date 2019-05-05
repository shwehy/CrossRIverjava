package Logic;

import java.util.Iterator;

public class FarmerItrator implements Iterator<Farmer> {

    private Farmer [] farmers= new Farmer[4];
    private int index = 0;


    public FarmerItrator (Farmer[] farmers){
this.farmers = farmers;
    }

    @Override
    public boolean hasNext() {
        if(index >= farmers.length ||farmers[index]==null){
        return false;
        }
        return true;
    }

    @Override
    public Farmer next() {
        return farmers[index++];
    }
}


package Logic;

import sample.ICrosser;
import sample.ICrossingStrategy;

import java.util.List;

public class Story1Logic implements ICrossingStrategy {
    public void createCharcters(){
        Farmer farmer = new Farmer();
        
    }


    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        return false;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        return null;
    }

    @Override
    public String[] getInstructions() {
        return new String[0];
    }

}

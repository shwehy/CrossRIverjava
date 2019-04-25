package Logic;

import java.util.List;

public interface ICrosseingStrategy{
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders);
    public List<ICrosser> getIntialICrossers();
    public String[] getInstructions();

}

package Logic;

import java.util.List;

public interface IRiverCrossingController {
    public void newGame (ICrosseingStrategy gameStrategy);
    public void resetGame(int Case);
    public String[] getInstructions();
    public List<ICrosser> getCrossersOnRightBank();
    public List<ICrosser> getCrossersOnLeftBank();
    public boolean isBoatOnTheLeftBank();
    public int getNumberOfSails();
    public boolean canMove(List<ICrosser> crossers, boolean fromLeftTorightBank);
    public boolean doMove(List<ICrosser> crossers, boolean fromLeftTorightBank);
    public boolean canDo();
    public boolean canReDo();
    public void undo();
    public void redo();
    public void saveGame();
    public void loadGame();
}

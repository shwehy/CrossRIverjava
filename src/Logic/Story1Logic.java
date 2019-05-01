package Logic;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Story1Logic extends GameEngine implements ICrosseingStrategy {

private static Story1Logic single_Instance = null;

private Story1Logic(){}
    public static Story1Logic getInstance(){
        if(single_Instance == null)
            single_Instance = new Story1Logic();
        return single_Instance;
    }
        List<ICrosser> Win = getWinner();
    public  boolean eatRRank(int x ,int y){
        if( (y>x||x>y) && (x-y==1 || x-y==-1) ) {
            return true;
        }
        else return false;

}
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        if(!leftBankCrossers.isEmpty()) {
            for (int i = 0; i < leftBankCrossers.size(); i++) {
                for (int j = i; j < leftBankCrossers.size()-1; j++) {
                    if (eatRRank(leftBankCrossers.get(i).eatRank(), leftBankCrossers.get(j + 1).eatRank())) {
                        return false;
                    }
                }
            }
            return true;
        }
        if(!rightBankCrossers.isEmpty()){
            for (int i = 0; i < rightBankCrossers.size(); i++) {
                for (int j = i; j < rightBankCrossers.size()-1; j++) {
                    if (eatRRank(rightBankCrossers.get(i).eatRank(), rightBankCrossers.get(j + 1).eatRank())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    void setObjects(Farmer f , Planet p , Herbanimal H , Carnianimal C){
        this.farmer = f;
        this.planet = p;
        this.herbanimal = H;
        this.carnianimal = C;
    }
    public List<ICrosser>  getIntialICrossers() {
            List<ICrosser> Alist = new LinkedList<ICrosser>();
            Alist.add(farmer);
            Alist.add(planet);
            Alist.add(carnianimal);
            Alist.add(herbanimal);
            System.out.println(Alist);
            return Alist;
    }
    public List<ICrosser>  getWinner() {
        List<ICrosser> Alist = new LinkedList<ICrosser>();
        Alist.add(planet);
        Alist.add(carnianimal);
        Alist.add(herbanimal);
//        Alist.add(farmer);
        return Alist;
}

    public String[] getInstructions() {
        return new String[0];
    }

}

package Logic;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Story2Logic extends GameEngine implements ICrosseingStrategy {

    private static Story2Logic single_Instance = null;

    private Story2Logic(){}
    public static Story2Logic getInstance(){
        if(single_Instance == null)
            single_Instance = new Story2Logic();
        return single_Instance;
    }
    public  boolean willsink(double x ,double y){
        if( x+y > 100 ) {
            return true;
        }
        return false;

    }
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        if(boatRiders.size()==2){
            if (willsink(boatRiders.get(0).getWeight(), boatRiders.get(1).getWeight() ) ) {
                return false;
            }
            return true;
        }
        return true;
    }

    void setObjects(Farmer A , Farmer B, Farmer C, Farmer D, Herbanimal herbanimal){
        this.farmerA = A;
        this.farmerB = B;
        this.farmerC = C;
        this.farmerD = D;
        this.story2herbAnimal = herbanimal;
    }
    public List<ICrosser>  getIntialICrossers() {
            List<ICrosser> Alist = new LinkedList<ICrosser>();
            Alist.add(farmerA);
            Alist.add(farmerB);
            Alist.add(farmerC);
            Alist.add(farmerD);
            Alist.add(story2herbAnimal);
            System.out.println("List  " + Alist);
            return Alist;

    }
    public List<ICrosser>  getWinner() {
        List<ICrosser> Alist = new LinkedList<ICrosser>();
        Alist.add(farmerA);
        return Alist;
    }

    public String[] getInstructions() {
        return new String[0];
    }

}

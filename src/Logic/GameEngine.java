package Logic;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.*;
import javax.xml.bind.annotation.*;

public class GameEngine implements IRiverCrossingController {
    public Farmer farmer, farmerA, farmerB, farmerC, farmerD;
    public Planet planet;
    public SaveModel SAVEE;
    public Carnianimal carnianimal;
    public Herbanimal herbanimal, story2herbAnimal;
    public Stack ReDo;
    public Stack UnDo;
    private static GameEngine instance = null;
    public List<List<ICrosser>> Redo =  new LinkedList<List<ICrosser>>();
    public List<List<ICrosser>> Undo=  new LinkedList<List<ICrosser>>();

        boolean s = false;

    public List<ICrosser> storyOneList = new LinkedList<ICrosser>();
    public List<ICrosser> story1RightBankList = new LinkedList<ICrosser>();
    public List<ICrosser> story1LeftBankList = new LinkedList<ICrosser>();
    public List<ICrosser> boatRiders = new LinkedList<ICrosser>();

    private boolean letsGoButtonClicked;
    public int storyType;

    public void setStoryType(int x ){
        this.storyType = x;
    }

    public void newGame(ICrosseingStrategy gameStrategy) {
        if(storyType==1){
            this.farmer = new Farmer(10,0);
            this.planet = new Planet();
            this.carnianimal = new Carnianimal(0);
            this.herbanimal = new Herbanimal(0);
            this.UnDo = new Stack();
            this.ReDo = new Stack();
//            this.Redo = new LinkedList<<ICrosser >>();
            Story1Logic.getInstance().setObjects(this.farmer,this.planet,herbanimal,carnianimal);
            this.storyOneList = Story1Logic.getInstance().getIntialICrossers();
            this.story1RightBankList.addAll(storyOneList);
                    //= this.storyOneList;
            boatRiders = null;

        }
        else{
            this.farmerA = new Farmer(0,90);
            this.farmerB = new Farmer(0,80);
            this.farmerC = new Farmer(0,60);
            this.farmerD = new Farmer(0,40);
            this.story2herbAnimal = new Herbanimal(20);

            Story2Logic.getInstance().setObjects(this.farmerA,this.farmerB,this.farmerC,this.farmerD,this.story2herbAnimal);
            this.storyOneList = Story2Logic.getInstance().getIntialICrossers();
            this.story1RightBankList.addAll(storyOneList);
            boatRiders = null;
        }
    }

    public List<ICrosser> getWinnerfromLogic(){
        return Story1Logic.getInstance().getWinner();
    }
    public boolean isValidforBoat(List<ICrosser> boatRiders){
        if(boatRiders.contains(farmer)||boatRiders.contains(farmerA)||boatRiders.contains(farmerB)||boatRiders.contains(farmerC)||boatRiders.contains(farmerD))
            return true;
        return false;
    }
    public boolean isValidFromLogic(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders){
        System.out.println("I recieved: "+rightBankCrossers + leftBankCrossers + boatRiders);
        return Story1Logic.getInstance().isValid(rightBankCrossers,leftBankCrossers,boatRiders);
    }
    public boolean isValidFromLogic2(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders){
        System.out.println("I recieved: "+rightBankCrossers + leftBankCrossers + boatRiders);
        return Story2Logic.getInstance().isValid(rightBankCrossers,leftBankCrossers,boatRiders);
    }
    public void resetGame(int Case) {
        this.story1RightBankList = null;
        this.story1RightBankList = new LinkedList<ICrosser>();
        if(Case==1)
            this.story1RightBankList = Story1Logic.getInstance().getIntialICrossers();
        else
            this.story1RightBankList = Story2Logic.getInstance().getIntialICrossers();
        this.story1LeftBankList =null;
        this.boatRiders = null;
        this.boatRiders = new LinkedList<ICrosser>();
        this.story1LeftBankList = new LinkedList<ICrosser>();


    }

    public String[] getInstructions() {
        return new String[0];
    }

    public List<ICrosser> getCrossersOnRightBank() {
        return this.story1RightBankList;
    }

    public List<ICrosser> getCrossersOnLeftBank() {
        return story1LeftBankList;

    }
    public List<ICrosser> getCrossersOnboat() {
        return boatRiders;
    }
    public void setCrossersOnboat(List<ICrosser> boatRiders){
        this.boatRiders = boatRiders;
    }

    public void setLetsGoButtonClicked( boolean letsGoButtonClicked){
        this.letsGoButtonClicked = letsGoButtonClicked;
    }
    public boolean isBoatOnTheLeftBank() {
        if(letsGoButtonClicked)
            return false;
        return true;
    }

    public int getNumberOfSails() {
        return 0;
    }

    public boolean canMove(List<ICrosser> crossers, boolean fromLeftTorightBank) {
        return false;
    }

    public boolean doMove(List<ICrosser> crossers, boolean fromLeftTorightBank) {
        return false;
    }

    public boolean canDo() {
        return false;
    }

    public boolean canReDo() {

        if (!ReDo.empty()&&s)
            return true;
        else
            return false;
    }

    public void undo() {
        LinkedList<List<ICrosser>> Alist;
        if(!UnDo.empty())
        {
            Alist= (LinkedList<List<ICrosser>>)UnDo.pop();
            System.out.println("UndoSYSO"+Alist.get(0)+"222"+Alist.get(1));
            this.ReDo.push((LinkedList<List<ICrosser>>)Alist);
            Alist = null;
        }
        else
            System.out.println("FADYYAAAA");
    }

    public void redo() {
        LinkedList<List<ICrosser>> Alist ;
        if(canReDo())
        {
            Alist= (LinkedList<List<ICrosser>>) ReDo.pop();
            System.out.println("REDOSYSO"+Alist.get(0)+"222"+Alist.get(1));
            UnDo.push( (LinkedList<List<ICrosser>>) Alist);
            Alist =null;
        }
        else
            System.out.println("FADYAAA NEEEK");
    }

    public void saveGame() {

        SAVEXML s  = new SAVEXML();
        s.File_Save();

    }
    public void Set_Save_object(int type, List<ICrosser> left, List<ICrosser> right, List<ICrosser> boat, int score, boolean isLeft){
        this.SAVEE = new SaveModel(type, left, right, boat,score, isLeft );

    }
    public SaveModel Get_Save_object(){
        return this.SAVEE;
    }
    public void loadGame() {

    }
    public void  REDOLIST(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers){
        if(!s){
            IniStacks();
        }
        else{
//         List<List<ICrosser>> Alist = new LinkedList<>();
//         Alist.add(rightBankCrossers);
//         Alist.add(leftBankCrossers);

        UnDo.push(rightBankCrossers);
        UnDo.push(leftBankCrossers);


    }
    }
    public void IniStacks(){
        LinkedList<ICrosser> alist = new LinkedList<>();
        LinkedList<ICrosser> alistl = new LinkedList<>();
//        List<List<ICrosser>> Alist = new LinkedList<>();
        this.UnDo = null;
        this.ReDo =null;
        this.UnDo = new Stack();
        this.ReDo = new Stack();
        alist.add(farmer);
        alist.add(herbanimal);
        alist.add(carnianimal);
        alist.add(planet);
//        Alist.add(alist);
//        Alist.add(alistl);
        this.UnDo.push(alist);
        this.UnDo.push(alistl);
        s=true;
    }
    public static GameEngine setGameInstance(GameEngine e){
        return instance = e;
    }
    public static GameEngine getGameInstance(){
        return instance;
    }

}

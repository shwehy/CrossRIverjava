package model;

public enum FARMER {

    MAN("view/resources/farmerChooser/IdleFarmer.png",
            "view/resources/farmerChooser/DeadFarmer.png","view/resources/farmerChooser/JumpFarmer.png"),
    BOY("view/resources/farmerChooser/IdleBoy.png",
            "view/resources/farmerChooser/DeadBoy.png","view/resources/farmerChooser/JumpBoy.png"),
    GIRL("view/resources/farmerChooser/IdleGirl.png",
            "view/resources/farmerChooser/DeadGirl.png","view/resources/farmerChooser/JumpGirl.png");

    private String urlFarmer, urlDeadFarmer, urlJumpFarmer;
    private FARMER (String urlFarmer, String urlDeadFarmer, String urlJumpFarmer){

        this.urlFarmer = urlFarmer;
        this.urlDeadFarmer = urlDeadFarmer;
        this.urlJumpFarmer = urlJumpFarmer;
    }
    public String getUrl(){
        return this.urlFarmer;
    }

    public String getDeadFarmerUrl(){
        return this.urlDeadFarmer;
    }

    public String getJumpFarmerUrl(){
        return this.urlJumpFarmer;
    }


}
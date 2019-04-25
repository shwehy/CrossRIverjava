package model;

public enum CARNANIMAL {
    DIN("view/resources/carnivorousChooser/IdleDin.png",
            "view/resources/carnivorousChooser/JumpDin.png",
            "view/resources/carnivorousChooser/KillDin.png","view/resources/carnivorousChooser/DeadDin.png"),
    FOX("view/resources/carnivorousChooser/IdleFox.png",
            "view/resources/carnivorousChooser/JumpFox.png",
            "view/resources/carnivorousChooser/KillFox.png","view/resources/carnivorousChooser/DeadFox.png"),
    WILDCAT("view/resources/carnivorousChooser/IdleWildCat.png",
            "view/resources/carnivorousChooser/JumpWildCat.png",
            "view/resources/carnivorousChooser/KillWildCat.png","view/resources/carnivorousChooser/DeadWildCat.png");

    private String urlCarnAnimal,urlCarnSelectedAnimal,urlCarnKillAnimal, urlCarnDeadAnimal;
    private CARNANIMAL(String urlCarnAnimal, String urlCarnSelectedAnimal, String urlCarnKillAnimal, String urlCarnDeadAnimal){
        this.urlCarnAnimal = urlCarnAnimal;
        this.urlCarnSelectedAnimal = urlCarnSelectedAnimal;
        this.urlCarnKillAnimal = urlCarnKillAnimal;
        this.urlCarnDeadAnimal = urlCarnDeadAnimal;
    }
    public String getUrl(){
        return this.urlCarnAnimal;
    }
    public String getUrlherbSelectedAnimal(){
        return this.urlCarnSelectedAnimal;
    }
    public String getUrlherbDeadAnimal(){
        return this.urlCarnDeadAnimal;
    }
    public String getUrlherbKillAnimal(){
        return this.urlCarnKillAnimal;
    }

}

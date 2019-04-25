package model;

public enum PLANTS {
    BUSH("view/resources/plantsChooser/bush.png",
            "view/resources/plantsChooser/bushOrange.png"),
    BUSHSMALL("view/resources/plantsChooser/bushSmall.png",
            "view/resources/plantsChooser/bushSmallOrange.png"),
    TREE("view/resources/plantsChooser/treeSmall.png",
            "view/resources/plantsChooser/treeSmallOrange.png");

    private String urlplant,urlEatenPlant;
    private PLANTS(String urlplant, String urlEatenPlant){
        this.urlplant = urlplant;
        this.urlEatenPlant = urlEatenPlant;
    }
    public String getUrl(){
        return this.urlplant;
    }
    public String getUrlEatenPlant(){
        return this.urlEatenPlant;
    }

}

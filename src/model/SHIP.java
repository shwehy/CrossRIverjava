package model;

public enum SHIP {
    SMALL("view/resources/shipChooser/boat_large_E.png"),
    DARK("view/resources/shipChooser/ship_dark_E.png"),
    LIGHT("view/resources/shipChooser/ship_light_E.png"),
    Wreck("view/resources/shipChooser/ship_wreck_E.png");

    private String urlShip;
    private SHIP(String urlShip){
        this.urlShip = urlShip;
    }
    public String getUrl(){
        return this.urlShip;
    }
}

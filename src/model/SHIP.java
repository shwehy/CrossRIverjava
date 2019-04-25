package model;

public enum SHIP {
    SMALL("view/resources/shipChooser/boat_large_E.png", "view/resources/shipChooser/boat_large_W.png"),
    DARK("view/resources/shipChooser/ship_dark_E.png","view/resources/shipChooser/ship_dark_W.png"),
    LIGHT("view/resources/shipChooser/ship_light_E.png","view/resources/shipChooser/ship_light_W.png"),
    Wreck("view/resources/shipChooser/ship_wreck_E.png","view/resources/shipChooser/ship_wreck_W.png");

    private String urlShip, urlReversedShip;
    private SHIP(String urlShip, String urlReversedShip){
        this.urlShip = urlShip;
        this.urlReversedShip = urlReversedShip;
    }
    public String getUrl(){
        return this.urlShip;
    }
    public String getReversedShipUrl(){
        return this.urlReversedShip;
    }

}
package model;

public enum HERBANIMAL {
    BUFFALO("view/resources/HerbivorousChooser/buffalo.png",
            "view/resources/HerbivorousChooser/buffaloSelected.png","view/resources/HerbivorousChooser/buffaloDead.png"),
    CHICKEN("view/resources/HerbivorousChooser/chicken.png",
            "view/resources/HerbivorousChooser/chickenSelected.png","view/resources/HerbivorousChooser/chickenDead.png"),
    RABBIT("view/resources/HerbivorousChooser/rabbit.png",
            "view/resources/HerbivorousChooser/rabbitSelected.png","view/resources/HerbivorousChooser/rabbitDead.png");

    private String urlherbAnimal,urlherbSelectedAnimal,urlherbDeadAnimal;
    private HERBANIMAL(String urlherbAnimal, String urlherbSelectedAnimal, String urlherbDeadAnimal){
        this.urlherbAnimal = urlherbAnimal;
        this.urlherbSelectedAnimal = urlherbSelectedAnimal;
        this.urlherbDeadAnimal = urlherbDeadAnimal;
    }
    public String getUrl(){
        return this.urlherbAnimal;
    }
    public String getUrlherbSelectedAnimal(){
        return this.urlherbSelectedAnimal;
    }
    public String getUrlherbDeadAnimal(){
        return this.urlherbDeadAnimal;
    }
}

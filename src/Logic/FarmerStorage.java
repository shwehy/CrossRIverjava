package Logic;

public class FarmerStorage {
    private Farmer [] farmers= new Farmer[4];
    private int index = 0;
    public void add(Farmer f){
farmers[index]=f;
index++;
    }

    public Farmer[] getFarmers() {
        return farmers;
    }
}

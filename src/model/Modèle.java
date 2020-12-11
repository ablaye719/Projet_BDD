package model;

public class Modèle extends Entity{
    private int id;
    private String dénomination;
    private int puissanceFiscale;

    public Modèle(){this(0);}
    public Modèle(int id){
        this(id,null,0);
    }
    public Modèle(String dénomination){
        this.dénomination=dénomination;
    }

    public Modèle(int id, String dénomination,int puissanceFiscale){
        super();
        this.id=id;
        this.dénomination=dénomination;
        this.puissanceFiscale=puissanceFiscale;
    }

    public int getId() {
        return id;
    }

    public String getDénomination() {
        return dénomination;
    }

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDénomination(String dénomination) {
        this.dénomination = dénomination;
    }

    public void setPuissanceFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }
}

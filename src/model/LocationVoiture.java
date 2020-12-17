package model;

public class LocationVoiture extends Entity{
    private int idModele;
    private int idMarque;
    private int immatriculation;
    private double prix;
    private int idAgecnceretour;


    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getIdAgecnceretour() {
        return idAgecnceretour;
    }

    public void setIdAgecnceretour(int idAgecnceretour) {
        this.idAgecnceretour = idAgecnceretour;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

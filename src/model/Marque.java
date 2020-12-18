package model;

public class Marque extends Entity{
    private int idMarque;
    private String nomMarque;
    private int nbrevehiculesparmarque;

    public Marque(){}

    public Marque(int id) {
        idMarque = id;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public int getNbrevehiculesparmarque() {
        return nbrevehiculesparmarque;
    }

    public void setNbrevehiculesparmarque(int nbrevehiculesparmarque) {
        this.nbrevehiculesparmarque = nbrevehiculesparmarque;
    }
}

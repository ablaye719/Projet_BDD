package model;

public class Vehicule extends Entity{
    private String dateMiseEnCirculation;
    private String etat;
    private int nbKilometres;
    private int prixJourLocation;
    private int idMarque;
    private int idModele;
    private int idCategorie;
    private int idType;
    private int idAgence;

    public Vehicule(){}

    public String getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(String dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbKilometres() {
        return nbKilometres;
    }

    public void setNbKilometres(int nbKilometres) {
        this.nbKilometres = nbKilometres;
    }

    public int getPrixJourLocation() {
        return prixJourLocation;
    }

    public void setPrixJourLocation(int prixJourLocation) {
        this.prixJourLocation = prixJourLocation;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
}

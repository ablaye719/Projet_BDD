package model;

public class Vehicule extends Entity{
    private int immatriculation;
    private String dateMiseEnCirculation;
    private String etat;
    private double nbKilometres;
    private double prixJourLocation;
    private int idMarque;
    private int idModele;
    private int idCategorie;
    private int idType;
    private int idAgence;

    public Vehicule(){}

    public Vehicule(int id) {
        immatriculation = id;
    }

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

    public double getNbKilometres() {
        return nbKilometres;
    }

    public void setNbKilometres(double nbKilometres) {
        this.nbKilometres = nbKilometres;
    }

    public double getPrixJourLocation() {
        return prixJourLocation;
    }

    public void setPrixJourLocation(double prixJourLocation) {
        this.prixJourLocation = prixJourLocation;
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

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getIdMarque() {
        return idMarque;
    }
}

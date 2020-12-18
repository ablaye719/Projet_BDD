package model;

public class RetourDeVehicule extends Entity{
    private int immatriculation;
    private String dateDeRetrait;
    private String dateDeRetour;
    private String nomClient;

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDateDeRetrait() {
        return dateDeRetrait;
    }

    public void setDateDeRetrait(String dateDeRetrait) {
        this.dateDeRetrait = dateDeRetrait;
    }

    public String getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(String dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}

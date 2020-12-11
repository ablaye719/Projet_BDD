package model;

public class Agence extends Entity{
    private int idAgence;
    private int nbEmployes;
    private int idVille;

    public Agence(){}
    public Agence(int idAgence){
        this.idAgence = idAgence;
    }
    public Agence(int idAgence, int nbEmployes){
        this.idAgence = idAgence;
        this.nbEmployes = nbEmployes;
    }
    public Agence(int idAgence, int nbEmployes, int idVille){
        this.idAgence = idAgence;
        this.idVille = idVille;
        this.nbEmployes = nbEmployes;

    }
    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public int getNbEmployes() {
        return nbEmployes;
    }

    public void setNbEmployes(int nbEmployes) {
        this.nbEmployes = nbEmployes;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }
}

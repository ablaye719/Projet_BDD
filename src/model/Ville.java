package model;

public class Ville extends Entity{
    private int idVille;
    private String nomVille;
    private int nombreHabitants;

    public Ville(int i){
        idVille = i;
    }

    public Ville(int i, String nomVille, int i1) {
        idVille = i;
        this.nomVille = nomVille;
        nombreHabitants = i1;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getNombreHabitants() {
        return nombreHabitants;
    }

    public void setNombreHabitants(int nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }
}

package model;

public class Marque extends Entity{
    private int idMarque;
    private String nomMarque;

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
}

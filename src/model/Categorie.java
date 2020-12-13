package model;

public class Categorie extends Entity {

    private int id;
    private String libellé;

    public Categorie(){
        this(0);
    }
    public Categorie(int id){
        this(id,null);
    }
    public Categorie(String libellé){
        this.libellé=libellé;
    }
    public Categorie(int id, String libellé){
        super();
        this.id=id;
        this.libellé=libellé;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }
}

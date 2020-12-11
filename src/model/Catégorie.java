package model;

public class Catégorie extends Entity {

    private int id;
    private String libellé;

    public Catégorie(){
        this(0);
    }
    public Catégorie(int id){
        this(id,null);
    }
    public Catégorie(String libellé){
        this.libellé=libellé;
    }
    public Catégorie(int id,String libellé){
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

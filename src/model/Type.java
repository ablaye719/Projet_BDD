package model;

public class Type extends Entity {
    private int id;
    private String libellé;

    public Type(){
        this(0);
    }
    public Type(int id){
        this(id,null);
    }
    public Type(int id,String libellé){
        super();
        this.id = id;
        this.libellé = libellé;
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

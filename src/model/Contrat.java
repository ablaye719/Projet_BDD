package model;

public class Contrat extends Entity {
    private int id;
    private String dateDeRetrait;
    private String dateDeRetour;
    private float kmRetrait;
    private float kmRetour;
    private Client client;
    private Véhicule véhicule;
    private Agence agence;

    public Contrat(){
        this(0);
    }
    public Contrat(int id){
        this(id,null,null,0,0);
    }
    public Contrat(int id,String dateDeRetrait,String dateDeRetour,float kmRetrait,float kmRetour){
        this(id,dateDeRetrait,dateDeRetour,kmRetrait,kmRetour,null,null,null);
    }
    public Contrat(int id,String dateDeRetrait,String dateDeRetour,float kmRetrait,float kmRetour,Client client,Véhicule véhicule,Agence agence){
        super();
        this.id=id;;
        this.dateDeRetrait=dateDeRetrait;
        this.dateDeRetour=dateDeRetour;
        this.kmRetrait=kmRetrait;
        this.kmRetour=kmRetour;
        this.client=client;
        this.véhicule=véhicule;
        this.agence=agence;
    }

    public int getId() {
        return id;
    }

    public String getDateDeRetrait() {
        return dateDeRetrait;
    }

    public String getDateDeRetour() {
        return dateDeRetour;
    }

    public float getKmRetrait() {
        return kmRetrait;
    }

    public float getKmRetour() {
        return kmRetour;
    }

    public Client getClient() {
        return client;
    }

    public Véhicule getVéhicule() {
        return véhicule;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateDeRetrait(String dateDeRetrait) {
        this.dateDeRetrait = dateDeRetrait;
    }

    public void setDateDeRetour(String dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public void setKmRetrait(float kmRetrait) {
        this.kmRetrait = kmRetrait;
    }

    public void setKmRetour(float kmRetour) {
        this.kmRetour = kmRetour;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVéhicule(Véhicule véhicule) {
        this.véhicule = véhicule;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}

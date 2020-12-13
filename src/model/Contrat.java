package model;

public class Contrat extends Entity {
    private int id;
    private String dateDeRetrait;
    private String dateDeRetour;
    private float kmRetrait;
    private float kmRetour;
    private Client client;
    private Vehicule vehicule;
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
    public Contrat(int id,String dateDeRetrait,String dateDeRetour,float kmRetrait,float kmRetour,Client client,Vehicule vehicule,Agence agence){
        super();
        this.id=id;;
        this.dateDeRetrait=dateDeRetrait;
        this.dateDeRetour=dateDeRetour;
        this.kmRetrait=kmRetrait;
        this.kmRetour=kmRetour;
        this.client=client;
        this.vehicule=vehicule;
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

    public Vehicule getVehicule() {
        return vehicule;
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

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}

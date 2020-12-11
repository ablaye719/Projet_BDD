package model;

public class Facture extends Entity{
    private int id;
    private float montant;
    private Contrat contrat;

    public Facture(){
        this(0);
    }

    public Facture(int id){
        this(id,0);
    }
    public Facture(int id,float montant){
        this(id,montant,null);
    }

    public Facture(int id,float montant,Contrat contrat){
        super();
        this.id=id;
        this.montant=montant;
        this.contrat=contrat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}

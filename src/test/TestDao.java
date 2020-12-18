package test;

import dao.Dao;
import dao.exception.DaoException;
import dao.jdbc.*;
import model.*;
import sql.PostgresConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class TestDao {

    private final Connection connection = PostgresConnection.getInstance();
    private Dao dao;

    public void testFindAllVille(){
        System.out.println("Affichage des villes");
        dao = new VilleDaoImpl(connection);
        try {
            Collection<Entity> villes = dao.findAll();
            for(Entity entity: villes){
                Ville ville = (Ville) entity;
                System.out.println(ville.getIdVille()+"|"+ville.getNomVille()+ "|"+ville.getNombreHabitants());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindByIdVille(int id){
        dao = new VilleDaoImpl(connection);
        try {
            Ville ville = (Ville)dao.findById(id);
            System.out.println(ville.getIdVille()+"|"+ville.getNomVille()+ "|"+ville.getNombreHabitants());

        }catch (DaoException e) {
            e.printStackTrace();
        }

    }
    public void testCreateVille(Ville ville){
        System.out.println("Création d'une nouvelle ville");
        dao = new VilleDaoImpl(connection);
        try {
            dao.create(ville);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testUpdateVille(Ville ville){
        dao = new VilleDaoImpl(connection);
        try {
            dao.update(ville);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testDeleteVille(){
        dao = new VilleDaoImpl(connection);
        Ville ville = new Ville(6);
        try {
            dao.delete(ville);
            ville = (Ville) dao.findById(6);
            if (ville == null){
                System.out.println("Ville supprimée");
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindAllAgence(){
        dao = new AgenceDaoImpl(connection);
        Dao dao1 = new VilleDaoImpl(connection);
        try {
            Collection<Entity> agences = dao.findAll();
            for (Entity entity: agences){
                Agence agence = (Agence) entity;
                Ville ville = (Ville) dao1.findById(agence.getIdVille());
                System.out.println(agence.getIdAgence()+"|"+agence.getNbEmployes()+"|"+ville.getNomVille());

            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindAgenceByIdAgence(int id){
        dao = new AgenceDaoImpl(connection);
        Dao dao1 = new VilleDaoImpl(connection);
        try {
            Agence agence = (Agence) dao.findById(id);
            Ville ville = (Ville) dao1.findById(agence.getIdVille());
            System.out.println(agence.getIdAgence()+"|"+agence.getNbEmployes()+"|"+ville.getNomVille());
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testCreateAgence(Agence agence){
        dao = new AgenceDaoImpl(connection);
        try {
            dao.create(agence);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testUpdateAgence(Agence agence){
        dao = new AgenceDaoImpl(connection);
        try {
            dao.update(agence);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testDeleteAgence(){
        dao = new AgenceDaoImpl(connection);
        Agence agence = new Agence(5);
        try {
            dao.delete(agence);
            agence = (Agence) dao.findById(5);
            if(agence == null){
                System.out.println("agence5 supprimé");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindAllMarque(){
        dao = new MarqueDaoImpl(connection);
        try {
            Collection<Entity> marques = dao.findAll();
            for(Entity entity:marques){
                Marque marque = (Marque) entity;
                System.out.println(marque.getIdMarque()+"|"+marque.getNomMarque());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindByIdMarque(int id){
        dao = new MarqueDaoImpl(connection);
        try {
            Marque marque = (Marque) dao.findById(id);
            System.out.println(marque.getIdMarque()+"|"+marque.getNomMarque());

        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testCreateMarque(Marque marque){
        dao = new MarqueDaoImpl(connection);
        try {
            dao.create(marque);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testUpdateMarque(Marque marque){
        dao = new MarqueDaoImpl(connection);
        try {
            dao.update(marque);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testDeleteMarque(){
        dao = new MarqueDaoImpl(connection);
        Marque marque = new Marque(4);
        try {
            dao.delete(marque);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindAllClients(){
        dao = new ClientDaoImpl(connection);
        Dao dao1 = new VilleDaoImpl(connection);
        try {
            Collection<Entity> clients = dao.findAll();
            for(Entity entity: clients){
                Client client = (Client) entity;
                Ville ville = (Ville) dao1.findById(client.getIdVille());
                System.out.println(client.getIdClient()+"|"+client.getNomClient()+"|"+ville.getNomVille());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindByIdClient(int id){
        dao = new ClientDaoImpl(connection);
        Dao dao1 = new VilleDaoImpl(connection);
        try {
            Client client = (Client)dao.findById(id);
            Ville ville = (Ville) dao1.findById(client.getIdVille());
            System.out.println(client.getIdClient()+"|"+client.getNomClient()+"|"+ville.getNomVille());
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testCreateClient(Client client){
        dao = new ClientDaoImpl(connection);
        try {
            dao.create(client);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testUpdateClient(Client client){
        dao = new ClientDaoImpl(connection);
        try {
            dao.update(client);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testDelete(){
        Client client = new Client(4);
        try {
            dao.delete(client);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindAllVehicules(){
        dao = new VehiculeDaoImpl(connection);
        Dao dao1 = new MarqueDaoImpl(connection);
        Dao dao2 = new ModeleDaoImpl(connection);
        Dao dao3 = new TypeDaoImpl(connection);
        Dao dao4 = new CategorieDaoImpl(connection);
        Dao dao5 = new AgenceDaoImpl(connection);
        try {
            Collection<Entity> vehicules = dao.findAll();
            for (Entity entity:vehicules){
                Vehicule vehicule = (Vehicule) entity;
                Modele modele = (Modele) dao2.findById(vehicule.getIdModele());
                Marque marque = (Marque) dao1.findById(vehicule.getIdMarque());
                Type type = (Type) dao3.findById(vehicule.getIdType());
                Categorie categorie = (Categorie) dao4.findById(vehicule.getIdCategorie());
                Agence agence = (Agence) dao5.findById(vehicule.getIdAgence());
                System.out.println(vehicule.getImmatriculation()+"|"+vehicule.getDateMiseEnCirculation()+"|"+vehicule.getEtat()+"|"+vehicule.getNbKilometres()+"|"+vehicule.getPrixJourLocation()+"|"+marque.getNomMarque()+"|"+modele.getDenomination()+"|"+categorie.getLibellé()+"|"+type.getLibelle()+"|"+agence.getIdAgence());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testFindByIdVehicule(int id){
        dao = new VehiculeDaoImpl(connection);
        Dao dao1 = new MarqueDaoImpl(connection);
        Dao dao2 = new ModeleDaoImpl(connection);
        Dao dao3 = new TypeDaoImpl(connection);
        Dao dao4 = new CategorieDaoImpl(connection);
        Dao dao5 = new AgenceDaoImpl(connection);
        try {
            Vehicule vehicule = (Vehicule) dao.findById(id);
            Modele modele = (Modele) dao2.findById(vehicule.getIdModele());
            Marque marque = (Marque) dao1.findById(vehicule.getIdMarque());
            Type type = (Type) dao3.findById(vehicule.getIdType());
            Categorie categorie = (Categorie) dao4.findById(vehicule.getIdCategorie());
            Agence agence = (Agence) dao5.findById(vehicule.getIdAgence());
            System.out.println(vehicule.getImmatriculation()+"|"+vehicule.getDateMiseEnCirculation()+"|"+vehicule.getEtat()+"|"+vehicule.getNbKilometres()+"|"+vehicule.getPrixJourLocation()+"|"+marque.getNomMarque()+"|"+modele.getDenomination()+"|"+categorie.getLibellé()+"|"+type.getLibelle()+"|"+agence.getIdAgence());
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testCreateVehicule( Vehicule vehicule){
        dao = new VehiculeDaoImpl(connection);
        try {
            dao.create(vehicule);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testUpdateVehicule( Vehicule vehicule){
        dao = new VehiculeDaoImpl(connection);
        try {
            dao.update(vehicule);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testDeleteVehicule() {
        Vehicule vehicule = new Vehicule(7);
        try {
            dao.delete(vehicule);
            vehicule = (Vehicule) dao.findById(100);

            if (vehicule == null)
                System.out.println("Véhicule supprimé");

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testVille(){
        System.out.println("*****Liste des villes*****");
        testFindAllVille();
        System.out.println();

        System.out.println("*****Récupère la ville d'id = 2*****");
        testFindByIdVille(2);

        System.out.println("*****Création d'une ville");
        Ville ville = new Ville(11,"Dakar",3000000);
        testCreateVille(ville);
        testFindByIdVille(4);

        System.out.println("*****Modification de la ville 4");
        ville.setNomVille("Besançon");
        testUpdateVille(ville);
        testFindByIdVille(4);

        System.out.println("*****Suppression de la ville 2");
        testDeleteVille();
        System.out.println("*****Liste des villes*****");
        testFindAllVille();
        System.out.println();

        System.out.println("=====Affichage des véhicules====");
        testFindAllVehicules();


    }
    public void testAgence(){
        /**System.out.println("=====Affichage des agences=====");
        testFindAllAgence();

        System.out.println("=====Récupération de l'agence avec comme id=3=====");
        testFindAgenceByIdAgence(3);

        System.out.println("=====Création d'un agence=====");
        Agence agence = new Agence(5,34,2);
        testCreateAgence(agence);
        testFindAgenceByIdAgence(5);

        System.out.println("=====Modification de l'agence 5");
        agence.setNbEmployes(50);
        testFindAgenceByIdAgence(agence.getIdAgence());

        System.out.println("=====Supression de l'agence 5");
        testDeleteAgence();**/

    }
    public void faireUneLocation(){
        dao = new LocationVoitureDaoImpl(connection);
        Dao dao1 = new VehiculeDaoImpl(connection);
        Dao dao2 = new ModeleDaoImpl(connection);
        Dao dao3 = new MarqueDaoImpl(connection);
        Dao dao4 = new VilleDaoImpl(connection);
        Dao dao5 = new AgenceDaoImpl(connection);
        try {

            Collection<Entity> locations = dao.faireUneLocation();
            for (Entity entity:locations){
                LocationVoiture locationVoiture = (LocationVoiture) entity;
                Vehicule vehicule = (Vehicule) dao1.findById(locationVoiture.getImmatriculation());
                Modele modele = (Modele) dao2.findById(locationVoiture.getIdModele());
                Marque marque = (Marque) dao3.findById(locationVoiture.getIdMarque());
                Agence agence = (Agence) dao5.findById(locationVoiture.getIdAgecnceretour());
                Ville ville = (Ville) dao4.findById(agence.getIdVille());
                System.out.println(vehicule.getImmatriculation()+"|"+modele.getDenomination()+"|"+marque.getNomMarque()+"|"+agence.getIdAgence());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void retournerUneVoiture(){
        dao = new RetourDeVehiculeDaoImpl(connection);
        Dao dao1 = new VehiculeDaoImpl(connection);
        Dao dao2 = new MarqueDaoImpl(connection);
        try {
            Collection<Entity> retours = dao.retourdeVehicule();
            for(Entity entity:retours){
                RetourDeVehicule retourDeVehicule = (RetourDeVehicule) entity;
                Vehicule vehicule = (Vehicule) dao1.findById(retourDeVehicule.getImmatriculation());
                Marque marque = (Marque) dao2.findById(vehicule.getIdMarque());
                System.out.println(retourDeVehicule.getNomClient()+"|"+marque.getNomMarque()+"|"+retourDeVehicule.getDateDeRetrait()+"|");
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void facture(){
        dao = new FactureDaoImpl(connection);
        try {
            Collection<Entity> factures = dao.FaireUneFacture();
            for(Entity entity:factures){
                Facture facture = (Facture) entity;
                System.out.println(facture.getId()+"|"+facture.getMontant());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void nbVehiculesParMarque(){
        dao = new MarqueDaoImpl(connection);
        try {
            Collection<Entity> marques = dao.nbVehiculesParMarque();
            for (Entity entity : marques){
                Marque marque = (Marque) entity;
                System.out.println(marque.getIdMarque()+"|"+marque.getNbrevehiculesparmarque());
            }
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void chiffreAffaire(){
        dao = new FactureDaoImpl(connection);
        try {
            int chiffreAffaire = dao.chiffredaffaire();
            System.out.println(chiffreAffaire);
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void testRequetes(){
        System.out.println(("=====Une location====="));
        faireUneLocation();

        System.out.println(("=====Un retour de véhicule====="));
        retournerUneVoiture();
        System.out.println(("=====Une facture====="));
        facture();

        System.out.println(("=====Nombre de Véhicules par marques====="));
        nbVehiculesParMarque();

        System.out.println(("=====Chiffre d'affaire====="));
        chiffreAffaire();

    }

    public static void main(String[] args) throws DaoException {

       TestDao testDao = new TestDao();
       //testDao.testVille();
       testDao.testRequetes();
    }
}

package dao.jdbc;

import dao.Dao;
import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Contrat;
import model.Client;
import model.Vehicule;
import model.Agence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ContratDaoImpl extends dao.jdbc.JdbcDao {

    private jdbc.ClientDaoImpl clientDao;
    private jdbc.VehiculeDaoImpl véhiculeDao;
    private jdbc.AgenceDaoImpl agenceDao;


    public ContratDaoImpl(Connection connection) {
        super(connection);
        clientDao = new jdbc.ClientDaoImpl(connection);
        véhiculeDao = new jdbc.VehiculeDaoImpl(connection);
        agenceDao = new jdbc.AgenceDaoImpl(connection);


    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> contrats = new ArrayList<>();
        jdbc.ClientDaoImpl clientDao = new jdbc.ClientDaoImpl(connection);
        jdbc.VehiculeDaoImpl véhiculeDao = new jdbc.VehiculeDaoImpl(connection);
        jdbc.AgenceDaoImpl agenceDao = new jdbc.AgenceDaoImpl(connection);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Contrat");

            while (resultSet.next()) {
                Contrat contrat = new Contrat();
                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getString("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getString("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getFloat("kmRetrait"));
                contrat.setKmRetour(resultSet.getFloat("kmRetour"));

                Client client = (Client) clientDao.findById(resultSet.getInt("idClient"));
                contrat.setClient(client);
                Vehicule vehicule = (Vehicule) véhiculeDao.findById(resultSet.getString("immatriculation"));
                contrat.setVéhicule(vehicule);
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                contrat.setAgence(agence);


                contrats.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return contrats;


        // Il faudra ici, entres autres, utiliser la méthode findById de EntrepriseDaoImpl car un Etudiant
        // est composé d'un id, d'un nom et d'un objet Entreprise
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Contrat contrat = null;

        String sqlReq = "SELECT * FROM Contrat WHERE idContrat = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                contrat = new Contrat();
                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getString("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getString("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getFloat("kmRetrait"));
                contrat.setKmRetour(resultSet.getFloat("kmRetour"));

                Client client = (Client) clientDao.findById(resultSet.getInt("idClient"));
                contrat.setClient(client);
                Vehicule vehicule = (Vehicule) véhiculeDao.findById(resultSet.getString("immatriculation"));
                contrat.setVéhicule(vehicule);
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                contrat.setAgence(agence);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return contrat;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;

        PreparedStatement statement = null;

        String sqlReq = "insert into Contrat(idContrat, dateDeRetrait,dateDeRetour,kmRetrait,kmRetour) values (?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setInt(1, contrat.getId());
            statement.setString(2, contrat.getDateDeRetrait());
            statement.setString(3, contrat.getDateDeRetour());
            statement.setFloat(4, contrat.getKmRetrait());
            statement.setFloat(5, contrat.getKmRetour());



            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;

        PreparedStatement statement = null;

        String sqlReq = "update Contrat set idContrat = ?, idClient = ? where idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setString(1, contrat.getDateDeRetrait());
            statement.setInt(2, contrat.getClient().getIdClient());
            statement.setInt(3, contrat.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from Contrat where idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
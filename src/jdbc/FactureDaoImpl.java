package jdbc;

import dao.exception.DaoException;
import dao.jdbc.ContratDaoImpl;
import model.Entity;
import model.Facture;
import model.Contrat;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureDaoImpl extends dao.jdbc.JdbcDao {

    private ContratDaoImpl contratDao;

    public FactureDaoImpl(Connection connection) {
        super(connection);

    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> factures = new ArrayList<>();
        ContratDaoImpl contratDao = new ContratDaoImpl(connection);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Facture");

            while (resultSet.next()) {
                Facture facture = new Facture();
                facture.setId(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getFloat("montant"));
                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
                factures.add(facture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return factures;


        // Il faudra ici, entres autres, utiliser la méthode findById de EntrepriseDaoImpl car un Etudiant
        // est composé d'un id, d'un nom et d'un objet Entreprise
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Facture facture = null;

        String sqlReq = "SELECT * FROM Facture WHERE idFacture = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                facture = new Facture();
                facture.setId(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getFloat("montant"));

                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return facture;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;

        PreparedStatement statement = null;

        String sqlReq = "insert into Facture(idFacture, montant) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setInt(1, facture.getId());
            statement.setFloat(2, facture.getMontant());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;

        PreparedStatement statement = null;

        String sqlReq = "update Facture set idFacture = ?, idContrat = ? where idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setFloat(1, facture.getMontant());
            statement.setInt(2, facture.getContrat().getId());
            statement.setInt(3, facture.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from Facture where idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
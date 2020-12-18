package dao.jdbc;

import dao.exception.DaoException;
import model.Contrat;
import model.Entity;
import model.Facture;
import model.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureDaoImpl extends JdbcDao{
    private ContratDaoImpl contratDao;

    public FactureDaoImpl(Connection connection) {
        super(connection);
        contratDao = new ContratDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> factures = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM facture");

            while (resultSet.next()) {
                Facture facture = new Facture();
                facture.setId(resultSet.getInt("idfacture"));
                facture.setMontant(resultSet.getInt("montant"));

                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idcontrat"));
                facture.setContrat(contrat);

                factures.add(facture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return factures;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Facture facture = null;

        String sqlReq = "SELECT * FROM facture WHERE idfacture = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                facture = new Facture();
                facture.setId(resultSet.getInt("idfacture"));
                facture.setMontant(resultSet.getInt("montant"));

                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idcontrat"));
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

        String sqlReq = "insert into facture(idfacture, montant) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setInt(1, facture.getId());
            statement.setDouble(2, facture.getMontant());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;

        PreparedStatement statement = null;

        String sqlReq = "update facture set montant = ?, idcontrat = ? where idfacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setDouble(1, facture.getMontant());
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

        String sqlReq = "delete from facture where idfacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Collection<Entity> faireUneLocation() throws DaoException {
        return null;
    }

    @Override
    public Collection<Entity> retourdeVehicule() throws DaoException {
        return null;
    }

    @Override
    public Collection<Entity> FaireUneFacture() throws DaoException {
        Collection<Entity> factures = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String rq ="SELECT F.idFacture,F.montant, C.idclient, c.immatriculation\n" +
                    "FROM Facture as F\n" +
                    "INNER JOIN Contrat C on C.idContrat = F.idContrat\n" +
                    "WHERE C.dateDeRetrait = (SELECT max(c1.dateDEretrait)\n" +
                    "                       FROM Contrat as c1) ;";
            ResultSet resultSet = statement.executeQuery(rq);
            while (resultSet.next()){
                Facture facture = new Facture();
                facture.setId(resultSet.getInt("idfacture"));
                facture.setMontant(resultSet.getInt("montant"));
                factures.add(facture);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return factures;
    }

    @Override
    public Collection<Entity> nbVehiculesParMarque() throws DaoException {
        return null;
    }

    @Override
    public int chiffredaffaire() throws DaoException {
        int chiffreAffaire = 0;
        try {
            Statement statement =  connection.createStatement();
            String rq = "SELECT SUM(F.montant) AS ChiffreDaffaire\n" +
                    "FROM Facture as F\n" +
                    "INNER JOIN Contrat C on C.idContrat = F.idContrat\n" +
                    "WHERE C.dateDeRetrait BETWEEN '01-01-2020' AND '31-12-2020' AND C.idAgenceRetour = 1;";
            ResultSet resultSet = statement.executeQuery(rq);
            while (resultSet.next()){
                chiffreAffaire = resultSet.getInt("chiffredaffaire");
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return chiffreAffaire;
    }
}




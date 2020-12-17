package dao.jdbc;

import dao.exception.DaoException;
import model.Agence;
import model.Entity;
import model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceDaoImpl extends JdbcDao {

    public AgenceDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> agences = new ArrayList<>();
        AgenceDaoImpl agenceDao = new AgenceDaoImpl(connection);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM agence");
            while (resultSet.next()){
                Agence agence = new Agence();
                agence.setIdAgence(resultSet.getInt("idagence"));
                agence.setNbEmployes(resultSet.getInt("nbemployes"));
                VilleDaoImpl villeDao = new VilleDaoImpl(connection);
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idville"));
                agence.setIdVille(ville.getIdVille());
                agences.add(agence);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agences;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Agence agence = new Agence();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM agence WHERE idagence="+id);
            while (resultSet.next()){
                agence.setIdAgence(resultSet.getInt("idagence"));
                agence.setNbEmployes(resultSet.getInt("nbemployes"));
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return agence;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String rqSql = "INSERT INTO agence(idagence,nbemployes,idville) values(?,?,?)";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,agence.getIdAgence());
            statement.setInt(2,agence.getNbEmployes());
            statement.setInt(3,agence.getIdVille());
            int res = statement.executeUpdate();
        }catch (SQLException e) {
            System.err.println("Erreur SQL : " +e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String rqSql = "UPDATE agence SET nbemployes = ?, idville = ? WHERE idagence = ?";
        try{
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,agence.getNbEmployes());
            statement.setInt(2,agence.getIdVille());
        }catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String rqSql = "DELETE FROM agence WHERE idagence = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,agence.getIdAgence());
            int res = statement.executeUpdate();

        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

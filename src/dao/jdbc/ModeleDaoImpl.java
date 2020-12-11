package jdbc;//package dao.jdbc;

import dao.exception.DaoException;
import model.Entity;
import model.Modele;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleDaoImpl extends dao.jdbc.JdbcDao {

    public ModeleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> modèles = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Modèle");

            while (resultSet.next()) {
                Modele modèle = new Modele();
                modèle.setId(resultSet.getInt("idModèle"));
                modèle.setDénomination(resultSet.getString("dénomination"));
                modèle.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));

                modèles.add(modèle);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return modèles;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Modele modèle = null;

        String sqlReq = "SELECT * FROM Modèle WHERE idModèle = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modèle = new Modele();
                modèle.setId(resultSet.getInt("idModèle"));
                modèle.setDénomination(resultSet.getString("dénomination"));
                modèle.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return modèle;

    }

    @Override
    public void create(Entity entity) throws DaoException {

        Modele modèle = (Modele) entity;

        PreparedStatement stmt= null;

        String sqlReq = "insert into Modèle (idModèle,dénomination,puissanceFiscale) values (?,?,?)";

        try {

            stmt = connection.prepareStatement(sqlReq);

            // stmt.setInt(1, 5);

            stmt.setInt(1, modèle.getId());
            stmt.setString(2,modèle.getDénomination());
            stmt.setInt(3,modèle.getPuissanceFiscale());

            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Ligne insérée");
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());

        }

    }

    @Override
    public void update(Entity entity) throws DaoException {
        Modele modèle = (Modele) entity;

        PreparedStatement statement = null;

        String sqlReq = "update Modèle set dénomination = ? where idModèle = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setString(1, modèle.getDénomination());
            statement.setInt(2, modèle.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Modele modèle = (Modele) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from Modèle where idModèle = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modèle.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

}
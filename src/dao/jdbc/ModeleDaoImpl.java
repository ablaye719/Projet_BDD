package dao.jdbc;//package dao.jdbc;

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
        Collection<Entity> modeles = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM modele");

            while (resultSet.next()) {
                Modele modele = new Modele();
                modele.setId(resultSet.getInt("idModèle"));
                modele.setDenomination(resultSet.getString("dénomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));

                modeles.add(modele);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return modeles;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Modele modele = null;

        String sqlReq = "SELECT * FROM modele WHERE idmodele = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modele = new Modele();
                modele.setId(resultSet.getInt("idModèle"));
                modele.setDenomination(resultSet.getString("dénomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return modele;

    }

    @Override
    public void create(Entity entity) throws DaoException {

        Modele modele = (Modele) entity;

        PreparedStatement stmt= null;

        String sqlReq = "insert into modele (idmodele,denomination,puissanceFiscale) values (?,?,?)";

        try {

            stmt = connection.prepareStatement(sqlReq);

            // stmt.setInt(1, 5);

            stmt.setInt(1, modele.getId());
            stmt.setString(2,modele.getDenomination());
            stmt.setInt(3,modele.getPuissanceFiscale());

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

            statement.setString(1, modèle.getDenomination());
            statement.setInt(2, modèle.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Modèle where idModèle = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modele.getId());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }
}
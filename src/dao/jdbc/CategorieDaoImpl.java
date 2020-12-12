package jdbc;//package dao.jdbc;

import dao.exception.DaoException;
import model.Categorie;
import model.Entity;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieDaoImpl extends dao.jdbc.JdbcDao {

    public CategorieDaoImpl(Connection connection) {
        super(connection);
    }

    @Override public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> catégories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Type");

            while (resultSet.next()) {
                Categorie catégorie = new Categorie();
                catégorie.setId(resultSet.getInt("idCatégorie"));
                catégorie.setLibellé(resultSet.getString("LibelléCatégorie"));
                catégories.add(catégorie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return catégories;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Categorie catégorie = null;

        String sqlReq = "SELECT * FROM Catégorie WHERE idCatégorie = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                catégorie = new Categorie();
                catégorie.setId(resultSet.getInt("idCatégorie"));
                catégorie.setLibellé(resultSet.getString("LibelléCatégorie"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return catégorie;
    }

    @Override
    public void create(Entity entity) throws DaoException {

        Categorie catégorie = (Categorie) entity;

        PreparedStatement stmt= null;

        String sqlReq = "insert into Catégorie(idCatégorie,LibelléCatégorie) values (?,?)";

        try {

            stmt = connection.prepareStatement(sqlReq);

            // stmt.setInt(1, 5);
            stmt.setInt(1,catégorie.getId());
            stmt.setString(2, catégorie.getLibellé());

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
        Categorie catégorie = (Categorie) entity;

        PreparedStatement statement = null;

        String sqlReq = "update Catégorie set idCatégorie = ? where idCatégorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setString(1, catégorie.getLibellé());
            statement.setInt(2, catégorie.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Categorie catégorie = (Categorie) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from Catégorie where idCatégorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, catégorie.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

}
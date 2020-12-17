package dao.jdbc;//package dao.jdbc;

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
        Collection<Entity> categories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Type");

            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("idCategorie"));
                categorie.setLibelle(resultSet.getString("LibelleCategorie"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return categories;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Categorie categorie = null;

        String sqlReq = "SELECT * FROM Categorie WHERE idCategorie = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categorie = new Categorie();
                categorie.setId(resultSet.getInt("idCategorie"));
                categorie.setLibelle(resultSet.getString("LibelleCategorie"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return categorie;
    }

    @Override
    public void create(Entity entity) throws DaoException {

        Categorie catégorie = (Categorie) entity;

        PreparedStatement stmt= null;

        String sqlReq = "insert into Categorie(idCategorie,LibelleCategorie) values (?,?)";

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

        String sqlReq = "update Catégorie set idCatégorie = ? where idCategorie = ?";
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

        String sqlReq = "delete from Categorie where idCategorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, catégorie.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

}
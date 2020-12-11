package jdbc;//package dao.jdbc;

import dao.exception.DaoException;
import model.Entity;
import model.Type;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TypeDaoImpl extends dao.jdbc.JdbcDao {

    public TypeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> types = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Type");

            while (resultSet.next()) {
                Type type = new Type();
                type.setId(resultSet.getInt("idType"));
                type.setLibellé(resultSet.getString("libelléType"));
                types.add(type);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return types;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Type type = null;

        String sqlReq = "SELECT * FROM Type WHERE idType = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                type = new Type();
                type.setId(resultSet.getInt("idType"));
                type.setLibellé(resultSet.getString("libelléType"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return type;

    }

    @Override
    public void create(Entity entity) throws DaoException {

        Type type = (Type) entity;

        PreparedStatement stmt= null;

        String sqlReq = "insert into type (idType,libelléType) values (?,?)";

        try {

            stmt = connection.prepareStatement(sqlReq);

            // stmt.setInt(1, 5);
            stmt.setInt(1,type.getId());
            stmt.setString(2, type.getLibellé());

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
        Type type = (Type) entity;

        PreparedStatement statement = null;

        String sqlReq = "update Type set libelléType = ? where idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);

            statement.setString(1, type.getLibellé());
            statement.setInt(2, type.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Type type = (Type) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from Type where idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getId());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

}
package dao.jdbc;

import dao.Dao;
import dao.exception.DaoException;
import model.Client;
import model.Entity;
import model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaoImpl extends JdbcDao {
    public ClientDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> clients = new ArrayList<>();
        VilleDaoImpl villeDao = new VilleDaoImpl(connection);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville");
            while (resultSet.next()){
                Client client = new Client();
                client.setNomClient(resultSet.getString("nomclient"));
                client.setIdClient(resultSet.getInt("idclient"));
                client.setAdresseClient(resultSet.getString("adresseclient"));
                client.setCodePostalClient(resultSet.getString("codepostalclient"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idville"));
                client.setIdVille(ville.getIdVille());
                clients.add(client);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Client client = null;
        VilleDaoImpl villeDao = new VilleDaoImpl(connection);
        String rqSql = "SELECT * FROM client WHERE idclient ="+id;
        PreparedStatement statement = null;
        try {
            statement=connection.prepareStatement(rqSql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                client = new Client();
                client.setIdVille(resultSet.getInt("idville"));
                client.setNomClient(resultSet.getString("nomclient"));
                client.setAdresseClient(resultSet.getString("adresseclient"));
                client.setCodePostalClient(resultSet.getString("codepostalclient"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idville"));
                client.setIdVille(ville.getIdVille());

            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String rqSql = "INSERT INTO client(idclient,nomclient,adresseclient,codepostaclient,idville) VALUES(?,?,?,?,)?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,client.getIdClient());
            statement.setString(2,client.getNomClient());
            statement.setString(3,client.getAdresseClient());
            statement.setString(4,client.getCodePostalClient());
            statement.setInt(5,client.getIdVille());

        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String rqSql = "UPDATE client SET idclient = ?, nomclient = ?, adresseclient = ?, codepostalclient = ?, idville = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,client.getIdClient());
            statement.setString(2,client.getNomClient());
            statement.setString(3,client.getAdresseClient());
            statement.setString(4,client.getCodePostalClient());
            statement.setInt(5,client.getIdVille());
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String rqSql = "DELETE FROM client WHERE idclient = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,client.getIdClient());
            int res = statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

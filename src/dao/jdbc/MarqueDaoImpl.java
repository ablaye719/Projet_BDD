package dao.jdbc;

import dao.exception.DaoException;
import model.Entity;
import model.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MarqueDaoImpl extends JdbcDao {
    public MarqueDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> marques = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM marque");
            while (resultSet.next()){
                Marque marque = new Marque();
                marque.setIdMarque(resultSet.getInt("idmarque"));
                marque.setNomMarque(resultSet.getString("nommarque"));
                marques.add(marque);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return marques;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Marque marque = null;
        String rqSql = "SELECT * FROM marque WHERE idmarque = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                marque = new Marque();
                marque.setIdMarque(resultSet.getInt("idmarque"));
                marque.setNomMarque(resultSet.getString("nommarque"));
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return marque;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String rqSql = "INSERT INTO marque(idmarque, nommarque) VALUES(?,?)";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,marque.getIdMarque());
            statement.setString(2,marque.getNomMarque());
            int res = statement.executeUpdate();
            if(res > 0){
                System.out.println("Marque créé ");
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void update(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String rqSql = "UPDATE marque SET  nommarque = ? WHERE idmarque = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setString(1,marque.getNomMarque());
            statement.setInt(2,marque.getIdMarque());
            int res = statement.executeUpdate();
            if (res>0){
                System.out.println("Modification réussie");
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;

        PreparedStatement statement = null;

        String sqlReq = "delete from marque where idmarque = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, marque.getIdMarque());

            int res = statement.executeUpdate();
            if(res>0){
                System.out.println("Suppression réussie");
            }
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
        return null;
    }

    @Override
    public Collection<Entity> nbVehiculesParMarque() throws DaoException {
        Collection<Entity> marques = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String rq = "SELECT M.idMarque, count(V.idMarque) AS NbreVehiculesParMarque\n" +
                    "FROM Vehicule as V\n" +
                    "INNER JOIN Marque M on M.idMarque = V.idMarque\n" +
                    "GROUP BY M.idMarque\n";
            ResultSet resultSet = statement.executeQuery(rq);
            while (resultSet.next()){
                Marque marque = new Marque();
                marque.setIdMarque(resultSet.getInt("idmarque"));
                marque.setNbrevehiculesparmarque(resultSet.getInt("nbrevehiculesparmarque"));
                marques.add(marque);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return marques;
    }

    @Override
    public int chiffredaffaire() throws DaoException {
        return 0;
    }


}

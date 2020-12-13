package dao.jdbc;

import com.sun.source.tree.TryTree;
import dao.exception.DaoException;
import model.Entity;
import model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VilleDaoImpl extends JdbcDao{
    public VilleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> villes = new ArrayList<>();
        VilleDaoImpl villeDao = new VilleDaoImpl(connection);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville");
            while(resultSet.next()){
                Ville ville = new Ville();
                ville.setNombreHabitants(resultSet.getInt("nbhabitants"));
                ville.setNomVille(resultSet.getString("nomville"));
                ville.setIdVille(resultSet.getInt("idville"));
                villes.add(ville);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return villes;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Ville ville = new Ville();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville WHERE idville="+id);
            while (resultSet.next()){
                ville.setIdVille(resultSet.getInt("idville"));
                ville.setNomVille(resultSet.getString("nomville"));
                ville.setNombreHabitants(resultSet.getInt("nbhabitants"));

            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return ville;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String rqSql = "INSERT INTO ville(idville,nomville, nombrehabitants) VALUES(?,?,?)";
        try {
            statement  = connection.prepareStatement(rqSql);
            int res = statement.executeUpdate();
            statement.setInt(1,ville.getIdVille());
            statement.setString(2,ville.getNomVille());
            statement.setInt(3,ville.getNombreHabitants());
            if(res>0){
                System.out.println("Nouvelle ville insérée");
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }

    }

    @Override
    public void update(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String rqSql = "UPDATE ville SET idville = ?, nomville = ?, nombreehabitants =  ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,ville.getIdVille());
            statement.setString(2,ville.getNomVille());
            statement.setInt(3,ville.getNombreHabitants());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String rqSql = "DELETE FROM ville WHERE idville = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,ville.getIdVille());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

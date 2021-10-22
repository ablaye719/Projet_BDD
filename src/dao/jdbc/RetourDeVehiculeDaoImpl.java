package dao.jdbc;
import dao.exception.DaoException;
import model.Entity;
import model.RetourDeVehicule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class RetourDeVehiculeDaoImpl extends JdbcDao{
    public RetourDeVehiculeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        return null;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(Entity entity) throws DaoException {

    }

    @Override
    public void update(Entity entity) throws DaoException {

    }

    @Override
    public void delete(Entity entity) throws DaoException {

    }

    @Override
    public Collection<Entity> faireUneLocation() throws DaoException {
        return null;
    }

    @Override
    public Collection<Entity> retourdeVehicule() throws DaoException {
        Collection<Entity> retours  = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String rq = "SELECT  V.immatriculation, C.dateDeRetrait, C1.nomClient\n" +
                    "FROM Vehicule as V\n" +
                    "INNER JOIN Contrat C on V.immatriculation = C.immatriculation\n" +
                    "INNER JOIN Client c1 on c1.idclient = C.idclient\n" +
                    "WHERE C.dateDeRetrait = (SELECT  max(C2.dateDeRetrait)\n" +
                    "                       FROM Contrat AS C2)\n" +
                    "GROUP BY V.immatriculation, C.dateDeRetrait, C.dateDeRetour, C1.nomClient;";
            ResultSet resultSet = statement.executeQuery(rq);
            while (resultSet.next()){
                RetourDeVehicule retourDeVehicule = new RetourDeVehicule();
                //retourDeVehicule.setDateDeRetour(resultSet.getString(" datederetour"));
                retourDeVehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                retourDeVehicule.setNomClient(resultSet.getString("nomclient"));
                retourDeVehicule.setDateDeRetrait(resultSet.getString("datederetrait"));
                retours.add(retourDeVehicule);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return retours;
    }

    @Override
    public Collection<Entity> FaireUneFacture() throws DaoException {
        return null;
    }

    @Override
    public Collection<Entity> nbVehiculesParMarque() throws DaoException {
        return null;
    }

    @Override
    public int chiffredaffaire() throws DaoException {
        return 0;
    }


}

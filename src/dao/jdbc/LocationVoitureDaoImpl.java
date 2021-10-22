package dao.jdbc;

import dao.Dao;
import dao.exception.DaoException;
import model.Entity;
import model.LocationVoiture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class LocationVoitureDaoImpl extends JdbcDao {
    private MarqueDaoImpl marqueDao;
    private ModeleDaoImpl modeleDao;
    private AgenceDaoImpl agenceDao;
    private VehiculeDaoImpl vehiculeDao;

    public LocationVoitureDaoImpl(Connection connection){
        super(connection);
        agenceDao = new AgenceDaoImpl(connection);
        marqueDao = new MarqueDaoImpl(connection);
        modeleDao = new ModeleDaoImpl(connection);
        vehiculeDao = new VehiculeDaoImpl(connection);

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

        Collection<Entity> locations  = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String rq = "SELECT  v.idmodele,v.idmarque, v.immatriculation,v.prixJourDeLocation, c.idAgenceRetour\n" +
                    "FROM vehicule as v\n" +
                    "INNER JOIN Modele M on v.idModele = M.idModele\n" +
                    "INNER JOIN Contrat C on v.immatriculation = C.immatriculation\n" +
                    "INNER JOIN Agence A on A.idAgence = v.idAgence\n" +
                    "INNER JOIN Client c1 on c1.idclient = C.idclient\n" +
                    "WHERE c1.nomClient = 'titi' and C.dateDeRetrait ='24-10-2020'and C.dateDeRetour = '17-11-2020' and A.idAgence != C.idAgenceRetour;";
            ResultSet resultSet = statement.executeQuery(rq);
            while (resultSet.next()){
                LocationVoiture locationVoiture = new LocationVoiture();
                locationVoiture.setIdModele(resultSet.getInt("idmodele"));
                locationVoiture.setIdMarque(resultSet.getInt("idmarque"));
                locationVoiture.setIdAgecnceretour(resultSet.getInt("idagenceretour"));
                locationVoiture.setImmatriculation(resultSet.getInt("immatriculation"));
                locationVoiture.setPrix(resultSet.getDouble("prixjourdelocation"));
                locations.add(locationVoiture);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return locations;
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
        return null;
    }

    @Override
    public int chiffredaffaire() throws DaoException {
        return 0;
    }


}

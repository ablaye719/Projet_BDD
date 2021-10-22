package dao.jdbc;

import dao.exception.DaoException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class VehiculeDaoImpl extends JdbcDao{
    private MarqueDaoImpl marqueDao;
    private ModeleDaoImpl modeleDao;
    private CategorieDaoImpl categorieDao;
    private TypeDaoImpl typeDao;
    private AgenceDaoImpl agenceDao;
    public VehiculeDaoImpl(Connection connection) {
        super(connection);
        marqueDao = new MarqueDaoImpl(connection);
        modeleDao = new ModeleDaoImpl(connection);
        categorieDao = new CategorieDaoImpl(connection);
        typeDao = new TypeDaoImpl(connection);
        agenceDao = new AgenceDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> vehicules = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicule");
            while (resultSet.next()){
                Vehicule vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("datemiseencirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getDouble("nbkilometres"));
                vehicule.setPrixJourLocation(resultSet.getDouble("prixjourdelocation"));
                Marque marque = (Marque) marqueDao.findById(resultSet.getInt("idmarque"));
                vehicule.setIdMarque(marque.getIdMarque());
                Modele modele = (Modele) modeleDao.findById(resultSet.getInt("idmodele"));
                vehicule.setIdModele(modele.getId());
                Categorie categorie = (Categorie) categorieDao.findById(resultSet.getInt("idcategorie"));
                vehicule.setIdCategorie(categorie.getId());
                Type type = (Type) typeDao.findById(resultSet.getInt("idtype"));
                vehicule.setIdType(type.getId());
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idagence"));
                vehicule.setIdAgence(agence.getIdAgence());
                vehicules.add(vehicule);
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
        return vehicules;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Vehicule vehicule = null;
        String rqSql = "SELECT * FROM vehicule WHERE immatriculation = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("datemiseencirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getDouble("nbkilometres"));
                vehicule.setPrixJourLocation(resultSet.getDouble("prixjourdelocation"));
                Marque marque = (Marque) marqueDao.findById(resultSet.getInt("idmarque"));
                vehicule.setIdMarque(marque.getIdMarque());
                Modele modele = (Modele) modeleDao.findById(resultSet.getInt("idmodele"));
                vehicule.setIdModele(modele.getId());
                Categorie categorie = (Categorie) categorieDao.findById(resultSet.getInt("idcategorie"));
                vehicule.setIdCategorie(categorie.getId());
                Type type = (Type) typeDao.findById(resultSet.getInt("idtype"));
                vehicule.setIdType(type.getId());
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idagence"));
                vehicule.setIdAgence(agence.getIdAgence());
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }

        return vehicule;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String rqSql = "INSERT INTO vehicule(immatriculation,datemiseencirculation,etat,nbkilometres,prixjourlocation,idmarque,idmodele,idcategorie,idtype,idagence) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,vehicule.getImmatriculation());
            statement.setString(2,vehicule.getEtat());
            statement.setString(3,vehicule.getDateMiseEnCirculation());
            statement.setDouble(4,vehicule.getPrixJourLocation());
            statement.setInt(5, vehicule.getIdMarque());
            statement.setInt(6,vehicule.getIdModele());
            statement.setInt(7,vehicule.getIdType());
            statement.setInt(8,vehicule.getIdAgence());
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String rqSql = "UPDATE vehicule SET datemiseencirculation = ?, etat = ?, nbkilometres = ?, prixjourlocation = ?, idmaarque = ?, idmodele = ?, idcategorie = ?, idtype = ?, idagence = ? WHERE immatriculation = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(8,vehicule.getImmatriculation());
            statement.setString(1,vehicule.getEtat());
            statement.setString(2,vehicule.getDateMiseEnCirculation());
            statement.setDouble(3,vehicule.getPrixJourLocation());
            statement.setInt(4, vehicule.getIdMarque());
            statement.setInt(5,vehicule.getIdModele());
            statement.setInt(6,vehicule.getIdType());
            statement.setInt(7,vehicule.getIdAgence());
        }catch (SQLException e) {
            throw new DaoException(e);
        }


    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String rqSql = "DELETE FROM vehicule WHERE immatriculation = ?";
        try {
            statement = connection.prepareStatement(rqSql);
            statement.setInt(1,vehicule.getImmatriculation());
            int res = statement.executeUpdate();

        }catch (SQLException e) {
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
        return null;
    }

    @Override
    public int chiffredaffaire() throws DaoException {
        return 0;
    }


}

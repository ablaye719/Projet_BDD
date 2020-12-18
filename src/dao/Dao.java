package dao;

import dao.exception.DaoException;
import model.Entity;

import java.util.Collection;
import java.util.Date;

public interface Dao {
    Collection<Entity> findAll() throws DaoException;
    Entity findById(int id) throws DaoException;
    void create(Entity entity) throws DaoException;
    void update(Entity entity) throws DaoException;
    void delete(Entity entity) throws DaoException;
    Collection<Entity> faireUneLocation() throws DaoException;
    Collection<Entity> retourdeVehicule() throws DaoException;
    Collection<Entity> FaireUneFacture() throws DaoException;
    Collection<Entity> nbVehiculesParMarque() throws DaoException;
    int chiffredaffaire() throws DaoException;
}

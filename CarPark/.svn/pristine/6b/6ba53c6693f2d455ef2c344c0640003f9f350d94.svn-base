package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

/**
 * @author Tomas Svrcek
 */
@Repository
public class CarDAOImpl implements CarDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public Car getCarById(Long id) {

        return entityManager.find(Car.class, id);
    }

    public Car getCarBySpz(String spz) {
        if (spz == null) {
            throw new IllegalArgumentException("You have to set spz.");
        }

        Query q = entityManager.createNamedQuery(Car.FIND_BY_SPZ);
        q.setParameter("spz", spz);
        return (Car) q.getSingleResult();
    }

    public List<Car> getAllCars() {

        TypedQuery<Car> q = entityManager.createQuery("SELECT e FROM Car e", Car.class);
        return q.getResultList();
    }

    public void insert(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("You have to set car");
        }
        entityManager.persist(car);

    }

    public void remove(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("You have to set car");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity doesn't have ID");
        }
        entityManager.remove(entityManager.merge(car));

    }

    public void update(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("You have to specify car");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity doesn't have ID");
        }
        entityManager.merge(car);

    }

    public List<Car> getAllCarsWithHigherLevel(CompanyLevel companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("You have to specify company level");
        }
        if (companyLevel.getId() == null) {
            throw new IllegalArgumentException("Company level must have specified ID");
        }

        TypedQuery<Car> q = entityManager.createQuery("SELECT e FROM Car e INNER JOIN FETCH e.companyLevel as c WHERE (c.levelValue >= :companyLevelValue)", Car.class);
        q.setParameter("companyLevelValue", companyLevel.getLevelValue());
        return q.getResultList();
    }
}

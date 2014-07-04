package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduard Krak
 */
@Repository
public class ReservationDAOImpl implements ReservationDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public ReservationDAOImpl() {
    }

    @Override
    public Reservation getReservationById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public void insert(Reservation r) {
        if (r == null) {
            throw new IllegalArgumentException("You must specify reservation to insert");
        }
        entityManager.persist(r);
    }

    @Override
    public void update(Reservation r) {
        if (r == null) {
            throw new IllegalArgumentException("You must specify reservation to update");
        }
        if (r.getId() == null) {
            throw new IllegalArgumentException("Reservation to update must have specified ID");
        }
        entityManager.merge(r);
    }

    @Override
    public void remove(Reservation r) {
        if (r == null) {
            throw new IllegalArgumentException("You must specify reservation to remove");
        }
        if (r.getId() == null) {
            throw new IllegalArgumentException("Reservation to remove must have specified ID");
        }
        entityManager.remove(entityManager.merge(r));
    }

    @Override
    public List<Reservation> getReservationByCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("You must specify reservation car");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Car must have specified ID");
        }
        Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR);
        q.setParameter("car", car);
        return q.getResultList();
    }

    @Override
    public List<Reservation> getReservationByEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("You must specify reservation employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee must have specified ID");
        }
        Query q = entityManager.createNamedQuery(Reservation.FIND_BY_EMPLOYEE);
        q.setParameter("employee", employee);
        return q.getResultList();
    }

    @Override
    public List<Reservation> getReservationByCarAndEmployee(Car car, Employee employee) {
        if (car == null) {
            throw new IllegalArgumentException("You must specify reservation car");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Car must have specified ID");
        }
        if (employee == null) {
            throw new IllegalArgumentException("You must specify reservation employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee must have specified ID");
        }

        Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR_AND_EMPLOYEE);
        q.setParameter("car", car);
        q.setParameter("employee", employee);
        return q.getResultList();
    }

    @Override
    public List<Reservation> getAllReservations() {
        TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_ALL, Reservation.class);
        return q.getResultList();
    }

    @Override
    public List<Reservation> getReservationsToConfirm() {
        TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_TO_CONFIRM, Reservation.class);
        return q.getResultList();
    }

    @Override
    public List<Reservation> getAcceptedReservations() {
        TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_ACCEPTED, Reservation.class);
        return q.getResultList();
    }
}
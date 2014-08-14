package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Eduard Krak
 */
public interface ReservationDAO {
    
    /**
     * Returns reservation with given id
     * @param id identifier of reservation
     * @return reservation with given id
     */
    public Reservation getReservationById(Long id);

    /**
     * Inserts reservation into database
     * @param r reservation to save
     */
    public void insert(Reservation r);

    /**
     * Update given reservation in database
     * @param r reservation to update
     */
    public void update(Reservation r);

    /**
     * Removes given reservation in database
     * @param r reservation to remove
     */
    public void remove(Reservation r);

    /**
     * Returns list of reservations with specific/given car
     * @param car Car to find reservation connected with
     * @return List of reservations connected with given car
     */
    public List<Reservation> getReservationByCar(Car car);

    /**
     * Returns list of reservations with specific/given car
     * @param employee employee to find reservation connected with
     * @return List of reservations connected with given car
     */
    public List<Reservation> getReservationByEmployee(Employee employee);

    /**
     * Returns list of reservations with specific/given car and employee
     * @param car Car to find reservation connected with
     * @param employee Employee to find reservation connected with
     * @return List of reservations connected with given car and employee
     */
    public List<Reservation> getReservationByCarAndEmployee(Car car, Employee employee);

    /**
     * Returns all reservations present in database
     * @return All reservations
     */
    public List<Reservation> getAllReservations();
    
    /**
     * Returns all reservations that are not confirmed
     * @return Reservations that are not confirmed
     */
    public List<Reservation> getReservationsToConfirm();
    
    /**
     * Returns all accepted reservations
     * @return Reservations that are accepted
     */
    public List<Reservation> getAcceptedReservations();
}

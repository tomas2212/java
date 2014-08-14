package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;

/**
 * DAO class for entity {@link Car}
 *
 * @author Tomas Svrcek
 */
public interface CarDAO {

    /**
     * Returns Car by ID. Argument is ID of Car
     *
     * @param id identifier
     * @return Car Car with that ID
     */
    public Car getCarById(Long id);

    /**
     * Returns Car by SPZ. Argument is SPZ of Car
     *
     * @param spz identifier
     * @return Car Car with that SPZ
     */
    public Car getCarBySpz(String spz);

    /**
     * Returns all cars from DB
     *
     * @param nothing
     * @return List of all cars
     */
    public List<Car> getAllCars();

    /**
     * Inserts a car into database and collection
     *
     * @param car New car
     * @return nothing
     */
    public void insert(Car car);

    /**
     * Remove car from the collection and database
     *
     * @param car Car, we want to delete
     * @return nothing
     */
    public void remove(Car car);

    /**
     * Updates specified car
     *
     * @param car Car, which we want to change(update)
     * @return nothing
     */
    public void update(Car car);

    /**
     * Returns list of all cars, which have equal companyLevel or higher.
     *
     * @param companyLevel  Hierarchy level of employees in the VozovyParkIS
     * @return List of all cars with the same hierarchy level or higher
     */
    public List<Car> getAllCarsWithHigherLevel(CompanyLevel companyLevel);
}

package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author Tomas Svrcek
 */
public interface CarService {

    /**
     * Creates car in DB
     *
     * @param car
     * @return new car in DB
     */
    CarDTO createCar(CarDTO car);

    /**
     * Updates car in DB
     *
     * @param car
     * @return updated car
     */
    CarDTO updateCar(CarDTO car);

    /**
     * Set availability
     *
     * @param id
     * @param available
     * @return Car with new availability
     */
    CarDTO setCarAvailable(Long id, Boolean available);

    /**
     * Removing Car from DB
     *
     * @param id car to remove
     * @return void
     */
    void removeCar(Long id);

    /**
     * Finding car
     *
     * @param id
     * @return Car with its ID
     */
    CarDTO getCarById(Long id);

    /**
     * List all cars in DB
     *
     * @return All cars in DB
     */
    List<CarDTO> getAllCars();

    /**
     * Listing cars by company level
     *
     * @param companyLevel cl
     * @return all cars with higher CompanyLevel
     */
    List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel);
}
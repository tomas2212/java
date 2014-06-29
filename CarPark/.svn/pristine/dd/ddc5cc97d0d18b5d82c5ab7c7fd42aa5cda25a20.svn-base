package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Svrcek
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    CarDAO carDao;
    
    Mapper mapper;

    public void setCarDao(CarDAO carDao) {
        this.carDao = carDao;
    }
    
    public void setMapper(Mapper mapper){
        this.mapper = mapper;
    }

    public CarDTO createCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        car.setAvailable(Boolean.TRUE);
        carDao.insert(mapper.map(car, Car.class));
        return car;
    }

    public CarDTO updateCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car name is not specified");
        }  
        Car entity = mapper.map(car, Car.class);     
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Car ID is not specified");
        }
        carDao.update(entity);
        return mapper.map(entity, CarDTO.class);
    }

    public CarDTO setCarAvailable(Long id, Boolean available) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        if (available == null) {
            throw new IllegalArgumentException("Availability is not specified");
        }
        Car car = carDao.getCarById(id);
        car.setAvailable(available);
        carDao.update(car);

        return mapper.map(car, CarDTO.class);
    }

    public void removeCar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Car car = carDao.getCarById(id);
        if (car == null) {
            throw new IllegalArgumentException("Car with this ID does not exist");
        }
        carDao.remove(car);
    }

    public CarDTO getCarById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Car carById = carDao.getCarById(id);
        if(carById == null){
            return null;
        }
        return mapper.map(carById, CarDTO.class);
    }

    public List<CarDTO> getAllCars() {
        List<CarDTO> cars = new ArrayList<CarDTO>();
        List<Car> allCars = carDao.getAllCars();
        for (Car c : allCars) {
            cars.add(mapper.map(c, CarDTO.class));
        }
        return cars;
    }

    public List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }
        List<CarDTO> cars = new ArrayList<CarDTO>();
        CompanyLevel cl = mapper.map(companyLevel, CompanyLevel.class);
        List<Car> clCars = carDao.getAllCarsWithHigherLevel(cl);
        for (Car c : clCars) {
            cars.add(mapper.map(c, CarDTO.class));
        }
        return cars;
    }
}

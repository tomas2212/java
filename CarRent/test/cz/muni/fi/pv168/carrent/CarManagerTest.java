/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

import cz.muni.fi.pv168.carrent.Car;
import cz.muni.fi.pv168.carrent.CarManager;
import cz.muni.fi.pv168.carrent.CarManagerImpl;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author klodye
 */
public class CarManagerTest {

    private CarManager carManager;

    public CarManagerTest() {
    }

    @Before
    public void setUp() {
        carManager = new CarManagerImpl();
    }

    /**
     * Test of main method, of class Main.
     */
    /*@Test
    public void addCarTest() {
        Car car = new Car();
        car.setType("Opel");
        car.setSpz("java4");
        car.setReserved(false);
        car.setPriceForDayRent(1000);
        carManager.addCar(car);
        int carId = car.getCarId();
        Car car2 = carManager.findCarById(carId);
        assertEquals(car, car2);
    }


    @Test
    public void deleteCarTest() {
        Car car = new Car();
        car.setType("Punto");
        car.setSpz("cecko4");
        car.setReserved(false);
        car.setPriceForDayRent(2000);
        car = carManager.addCar(car);
        int carId = car.getCarId();
        carManager.deleteCar(car);
        assertNull( carManager.findCarById(carId));
    }

    @Test
    public void updateReaderTest() {
        Car car = new Car();
        car.setType("Mercedes");
        car.setSpz("ruby4");
        car.setReserved(false);
        car.setPriceForDayRent(8000);
        car = carManager.addCar(car);
        Car car2 = carManager.findCarById(car.getCarId());
        car2.setPriceForDayRent(9000);
        assertEquals(car2, carManager.updateCar(car2));
    }

    private Car createSampleCar() {
        Car car = new Car();
        car.setPriceForDayRent(7000);
        car.setReserved(false);
        car.setSpz("dfsa");
        car.setType("whoCares");
        return car;
    }*/

    @Test
    public void updateCarTest() {
        Collection<Car> cars = carManager.findCarsByString("bulo");
        System.out.println(cars.size());
    }
}

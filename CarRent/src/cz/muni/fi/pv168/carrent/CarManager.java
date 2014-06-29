/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pv168.carrent;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Klodye
 */
public interface CarManager {

    Car addCar(Car car);
    
    void deleteCar(Car car);
    
    Car updateCar(Car car);
    
    Collection<Car> findAllCars();
    
    Car findCarById(int carId);

    Collection<Car> findCarsByString(String str);

}

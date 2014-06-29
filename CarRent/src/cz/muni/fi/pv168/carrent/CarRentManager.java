/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pv168.carrent;

import java.util.Collection;

/**
 *
 * @author Klodye
 */
public interface CarRentManager {

    Rent addRent(Rent rent);

    Rent updateRent(Rent rent);

    void deleteRent(Rent rent);

    Collection<Rent> findAllRents();

    Rent findRentById(int rentId);

    Collection<Car> findRentedCars();

    Collection<Car> findFreeCars();

    Collection<Rent> findRentsByString(String str);

    //Collection<Customer> findActiveCustomers();

    //Collection<Rent> findSomebodiesRents(Customer customer);


}

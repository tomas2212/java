/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

import cz.muni.fi.pv168.carrent.Rent;
import cz.muni.fi.pv168.carrent.Car;
import cz.muni.fi.pv168.carrent.Customer;
import cz.muni.fi.pv168.carrent.CustomerManager;
import cz.muni.fi.pv168.carrent.CarRentManagerImpl;
import cz.muni.fi.pv168.carrent.CarRentManager;
import cz.muni.fi.pv168.carrent.CustomerManagerImpl;
import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomasius
 */
public class CarRentManagerImplTest {

    private CarRentManager carRentManager;
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public CarRentManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        carRentManager = new CarRentManagerImpl();
    }

    @After
    public void tearDown() {
    }

    /*@Test
    public void testAddRent() {
        Rent rent = new Rent();

        rent.setPersonalId("zakaznik1");
        rent.setDate(sqlDate.toString());
        rent.setCarId(81);
        rent.setForDays(2);
        rent.setPrice(rent.getPrice());
        rent = carRentManager.addRent(rent);
        int rentId = rent.getRentId();
        assertTrue(rentId > 0);
        Rent rent2 = carRentManager.findRentById(rentId);
        assertEquals(rent, rent2);

        // test if we know that price for a day is bigger than zero
        int price = rent.getPrice();
        assertTrue(price >= 0);
    }

    @Test
    public void testDeleteRent() {
        Rent rent = new Rent();
        Customer customer = new Customer();
        customer.setPersonalId("zakaznik3");

        rent.setPersonalId(customer.getPersonalId());
        rent.setCarId(83);
        rent.setForDays(3);
        rent.setDate(sqlDate.toString());
        rent.setPrice(rent.getPrice());

        rent = carRentManager.addRent(rent);
        int rentId = rent.getRentId();
        assertTrue(rentId > 0);

        carRentManager.deleteRent(rent);
        assertNull(carRentManager.findRentById(rentId));
    }

    @Test
    public void testUpdateRent() {
        Rent rent = new Rent();
        Customer customer = new Customer();
        Car car = new Car();
        customer.setPersonalId("zakaznik3");

        rent.setPersonalId(customer.getPersonalId());
        rent.setCarId(86);
        rent.setDate(sqlDate.toString());
        rent.setForDays(9);
        rent.setPrice(rent.getPrice());

        rent = carRentManager.addRent(rent);
        assertTrue(rent.getRentId() > 0);

        Rent rent2 = carRentManager.findRentById(rent.getRentId());
        rent2.setForDays(1);
        Rent rent3 = carRentManager.updateRent(rent2);
        assertEquals(rent2, rent3);

    }

    @Test
    public void testFindAllRents() {

        Rent rent1 = new Rent();
        rent1.setCarId(87);
        rent1.setPersonalId("zakaznik1");
        rent1.setDate(sqlDate.toString());
        rent1.setForDays(6);
        rent1.setPrice(rent1.getPrice());

        Rent rent2 = new Rent();
        rent2.setCarId(89);
        rent2.setPersonalId("zakaznik3");
        rent2.setDate(sqlDate.toString());
        rent2.setForDays(1);
        rent2.setPrice(rent1.getPrice());

        Rent rent3 = new Rent();
        rent3.setCarId(93);
        rent3.setPersonalId("zakaznik1");
        rent3.setDate(sqlDate.toString());
        rent3.setForDays(3);
        rent3.setPrice(rent1.getPrice());

        carRentManager.addRent(rent1);
        carRentManager.addRent(rent2);
        carRentManager.addRent(rent3);

        Collection<Rent> rents = carRentManager.findAllRents();
        int amount = rents.size();
        assertEquals(5, amount);

    }*/

    /*@Test
    public void testFindRentById(){
    Rent rent = new Rent();
    Customer customer = new Customer();
    customer.setPersonalId("zakaznik3");

    rent.setPersonalId(customer.getPersonalId());
    rent.setCarId(15);
    rent.setDate(sqlDate.toString());
    rent.setForDays(10);
    rent.setPrice(rent.getPrice());

    rent = carRentManager.addRent(rent);
    assertTrue(rent.getRentId() > 0);
    int rentId = rent.getRentId();
    Rent rent2 = carRentManager.findRentById(rent.getRentId());
    assertTrue(rent.equals(rent2));
    }*/

    /*@Test
    public void testFindSomebodiesRents(){
    CustomerManager cMan = new CustomerManagerImpl();
    Customer customer = cMan.findCustomerById("zakaznik1");
    int amount = carRentManager.findSomebodiesRents(customer).size();
    assertEquals(3, amount);
    }*/

    /*@Test
    public void testFindFreeCars(){
    int amount = carRentManager.findFreeCars().size();
    assertEquals(5, amount);
    }*/
    @Test
    public void testString() {
        Collection<Rent> rents = carRentManager.findRentsByString("7");
        for (Rent rent : rents) {
            System.out.println(rent.getPersonalId());
        }
    }
}

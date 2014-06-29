/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

import java.util.Collection;
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
public class CustomerManagerTest {

    private CustomerManager customerManager;

    
    public CustomerManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        customerManager = new CustomerManagerImpl();
    }

    @After
    public void tearDown() {
    }

    /*@Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setPersonalId("zakaznik100");
        customer.setFirstName("Jan");
        customer.setSurname("Hrozny");
        customer.setStreet("Botanicka");
        customer.setHouseNo(12);
        customer.setPsc("61700");
        customer.setCity("Brno");
        customer.setCountry("Czech Republic");
        customer.setPhoneNo("774 289 123");

        customerManager.addCustomer(customer);

        String customerId = customer.getPersonalId();
        assertNotNull(customerId);
        
        Customer customer2 = customerManager.findCustomerById(customerId);
        
        assertEquals(customer, customer2);
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer();

        customer.setPersonalId("zakaznik200");
        customer.setFirstName("Peter");
        customer.setSurname("Komar");
        customer.setStreet("Botanicka");
        customer.setHouseNo(14);
        customer.setPsc("61703");
        customer.setCity("Brno");
        customer.setCountry("Czech Republic");
        customer.setPhoneNo("734 239 143");

        customer = customerManager.addCustomer(customer);
        String customerId = customer.getPersonalId();
        assertNotNull(customerId);
        customerManager.deleteCustomer(customer);

        assertNull(customerManager.findCustomerById(customerId));
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();

        customer.setPersonalId("zakaznik300");
        customer.setFirstName("Karol");
        customer.setSurname("Homar");
        customer.setStreet("Geologicka");
        customer.setHouseNo(14);
        customer.setPsc("63743");
        customer.setCity("Praha");
        customer.setCountry("Czech Republic");
        customer.setPhoneNo("733 269 143");

        customer = customerManager.addCustomer(customer);
        Customer customer2 = customerManager.findCustomerById(customer.getPersonalId());
        customer2.setFirstName("Karelko");
        Customer customer3 = customerManager.updateCustomer(customer2);
        assertEquals(customer2, customer3);
    }

    @Test
    public void testAddCustomerErrors() {
        Customer customer = new Customer();
        //when we try to add null, we should get an exception
        try {
            customerManager.addCustomer(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        

        //when we try to add a customer with negative houseNo, we should get an exception
        customer.setHouseNo(-1);
        try {
            customerManager.addCustomer(customer);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //when we try to add a customer with zero houseNo, we should get an exception
        customer.setHouseNo(0);
        try {
            customerManager.addCustomer(customer);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }*/

    @Test
    public void testString(){
        Collection<Customer> cust = customerManager.findCustomersByString("14");
        for(Customer custo : cust){
            System.out.println(custo.getPersonalId());
        }
        
    }
    
}

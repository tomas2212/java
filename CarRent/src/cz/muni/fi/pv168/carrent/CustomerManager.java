/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pv168.carrent;

import java.util.Collection;

/**
 *
 * @author xsvrcek1
 */
public interface CustomerManager {

    Customer addCustomer(Customer customer);

    Customer findCustomerById(String id);

    void deleteCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Collection<Customer> findAllCustomers();

    Collection<Customer> findCustomersByString(String str);

}

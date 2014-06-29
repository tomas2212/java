/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author xsvrcek1, klodye
 */
public class CustomerManagerImpl implements CustomerManager {

    private final DataSource pool;
    private static final Logger logger = Logger.getLogger(CustomerManagerImpl.class.getName());

    public CustomerManagerImpl() {
        ResourceBundle connectionProp = ResourceBundle.getBundle("Connection");
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName(connectionProp.getString("server_name"));
        ds.setPortNumber(Integer.parseInt(connectionProp.getString("port_number")));
        ds.setDatabaseName(connectionProp.getString("database_name"));
        ds.setUser(connectionProp.getString("user"));
        ds.setPassword(connectionProp.getString("password"));
        this.pool = ds;
    }

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Parameter [customer] can't be null.");
        }
        if (customer.getFirstName() == null) {
            throw new IllegalArgumentException("First name can't be null");
        }
        if (customer.getSurname() == null) {
            throw new IllegalArgumentException("Surname can't be null");
        }
        if (customer.getStreet() == null) {
            throw new IllegalArgumentException("Street can't be null");
        }
        if (customer.getHouseNo() < 0) {
            throw new IllegalArgumentException("House number must be positive");
        }
        if (customer.getPsc() == null) {
            throw new IllegalArgumentException("Psc can't be null");
        }
        if (customer.getCity() == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        if (customer.getCountry() == null) {
            throw new IllegalArgumentException("Country can't be null");
        }
        if (customer.getPhoneNo() == null) {
            throw new IllegalArgumentException("Phone number can't be null");
        }

        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("INSERT INTO customers (personal_id, firstname, surname, street, house_no, psc, city, country, phone_no) VALUES (?,?,?,?,?,?,?,?,?)");
            pStatement.setString(1, customer.getPersonalId());
            pStatement.setString(2, customer.getFirstName());
            pStatement.setString(3, customer.getSurname());
            pStatement.setString(4, customer.getStreet());
            pStatement.setInt(5, customer.getHouseNo());
            pStatement.setString(6, customer.getPsc());
            pStatement.setString(7, customer.getCity());
            pStatement.setString(8, customer.getCountry());
            pStatement.setString(9, customer.getPhoneNo());

            int result = pStatement.executeUpdate();
            assert result == 1;

            return customer;

        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error when inserting customer into the Database", sqle);
            throw new RuntimeException("Error when inserting customer into to database", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error when closing connection", sqle);
                }
            }
        }
    }

    public Customer findCustomerById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter [id] must not be null!");
        }
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT personal_id, firstname, surname, street, house_no, psc, city, country,"
                    + " phone_no FROM customers WHERE personal_id = ?");
            pStatement.setString(1, id);
            ResultSet rSet = pStatement.executeQuery();

            if (rSet.next()) {
                Customer customer = new Customer();
                customer.setPersonalId(rSet.getString("personal_id"));
                customer.setFirstName(rSet.getString("firstname"));
                customer.setSurname(rSet.getString("surname"));
                customer.setStreet(rSet.getString("street"));
                customer.setHouseNo(rSet.getInt("house_no"));
                customer.setPsc(rSet.getString("psc"));
                customer.setCity(rSet.getString("city"));
                customer.setCountry(rSet.getString("country"));
                customer.setPhoneNo(rSet.getString("phone_no"));
                assert (!rSet.next());
                return customer;
            } else {
                return null;
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting customer from Database!", sqle);
            throw new RuntimeException("Error while getting customer from Database!", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error while closing connection", sqle);
                }
            }
        }
    }

    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Parameter customer cannot be null!");
        }
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("DELETE FROM customers WHERE personal_id=?");
            pStatement.setString(1, customer.getPersonalId());
            int execution = pStatement.executeUpdate();
            if (execution == 0) {
                throw new IllegalArgumentException("There's no customer with such id!");
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error when removing customer from DB", sqle);
            throw new RuntimeException("Error when removing customer from DB", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error while closing connection", sqle);
                }
            }
        }
    }

    public Customer updateCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Parameter customer cannot be null!");
        }
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("UPDATE customers SET firstname=?, surname=?, "
                    + "street=?, house_no=?, psc=?, city=?, country=?, phone_no=? WHERE personal_id=?");
            pStatement.setString(1, customer.getFirstName());
            pStatement.setString(2, customer.getSurname());
            pStatement.setString(3, customer.getStreet());
            pStatement.setInt(4, customer.getHouseNo());
            pStatement.setString(5, customer.getPsc());
            pStatement.setString(6, customer.getCity());
            pStatement.setString(7, customer.getCountry());
            pStatement.setString(8, customer.getPhoneNo());
            pStatement.setString(9, customer.getPersonalId());

            if (pStatement.executeUpdate() != 0) {
                return customer;
            } else {
                throw new IllegalArgumentException("There is no such customer!");
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while updating customer in Database!", sqle);
            throw new RuntimeException("Error while updating customer in Database!", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error while closing connection", sqle);
                }
            }
        }
    }

    public Collection<Customer> findAllCustomers() {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM customers");
            ResultSet rSet = pStatement.executeQuery();
            Collection<Customer> customers = new HashSet<Customer>();
            while (rSet.next()) {
                Customer customer = new Customer();
                customer.setPersonalId(rSet.getString("personal_id"));
                customer.setFirstName(rSet.getString("firstname"));
                customer.setSurname(rSet.getString("surname"));
                customer.setStreet(rSet.getString("street"));
                customer.setHouseNo(rSet.getInt("house_no"));
                customer.setPsc(rSet.getString("psc"));
                customer.setCity(rSet.getString("city"));
                customer.setCountry(rSet.getString("country"));
                customer.setPhoneNo(rSet.getString("phone_no"));
                customers.add(customer);
            }
            return Collections.unmodifiableCollection(customers);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting customers from Database!", sqle);
            throw new RuntimeException("Error while getting customers from Database!", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error while closing connection", sqle);
                }
            }
        }
    }


    public Collection<Customer> findCustomersByString(String str) {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            if (str.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                int intStr = Integer.parseInt(str);
                pStatement = connection.prepareStatement("SELECT * FROM customers where personal_id LIKE ? OR firstname LIKE ? OR surname LIKE ? OR street LIKE ? OR house_no=? OR psc LIKE ? OR city LIKE ? OR country LIKE ? OR phone_no LIKE ?");
                pStatement.setString(1, "%"+str+"%");
                pStatement.setString(2, "%"+str+"%");
                pStatement.setString(3, "%"+str+"%");
                pStatement.setString(4, "%"+str+"%");
                pStatement.setInt(5, intStr);
                pStatement.setString(6, "%"+str+"%");
                pStatement.setString(7, "%"+str+"%");
                pStatement.setString(8, "%"+str+"%");
                pStatement.setString(9, "%"+str+"%");
            }else{
                pStatement = connection.prepareStatement("SELECT * FROM customers where personal_id LIKE ? OR firstname LIKE ? OR surname LIKE ? OR street LIKE ? OR psc LIKE ? OR city LIKE ? OR country LIKE ? OR phone_no LIKE ?");
                pStatement.setString(1, "%"+str+"%");
                pStatement.setString(2, "%"+str+"%");
                pStatement.setString(3, "%"+str+"%");
                pStatement.setString(4, "%"+str+"%");
                pStatement.setString(5, "%"+str+"%");
                pStatement.setString(6, "%"+str+"%");
                pStatement.setString(7, "%"+str+"%");
                pStatement.setString(8, "%"+str+"%");
            }
            ResultSet rSet = pStatement.executeQuery();
            Collection<Customer> customers = new HashSet<Customer>();
            while (rSet.next()) {
                Customer customer = new Customer();
                customer.setPersonalId(rSet.getString("personal_id"));
                customer.setFirstName(rSet.getString("firstname"));
                customer.setSurname(rSet.getString("surname"));
                customer.setStreet(rSet.getString("street"));
                customer.setHouseNo(rSet.getInt("house_no"));
                customer.setPsc(rSet.getString("psc"));
                customer.setCity(rSet.getString("city"));
                customer.setCountry(rSet.getString("country"));
                customer.setPhoneNo(rSet.getString("phone_no"));
                customers.add(customer);
            }
            return Collections.unmodifiableCollection(customers);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting customers from Database!", sqle);
            throw new RuntimeException("Error while getting customers from Database!", sqle);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException sqle) {
                    logger.log(Level.SEVERE, "Error while closing connection", sqle);
                }
            }
        }
    }


}
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author Klodye
 */
public class CarRentManagerImpl implements CarRentManager {

    private final DataSource pool;
    private static final Logger logger = Logger.getLogger(CarManagerImpl.class.getName());

    public CarRentManagerImpl() {
        ResourceBundle connectionProp = ResourceBundle.getBundle("Connection");
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName(connectionProp.getString("server_name"));
        ds.setPortNumber(Integer.parseInt(connectionProp.getString("port_number")));
        ds.setDatabaseName(connectionProp.getString("database_name"));
        ds.setUser(connectionProp.getString("user"));
        ds.setPassword(connectionProp.getString("password"));
        this.pool = ds;
    }

    public Rent addRent(Rent rent) {
        CarManager car = new CarManagerImpl();
        CustomerManager customer = new CustomerManagerImpl();
        if (rent == null) {    
            throw new IllegalArgumentException();
        }
        if (car.findCarById(rent.getCarId()) == null) {  
            throw new IllegalArgumentException("there is no car with such id");
        }
        if (customer.findCustomerById(rent.getPersonalId()) == null) {   
            throw new IllegalArgumentException("there is no customer with such id");
        }
        if (rent.getForDays() < 0){
            throw new IllegalArgumentException("for days must be positive");
        }
        
        //...
        PreparedStatement pStatement = null;
        PreparedStatement noCarsStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            noCarsStatement = connection.prepareStatement("SELECT * FROM rents WHERE car_id = ?");
            noCarsStatement.setInt(1, rent.getCarId());
            ResultSet rSet = noCarsStatement.executeQuery();
            if (rSet.next()) {
                throw new IllegalArgumentException("There already is rent with that car");
            }

            pStatement = connection.prepareStatement("INSERT INTO rents (car_id, personal_id, date, price, for_days) VALUES (?,?,?,?,?)", pStatement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1, rent.getCarId());
            pStatement.setString(2, rent.getPersonalId());
            pStatement.setString(3, rent.getDate());
            pStatement.setInt(4, rent.getPrice());
            pStatement.setInt(5, rent.getForDays());
            int result = pStatement.executeUpdate();
            assert result == 1;
            int key = getId(pStatement.getGeneratedKeys());
            rent.setRentId(key);

            pStatement = connection.prepareStatement("UPDATE cars SET reserved=? WHERE car_id=?");
            pStatement.setBoolean(1, true);
            pStatement.setInt(2, rent.getCarId());
            int result2 = pStatement.executeUpdate();
            assert result2 == 1;
            return rent;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error when inserting rent into DB", ex);
            throw new RuntimeException("Error when inserting rent into DB", ex);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public void deleteRent(Rent rent) {
        if (rent == null) {
            throw new NullPointerException();
        }
        PreparedStatement pStatement = null;
        PreparedStatement pStatement2 = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("DELETE FROM rents WHERE rent_id = ?");
            pStatement.setLong(1, rent.getRentId());
            pStatement2 = connection.prepareStatement("UPDATE cars SET reserved=? WHERE car_id=?");
            pStatement2.setBoolean(1, false);
            pStatement2.setInt(2, rent.getCarId());

            int result = pStatement.executeUpdate();
            int result2 = pStatement2.executeUpdate();
            if (result == 0) {
                throw new IllegalArgumentException();
            }
            if (result2 == 0) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error deleting rents", sqle);
            throw new RuntimeException("Error when deleting rents", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public Collection<Rent> findAllRents() {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM rents");
            ResultSet rSet = pStatement.executeQuery();
            Collection<Rent> allRents = new HashSet<Rent>();
            while (rSet.next()) {
                Rent rent = new Rent();
                rent.setRentId(rSet.getInt("rent_id"));
                rent.setCarId(rSet.getInt("car_id"));
                rent.setPersonalId(rSet.getString("personal_id"));
                rent.setDate(rSet.getString("date"));
                rent.setForDays(rSet.getInt("for_days"));
                rent.setPrice(rSet.getInt("price"));
                allRents.add(rent);
            }
            return Collections.unmodifiableCollection(allRents);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting rents from Database!", sqle);
            throw new RuntimeException("Error while getting rents from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public Rent findRentById(int rentId) {
        if (rentId < 0) {
            throw new IllegalArgumentException("Parameter carId cannot be NULL!");
        }
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT rent_id,car_id,personal_id,date, price, for_days FROM rents WHERE rent_id = ?");
            pStatement.setLong(1, rentId);
            ResultSet rSet = pStatement.executeQuery();

            if (rSet.next()) {
                Rent rent = new Rent();
                rent.setRentId(rSet.getInt("rent_id"));
                rent.setCarId(rSet.getInt("car_id"));
                rent.setForDays(rSet.getInt("for_days"));
                rent.setPersonalId(rSet.getString("personal_id"));
                rent.setDate(rSet.getString("date"));
                rent.setPrice(rSet.getInt("price"));
                assert !rSet.next();
                int jo = rent.getPrice();


                return rent;
            } else {
                return null;
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting rent from Database!", sqle);
            throw new RuntimeException("Error while getting rent from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public Collection<Car> findRentedCars() {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM cars WHERE reserved = '1'");
            ResultSet rSet = pStatement.executeQuery();
            Collection<Car> freeCars = new HashSet<Car>();
            while (rSet.next()) {
                Car car = new Car();
                car.setCarId(rSet.getInt("car_id"));
                car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
                car.setReserved(rSet.getBoolean("reserved"));
                car.setSpz(rSet.getString("spz"));
                car.setType(rSet.getString("type_of_car"));
                freeCars.add(car);
            }
            return Collections.unmodifiableCollection(freeCars);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
            throw new RuntimeException("Error while getting cars from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public Collection<Car> findFreeCars() {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM cars WHERE reserved = '0'");
            ResultSet rSet = pStatement.executeQuery();
            Collection<Car> freeCars = new HashSet<Car>();
            while (rSet.next()) {
                Car car = new Car();
                car.setCarId(rSet.getInt("car_id"));
                car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
                car.setReserved(rSet.getBoolean("reserved"));
                car.setSpz(rSet.getString("spz"));
                car.setType(rSet.getString("type_of_car"));
                freeCars.add(car);
            }
            return Collections.unmodifiableCollection(freeCars);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
            throw new RuntimeException("Error while getting cars from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }

    public Collection<Rent> findRentsByString(String str){
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            if (str.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                int intStr = Integer.parseInt(str);
                pStatement = connection.prepareStatement("SELECT * FROM rents where rent_id=? OR car_id=? OR personal_id LIKE ? OR date LIKE ? OR price=? OR for_days=?");
                pStatement.setInt(1, intStr);
                pStatement.setInt(2, intStr);
                pStatement.setString(3, "%" + str + "%");
                pStatement.setString(4, "%" + str + "%");
                pStatement.setInt(5, intStr);
                pStatement.setInt(6, intStr);
            }else{
                pStatement = connection.prepareStatement("SELECT * FROM rents where personal_id LIKE ? OR date LIKE ?");
                pStatement.setString(1, "%" + str + "%");
                pStatement.setString(2, "%" + str + "%");
            }
            ResultSet rSet = pStatement.executeQuery();
            Collection<Rent> allRents = new HashSet<Rent>();
            while (rSet.next()) {
                Rent rent = new Rent();
                rent.setRentId(rSet.getInt("rent_id"));
                rent.setCarId(rSet.getInt("car_id"));
                rent.setPersonalId(rSet.getString("personal_id"));
                rent.setDate(rSet.getDate("date").toString());
                rent.setForDays(rSet.getInt("for_days"));
                rent.setPrice(rSet.getInt("price"));
                allRents.add(rent);
            }
            return Collections.unmodifiableCollection(allRents);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting rents from Database!", sqle);
            throw new RuntimeException("Error while getting rents from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }


    private static int getId(ResultSet keys) throws SQLException {
        if (keys.getMetaData().getColumnCount() != 1) {
            throw new IllegalArgumentException("Given ResultSet contains more columns");
        }
        if (keys.next()) {
            int result = keys.getInt(1);
            if (keys.next()) {
                throw new IllegalArgumentException("Given ResultSet contains more rows");
            }
            return result;
        } else {
            throw new IllegalArgumentException("Given ResultSet contain no rows");
        }
    }

    public void closeStatement(PreparedStatement pStatement) {
        if (pStatement != null) {
            try {
                pStatement.close();
            } catch (SQLException sqle) {
                logger.log(Level.SEVERE, "Error while closing connection", sqle);
            }
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                logger.log(Level.SEVERE, "Error while closing connection", sqle);
            }
        }
    }

    public Rent updateRent(Rent rent) {
        if (rent == null) {
            throw new NullPointerException("Parameter rent cannot be null!");
        }
        PreparedStatement pStatement = null;
        PreparedStatement pStatement2 = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT rent_id,car_id,personal_id,date, price, for_days FROM rents WHERE rent_id = ?");
            pStatement.setLong(1, rent.getRentId());
            ResultSet rSet = pStatement.executeQuery();
            if (rSet.next()) {
                assert !rSet.next();
            }
            pStatement2 = connection.prepareStatement("UPDATE rents SET car_id=?, personal_id = ?, for_days = ?, date=?, price = ? WHERE rent_id = ?");
            pStatement2.setInt(1, rent.getCarId());
            pStatement2.setString(2, rent.getPersonalId());
            pStatement2.setInt(3, rent.getForDays());
            pStatement2.setString(4, rent.getDate());
            pStatement2.setInt(5, rent.getPrice());
            pStatement2.setLong(6, rent.getRentId());
            if (pStatement2.executeUpdate() != 0) {
                return rent;
            } else {
                throw new IllegalArgumentException("There is no rent with such ID!");
            }

        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while updating rents from Database!", sqle);
            throw new RuntimeException("Error while updating rents from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }





    /*public Collection<Customer> findActiveCustomers() {
    PreparedStatement pStatement = null;
    try {
    pStatement = connection.prepareStatement("SELECT * FROM cars WHERE reserved = false");
    ResultSet rSet = pStatement.executeQuery();
    Collection<Customer> freeCars = new HashSet<Customer>();
    while (rSet.next()) {
    Car car = new Car();
    car.setCarId(rSet.getInt("car_id"));
    car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
    car.setReserved(rSet.getBoolean("reserved"));
    car.setSpz(rSet.getString("spz"));
    car.setType(rSet.getString("type_of_car"));
    freeCars.add(car);
    }
    return Collections.unmodifiableCollection(freeCars);
    } catch (SQLException sqle) {
    logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
    throw new RuntimeException("Error while getting cars from Database!", sqle);
    } finally {
    closeConnection(pStatement);
    }
    }*/

    /*public Collection<Rent> findSomebodiesRents(Customer customer) {
        CustomerManager cMan = new CustomerManagerImpl();
        if (cMan.findCustomerById(customer.getPersonalId()) == null) {
            throw new IllegalArgumentException("there is no customer with such id");
        }
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM rents WHERE personal_id = ?");
            pStatement.setString(1, customer.getPersonalId());
            ResultSet rSet = pStatement.executeQuery();
            Collection<Rent> somebodiesRents = new HashSet<Rent>();
            while (rSet.next()) {
                Rent rent = new Rent();
                rent.setRentId(rSet.getInt("rent_id"));
                rent.setCarId(rSet.getInt("car_id"));
                rent.setPersonalId(rSet.getString("personal_id"));
                rent.setForDays(rSet.getInt("for_days"));
                rent.setDate(rSet.getDate("date").toString());
                rent.setPrice(rSet.getInt("price"));
                somebodiesRents.add(rent);
            }
            return Collections.unmodifiableCollection(somebodiesRents);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting rents from Database!", sqle);
            throw new RuntimeException("Error while getting rents from Database!", sqle);
        } finally {
            closeStatement(pStatement);
            closeConnection(connection);
        }
    }*/
}

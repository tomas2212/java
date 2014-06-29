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
 * @author Klodye
 */
public class CarManagerImpl implements CarManager {

    private final DataSource pool;
    private static final Logger logger = Logger.getLogger(CarManagerImpl.class.getName());

    public CarManagerImpl() {
        ResourceBundle connectionProp = ResourceBundle.getBundle("Connection");
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName(connectionProp.getString("server_name"));
        ds.setPortNumber(Integer.parseInt(connectionProp.getString("port_number")));
        ds.setDatabaseName(connectionProp.getString("database_name"));
        ds.setUser(connectionProp.getString("user"));
        ds.setPassword(connectionProp.getString("password"));
        this.pool = ds;
    }

    public Car addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (car.getPriceForDayRent() <= 0) {
            throw new IllegalArgumentException("Price for a day rent cannot be negative");
        }
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("INSERT INTO cars (type_of_car, spz, reserved, price_for_day_rent) VALUES (?,?,?,?)", pStatement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, car.getType());
            pStatement.setString(2, car.getSpz());
            pStatement.setBoolean(3, false);
            pStatement.setInt(4, car.getPriceForDayRent());

            int result = pStatement.executeUpdate();
            assert result == 1;

            System.out.println(pStatement.getGeneratedKeys());
            int key = getId(pStatement.getGeneratedKeys());
            car.setCarId(key);
            return car;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error when inserting car into DB", ex);
            throw new RuntimeException("Error when inserting car DB", ex);
        } finally {
            closeConnection(pStatement);
        }
    }

    public void deleteCar(Car car) {
        if (car == null) {
            throw new NullPointerException("Parameter car cannot be null!");
        }
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("DELETE FROM cars WHERE car_id = ?");
            pStatement.setInt(1, car.getCarId());
            int execution = pStatement.executeUpdate();
            if (execution == 0) {
                throw new IllegalArgumentException("There's no car with such id!");
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error when deleting from DB", sqle);
            throw new RuntimeException("Error when deleting from DB", sqle);
        } finally {
            closeConnection(pStatement);
        }
    }

    public Car updateCar(Car car) {
        if (car == null) {
            throw new NullPointerException("Parameter car cannot be null!");
        }
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("UPDATE cars SET price_for_day_rent=?,reserved=?,spz=?, type_of_car=? WHERE car_id=?");
            pStatement.setInt(1, car.getPriceForDayRent());
            pStatement.setBoolean(2, car.isReserved());
            pStatement.setString(3, car.getSpz());
            pStatement.setString(4, car.getType());
            pStatement.setInt(5, car.getCarId());
            if (pStatement.executeUpdate() != 0) {
                return car;
            } else {
                throw new IllegalArgumentException("There is no car with such ID!");
            }

        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
            throw new RuntimeException("Error while getting cars from Database!", sqle);
        } finally {
            closeConnection(pStatement);
        }
    }

    public Collection<Car> findAllCars() {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM cars");
            ResultSet rSet = pStatement.executeQuery();
            Collection<Car> allCars = new HashSet<Car>();
            while (rSet.next()) {
                Car car = new Car();
                car.setCarId(rSet.getInt("car_id"));
                car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
                car.setReserved(rSet.getBoolean("reserved"));
                car.setSpz(rSet.getString("spz"));
                car.setType(rSet.getString("type_of_car"));
                allCars.add(car);
            }
            return Collections.unmodifiableCollection(allCars);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
            throw new RuntimeException("Error while getting cars from Database!", sqle);
        } finally {
            closeConnection(pStatement);
        }
    }

    public Car findCarById(int carId) {
        if (carId < 0) {
            throw new IllegalArgumentException("Parameter carId cannot be NULL!");
        }
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = pool.getConnection();
            pStatement = connection.prepareStatement("SELECT car_id,price_for_day_rent,reserved,spz, type_of_car FROM cars WHERE car_id = ?");
            pStatement.setInt(1, carId);
            ResultSet rSet = pStatement.executeQuery();

            if (rSet.next()) {
                Car car = new Car();
                car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
                car.setReserved(rSet.getBoolean("reserved"));
                car.setSpz(rSet.getString("spz"));
                car.setType(rSet.getString("type_of_car"));
                car.setCarId(rSet.getInt("car_id"));
                assert !rSet.next();
                return car;
            } else {
                return null;
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting car from Database!", sqle);
            throw new RuntimeException("Error while getting car from Database!", sqle);
        } finally {
            closeConnection(pStatement);
        }
    }

    public Collection<Car> findCarsByString(String str) {
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            if (str.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                int intStr = Integer.parseInt(str);
                pStatement = connection.prepareStatement("SELECT * FROM cars WHERE car_id = ? OR spz LIKE ? OR price_for_day_rent = ? OR type_of_car LIKE ?");
                pStatement.setInt(1, intStr);
                pStatement.setString(2, "%" + str + "%");
                pStatement.setInt(3, intStr);
                pStatement.setString(4, "%" + str + "%");
            } else {
                pStatement = connection.prepareStatement("SELECT * FROM cars WHERE spz LIKE ? OR type_of_car LIKE ?");
                pStatement.setString(1, "%" + str + "%");
                pStatement.setString(2, "%" + str + "%");
            }
            ResultSet rSet = pStatement.executeQuery();
            Collection<Car> allCars = new HashSet<Car>();
            while (rSet.next()) {
                Car car = new Car();
                car.setCarId(rSet.getInt("car_id"));
                car.setPriceForDayRent(rSet.getInt("price_for_day_rent"));
                car.setReserved(rSet.getBoolean("reserved"));
                car.setSpz(rSet.getString("spz"));
                car.setType(rSet.getString("type_of_car"));
                allCars.add(car);
            }
            return Collections.unmodifiableCollection(allCars);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while getting cars from Database!", sqle);
            throw new RuntimeException("Error while getting cars from Database!", sqle);
        } finally {
            closeConnection(pStatement);
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

    public void closeConnection(PreparedStatement pStatement) {
        if (pStatement != null) {
            try {
                pStatement.close();
            } catch (SQLException sqle) {
                logger.log(Level.SEVERE, "Error while closing connection", sqle);
            }
        }
    }
}

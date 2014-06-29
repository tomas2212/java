/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pv168.carrent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klodye
 */
public class Rent {

    private int rentId;
    private String personalId;
    private int carId;
    private String date;
    private int forDays;
    private int price;

    private Connection connection;
    private static final Logger logger = Logger.getLogger(CarManagerImpl.class.getName());

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getForDays() {
        return forDays;
    }

    public void setForDays(int forDays) {
        this.forDays = forDays;
    }

    public int getPrice() {
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/CarRentDatabase", "klodyeSlashy", "kindermafia");
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while connecting to Database!", sqle);
            throw new RuntimeException("Error while connecting to Database!", sqle);
        }
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement("SELECT price_for_day_rent FROM cars WHERE car_id = ?");
            int caridd = getCarId();
            pStatement.setInt(1, getCarId());

            ResultSet rSet = pStatement.executeQuery();

            int priceForCar = 0;
            if (rSet.next()) {
                priceForCar = rSet.getInt("price_for_day_rent");
            }else{
                return 0;
            }
            this.price = getForDays() * priceForCar;
            return price;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error when inserting grave into DB", ex);
            throw new RuntimeException("Error when inserting into DB", ex);
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

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rent other = (Rent) obj;
        if (this.rentId != other.rentId) {
            return false;
        }
        if ((this.personalId == null) ? (other.personalId != null) : !this.personalId.equals(other.personalId)) {
            return false;
        }
        if (this.carId != other.carId) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        if (this.forDays != other.forDays) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.rentId;
        hash = 89 * hash + (this.personalId != null ? this.personalId.hashCode() : 0);
        hash = 89 * hash + this.carId;
        hash = 89 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 89 * hash + this.forDays;
        hash = 89 * hash + this.price;
        return hash;
    }

}

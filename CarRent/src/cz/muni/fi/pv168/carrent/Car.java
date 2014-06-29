/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

/**
 *
 * @author Klodye
 */
public class Car /*implements Comparable<Car>*/{

    private int carId;
    private String type;
    private String spz;
    private boolean reserved;
    private int priceForDayRent;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPriceForDayRent() {
        return priceForDayRent;
    }

    public void setPriceForDayRent(int priceForDayRent) {
        this.priceForDayRent = priceForDayRent;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.carId != other.carId) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.spz == null) ? (other.spz != null) : !this.spz.equals(other.spz)) {
            return false;
        }
        if (this.reserved != other.reserved) {
            return false;
        }
        if (this.priceForDayRent != other.priceForDayRent) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.carId;
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 37 * hash + (this.spz != null ? this.spz.hashCode() : 0);
        hash = 37 * hash + (this.reserved ? 1 : 0);
        hash = 37 * hash + this.priceForDayRent;
        return hash;
    }

    public int compareTo(Car o) {
        if(this.getCarId()<o.getCarId()){
            return -1;
        }else if(this.getCarId()>o.getCarId()){
            return 1;
        }else{
            return 0;
        }
    }



}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.carrent;

/**
 *
 * @author xsvrcek1
 */
public class Customer {

    private String personalId;
    private String firstName;
    private String surname;
    private String street;
    private int houseNo;
    private String psc;
    private String city;
    private String country;
    private String phoneNo;

    public Customer() {
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if ((this.personalId == null) ? (other.personalId != null) : !this.personalId.equals(other.personalId)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if (this.houseNo != other.houseNo) {
            return false;
        }
        if ((this.psc == null) ? (other.psc != null) : !this.psc.equals(other.psc)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country)) {
            return false;
        }
        if ((this.phoneNo == null) ? (other.phoneNo != null) : !this.phoneNo.equals(other.phoneNo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.personalId != null ? this.personalId.hashCode() : 0);
        hash = 23 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 23 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 23 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 23 * hash + this.houseNo;
        hash = 23 * hash + (this.psc != null ? this.psc.hashCode() : 0);
        hash = 23 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 23 * hash + (this.country != null ? this.country.hashCode() : 0);
        hash = 23 * hash + (this.phoneNo != null ? this.phoneNo.hashCode() : 0);
        return hash;
    }

    public int compareTo(Customer customer) {
        int result = personalId.compareTo(customer.getPersonalId());
        return result == 0 ? personalId.compareTo(((Customer) customer).personalId) : result;
    }
}

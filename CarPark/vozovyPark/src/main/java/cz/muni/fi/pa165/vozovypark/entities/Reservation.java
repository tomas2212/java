package cz.muni.fi.pa165.vozovypark.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Eduard Krak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Reservation.FIND_ALL, query = "SELECT p from Reservation p"),
    @NamedQuery(name = Reservation.FIND_BY_ID, query = "SELECT p from Reservation p where p.id=:id"),
    @NamedQuery(name = Reservation.FIND_BY_EMPLOYEE, query = "SELECT p from Reservation p where p.employee=:employee"),
    @NamedQuery(name = Reservation.FIND_BY_CAR, query = "SELECT p from Reservation p where p.car=:car"),
    @NamedQuery(name = Reservation.FIND_BY_CAR_AND_EMPLOYEE, query = "SELECT p from Reservation p where p.car=:car and p.employee =:employee"),
    @NamedQuery(name = Reservation.FIND_TO_CONFIRM, query = "SELECT p from Reservation p where p.confirmed is false"),
    @NamedQuery(name = Reservation.FIND_ACCEPTED, query = "SELECT p from Reservation p where p.confirmed is true")
})
public class Reservation implements Serializable {
    
    public static final String FIND_ALL = "findAllReservations";
    public static final String FIND_BY_ID = "findReservationById";
    public static final String FIND_BY_EMPLOYEE = "findReservationByEmployee";
    public static final String FIND_BY_CAR = "findReservationByCar";
    public static final String FIND_BY_CAR_AND_EMPLOYEE = "findReservationByCarAndEmployee";
    public static final String FIND_TO_CONFIRM = "findReservationsToConfirm";
    public static final String FIND_ACCEPTED = "findAcceptedReservations";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date returnDate;
    @OneToOne
    private Employee employee;
    @OneToOne
    private Car car;    
    private boolean confirmed = false;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.vozovypark.Reservation[ id=" + id + " ]";
    }
}
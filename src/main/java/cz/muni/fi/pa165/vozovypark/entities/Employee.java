package cz.muni.fi.pa165.vozovypark.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Lukas Maticky
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Employee.FIND_ALL, query = "SELECT p from Employee p"),
    @NamedQuery(name = Employee.FIND_BY_ID, query = "SELECT p from Employee p where p.id=:id"),
    @NamedQuery(name = Employee.FIND_BY_NAME, query = "SELECT p from Employee p where p.name=:name"),
    @NamedQuery(name = Employee.FIND_BY_LOGIN, query = "SELECT p from Employee p where p.login=:login"),
    @NamedQuery(name = Employee.FIND_BY_ADDRESS, query = "SELECT p from Employee p where p.address=:address")
})
public class Employee implements Serializable {
    
    public static final String FIND_ALL = "findAllEmp";
    public static final String FIND_BY_ID = "findEmployeById";
    public static final String FIND_BY_NAME = "findEmployeByName";
    public static final String FIND_BY_LOGIN = "findEmployeByLogin";
    public static final String FIND_BY_ADDRESS = "findEmployeByAddress";
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String position;
    private Boolean approve;
    private String address;
    @Column(unique=true)
    private String login;    
    private String password;    
    @OneToOne
    private CompanyLevel companyLevel;
    @ManyToMany
    private List<UserRole> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyLevel getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(CompanyLevel companyLevel) {
        this.companyLevel = companyLevel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id ;
    }
}
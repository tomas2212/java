package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukas Maticky
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    
    @PersistenceContext
    protected EntityManager entityManager;
  

    public EmployeeDAOImpl(EntityManagerFactory factory) {
        
    }
     @Deprecated
    public EmployeeDAOImpl(){
        
    }

    public Employee getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        return entityManager.find(Employee.class, id);
        //  return this.entityManager.find(Employee.class, id);
    }

    public Employee getEmployeeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("you must specify the name of employee");
        }
        
        Query q = entityManager.createNamedQuery(Employee.FIND_BY_NAME);
        q.setParameter("name", name);
        return (Employee) q.getSingleResult();
    }

    public Employee getEmployeeByAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("you must specify the address of employee");
        }
        
        Query q = entityManager.createNamedQuery(Employee.FIND_BY_ADDRESS);
        q.setParameter("address", address);
        return (Employee) q.getSingleResult();
    }

    public void insert(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify company level");
        } 
        entityManager.persist(employee);
       
    }

    public void remove(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
       
        entityManager.remove(entityManager.merge(employee));
        
    }

    public void update(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
       
        entityManager.merge(employee);
      
    }

    public List<Employee> getAllEmployee() {
        
        TypedQuery<Employee> q = (TypedQuery<Employee>) entityManager.createQuery("SELECT e FROM Employee e");
        return q.getResultList();
    }

    public List<Employee> getAllEmployeeWithHigherLevel(CompanyLevel companyLevel) {
          
          TypedQuery<Employee> q = entityManager.createQuery("SELECT e FROM Employee e INNER JOIN FETCH e.companyLevel as c WHERE (c.levelValue >= :companyLevelValue)", Employee.class);
          q.setParameter("companyLevelValue", companyLevel.getLevelValue());
          return q.getResultList();   

    }
}

package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andrej Bauer
 */
public class EmployeeDAOTest extends AbstractDAOTest {

    @Autowired
    private EmployeeDAO employeeDao;
    @Autowired
    private CompanyLevelDAO companyLevelDao;

    @Test
    public void findEmployeeTest() {
        Employee em = new Employee();
        em.setName("Ferdo M");
        employeeDao.insert(em);
        Employee em1 = employeeDao.getEmployeeById(em.getId());
        assertNotNull(em1);
        assertEquals(em, em1);

        em1 = employeeDao.getEmployeeById(34567L);
        assertNull(em1);
        //wrong parameters
        try {
            employeeDao.getEmployeeById(null);
            fail("queried with null ID");
        } catch (Exception e) {
            int a = 4;
        }
    }

    @Test
    public void insertTest() {
        Employee em = new Employee();
        em.setName("Ferdo M");
        employeeDao.insert(em);
        assertNotNull(em.getId());
        Employee em1 = employeeDao.getEmployeeById(em.getId());
        assertEquals(em, em1);

        //Wrong inputs
        try {
            employeeDao.insert(null);
            fail("inserted null");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void updateTest() {
        Employee em = new Employee();
        em.setName("Ferdo M");
        employeeDao.insert(em);
        em.setPosition("CEO");
        employeeDao.update(em);
        Employee em1 = employeeDao.getEmployeeById(em.getId());
        assertEquals(em1.getPosition(), "CEO");

        //Wrong inputs
        try {
            employeeDao.update(null);
            fail("Updated null entity");
        } catch (DataAccessException e) {
        }

        Employee nullId = new Employee();
        nullId.setPosition("CEO");
        try {
            employeeDao.update(nullId);
            fail("Updated with null ID");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void removeTest() {
        Employee em = new Employee();
        em.setName("Ferdo M");
        employeeDao.insert(em);
        Employee em1 = employeeDao.getEmployeeById(em.getId());
        Employee em2 = new Employee();
        em2.setName("Maro Potokolo");
        employeeDao.insert(em2);
        Long id = em.getId();
        employeeDao.remove(em);
        assertNull(employeeDao.getEmployeeById(id));
        assertNull(employeeDao.getEmployeeById(em.getId()));
        assertNotNull(employeeDao.getEmployeeById(em2.getId()));

        //wrong parameters
        try {
            employeeDao.remove(null);
            fail("Removed null");
        } catch (DataAccessException e) {
        }

        Employee nullId = new Employee();
        nullId.setPosition("CEO");
        try {
            employeeDao.update(nullId);
            fail("Removed with null ID");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void hierarchyTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(0);

        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(1);

        companyLevelDao.insert(cl1);
        companyLevelDao.insert(cl2);

        Employee ceo = new Employee();
        ceo.setPosition("CEO");
        ceo.setCompanyLevel(cl1);

        Employee assistent = new Employee();
        assistent.setPosition("asistent");
        assistent.setCompanyLevel(cl2);

        employeeDao.insert(ceo);
        employeeDao.insert(assistent);

        assertEquals(employeeDao.getAllEmployeeWithHigherLevel(cl1).size(), 2);
        assertEquals(employeeDao.getAllEmployeeWithHigherLevel(cl2).size(), 1);
    }

    @Test
    public void getAllEmployeesWithHigherLevelTest() {
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(2);

        companyLevelDao.insert(cl);
        int amount1 = employeeDao.getAllEmployeeWithHigherLevel(cl).size();

        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        companyLevelDao.insert(cl1);

        Employee employee1 = new Employee();
        employee1.setCompanyLevel(cl1);
        employeeDao.insert(employee1);


        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(3);
        companyLevelDao.insert(cl2);

        Employee employee2 = new Employee();
        employee2.setCompanyLevel(cl2);
        employeeDao.insert(employee2);

        int amount2 = employeeDao.getAllEmployeeWithHigherLevel(cl).size();

        assertEquals(amount1, amount2 - 1);
    }
}
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;

/**
 *
 * @author Lukas Maticky
 */
public interface EmployeeDAO {

    /**
     * Returns Employee by ID. Argument is ID of Employee
     *
     * @param id identifier
     * @return Employee Employee with that ID
     */
    public Employee getEmployeeById(Long id);

    /**
     * Returns Employee by Name. Argument is Name of Employee
     *
     * @param name identifier
     * @return Employee Employee with that Name
     */
    public Employee getEmployeeByName(String name);
    
    /**
     * Returns Employee by Login. Argument is Login Employee
     *
     * @param login identifier
     * @return Employee Employee with that login
     */
    public Employee getEmployeeByLogin(String login);

    /**
     * Returns Employee by Address. Argument is Address of Employee
     *
     * @param address identifier
     * @return Employee Employee with that Address *
     *
     */
    public Employee getEmployeeByAddress(String address);

    /**
     * Inserts a employee into database and collection
     *
     * @param employee New employee
     * @return nothing
     */
    public void insert(Employee employee);

    /**
     * Remove employee from the collection and database
     *
     * @param employee Employee, we want to delete
     * @return nothing
     */
    public void remove(Employee employee);

   /**
     * Updates specified employee
     *
     * @param employee Employee, which we want to change(update)
     * @return nothing
     */
    public void update(Employee employee);

    /**
     * Returns all employee from DB
     *
     * @param nothing
     * @return List of all employee
     */
    public List<Employee> getAllEmployee();

    /**
     * Returns list of all employee, which have equal companyLevel or higher.
     *
     * @param companyLevel  Hierarchy level of employees in the VozovyParkIS
     * @return List of all employee with the same hierarchy level or higher
     */
    public List<Employee> getAllEmployeeWithHigherLevel(CompanyLevel companyLevel);
}

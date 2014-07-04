package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import java.util.List;

/**
 *
 * @author Lukas Maticky
 */
public interface EmployeeService {

    /**
     * Creates employee in DB
     *
     * @param employee
     * @return new employee in DB
     */
    EmployeeDTO createEmployee(EmployeeDTO employee);

    /**
     * Updates employee in DB
     *
     * @param employee
     * @return updated employee
     */
    EmployeeDTO updateEmployee(EmployeeDTO employee);

    /**
     * Finding employee
     *
     * @param id
     * @return Employee with its ID
     */
    EmployeeDTO getEmployeeById(Long id);

    /**
     * List all employees in DB
     *
     * @return All employees in DB
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * Listing employees by company level
     *
     * @param companyLevel cl
     * @return all employees with higher CompanyLevel
     */
    List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel);

    /**
     * Removing employee from DB
     *
     * @param id employee to remove
     * @return void
     */
    void removeEmployee(Long id);
    
    EmployeeDTO getEmployeeByLogin(String login);
}
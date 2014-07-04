package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Maticky
 */
@Repository
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;
    private Mapper mapper;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee is not specified");
        }
        if (employee.getName() == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }
        Employee ead = mapper.map(employee, Employee.class);
        employeeDAO.insert(ead);

        return mapper.map(ead, EmployeeDTO.class);
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID is not specified");
        }
        Employee entity = mapper.map(employee, Employee.class);
        employeeDAO.update(entity);
        return mapper.map(entity, EmployeeDTO.class);

    }

    @PreAuthorize("hasRole('sysAdmin')")
    public void removeEmployee(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Employee with this ID does not exist");
        }
        employeeDAO.remove(employee);
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public EmployeeDTO getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Employee employeeById = employeeDAO.getEmployeeById(id);
        if (employeeById == null) {
            return null;
        }
        return mapper.map(employeeById, EmployeeDTO.class);
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();
        List<Employee> allEmployee = employeeDAO.getAllEmployee();
        for (Employee el : allEmployee) {
            employee.add(mapper.map(el, EmployeeDTO.class));
        }
        return employee;
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();
        CompanyLevel cl = mapper.map(companyLevel, CompanyLevel.class);
        List<Employee> clEmployees = employeeDAO.getAllEmployeeWithHigherLevel(cl);
        for (Employee e : clEmployees) {
            employee.add(mapper.map(e, EmployeeDTO.class));
        }
        return employee;
    }
}

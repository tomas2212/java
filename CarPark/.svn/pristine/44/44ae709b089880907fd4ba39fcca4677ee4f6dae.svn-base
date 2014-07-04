package cz.muni.fi.pa165.vozovypark.ServiceTests;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.service.EmployeeService;
import cz.muni.fi.pa165.vozovypark.service.EmployeeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrej Bauer
 */

public class EmployeeServiceTest extends AbstractServiceTest {

    @Autowired
    private EmployeeDAO employeeDao;
    @Autowired
    private EmployeeService employeeService;
    
    @Before
    public void setUp(){
        reset(employeeDao);
    }

    @Test
    public void testCreateEmployee() {

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName("Pero Parker");

        EmployeeDTO noNameDto = new EmployeeDTO();


        employeeService.createEmployee(employeeDto);
        verify(employeeDao, times(1)).insert(any(Employee.class));

        try {
            employeeService.createEmployee(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeDao was called before throwing exception
        verify(employeeDao, never()).insert(null);

        try {
            employeeService.createEmployee(noNameDto);
            fail("accepted no name");
        } catch (IllegalArgumentException e) {
        }


    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName("Pero Parker");
        employeeDto.setId(new Long(1));

        EmployeeDTO noIdDto = new EmployeeDTO();
        employeeDto.setName("sadf");

        employeeService.updateEmployee(employeeDto);
        verify(employeeDao, times(1)).update(any(Employee.class));

        try {
            employeeService.createEmployee(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeDao was called before throwing exception
        verify(employeeDao, never()).update(null);

        try {
            employeeService.createEmployee(noIdDto);
            fail("Implementation accepted no id");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(new Long(1));
        employee.setName("Johny Bravo");

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(new Long(1));
        dto.setName("Johny Bravo");

        when(employeeDao.getEmployeeById(new Long(1))).thenReturn(employee);
        EmployeeDTO employeeById = employeeService.getEmployeeById(new Long(1));
        assertEquals( dto, employeeById);
        
        verify(employeeDao, times(1)).getEmployeeById(eq(new Long(1)));

        try {
            employeeService.getEmployeeById(null);
            fail("accepted null id");

        } catch (IllegalArgumentException e) {
        }

        when(employeeDao.getEmployeeById(eq(new Long(2)))).thenReturn(null);
        assertNull(employeeService.getEmployeeById(new Long(2)));
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(new Long(1));
        employee1.setName("Johny Bravo");

        Employee employee2 = new Employee();
        employee2.setId(new Long(2));
        employee2.setName("Silvester Vlk");

        Employee employee3 = new Employee();
        employee3.setId(new Long(3));
        employee3.setName("Margareta Svietislnkova");

        EmployeeDTO employee1dto = new EmployeeDTO();
        employee1dto.setId(new Long(1));
        employee1dto.setName("Johny Bravo");

        EmployeeDTO employee2dto = new EmployeeDTO();
        employee2dto.setId(new Long(2));
        employee2dto.setName("Silvester Vlk");

        EmployeeDTO employee3dto = new EmployeeDTO();
        employee3dto.setId(new Long(3));
        employee3dto.setName("Margareta Svietislnkova");
        
        List<Employee> allEntities = new ArrayList<Employee>();
        allEntities.add(employee1);
        allEntities.add(employee2);
        allEntities.add(employee3);
        
        List<EmployeeDTO> allDTO = new ArrayList<EmployeeDTO>();
        allDTO.add(employee1dto);
        allDTO.add(employee2dto);
        allDTO.add(employee3dto);
        
        when(employeeDao.getAllEmployee()).thenReturn(allEntities);
        List<EmployeeDTO> returnedEmployees = employeeService.getAllEmployees();
        assertEquals(allDTO.size(), returnedEmployees.size());
        for(int i = 0; i < returnedEmployees.size(); i++){
            assertEquals(allDTO.get(i), returnedEmployees.get(i));
        }
        verify(employeeDao, times(1)).getAllEmployee();
        
    }

    @Test
    public void testGetEmployeesByCompanyLevel() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        cl1.setId(new Long(1));
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        cl2.setId(new Long(2));
        
        CompanyLevel cl3 = new CompanyLevel();
        cl3.setLevelValue(3);
        cl3.setId(new Long(3));
        
        CompanyLevelDTO cl1dto = new CompanyLevelDTO();
        cl1dto.setLevelValue(1);
        cl1dto.setId(new Long(1));
        
        CompanyLevelDTO cl2dto = new CompanyLevelDTO();
        cl2dto.setLevelValue(2);
        cl2dto.setId(new Long(2));
        
        CompanyLevelDTO cl3dto = new CompanyLevelDTO();
        cl3dto.setLevelValue(3);
        cl3dto.setId(new Long(3));
        
        Employee employee1 = new Employee();
        employee1.setId(new Long(1));
        employee1.setName("Johny Bravo");
        employee1.setCompanyLevel(cl1);

        Employee employee2 = new Employee();
        employee2.setId(new Long(2));
        employee2.setName("Silvester Vlk");
        employee2.setCompanyLevel(cl2);

        Employee employee3 = new Employee();
        employee3.setId(new Long(3));
        employee3.setName("Margareta Svietislnkova");
        employee3.setCompanyLevel(cl3);

        EmployeeDTO employee1dto = new EmployeeDTO();
        employee1dto.setId(new Long(1));
        employee1dto.setName("Johny Bravo");
        employee1dto.setCompanyLevel(cl1dto);

        EmployeeDTO employee2dto = new EmployeeDTO();
        employee2dto.setId(new Long(2));
        employee2dto.setName("Silvester Vlk");
        employee2dto.setCompanyLevel(cl2dto);

        EmployeeDTO employee3dto = new EmployeeDTO();
        employee3dto.setId(new Long(3));
        employee3dto.setName("Margareta Svietislnkova");
        employee2dto.setCompanyLevel(cl2dto);
        
        List<Employee> allEntities = new ArrayList<Employee>();
        allEntities.add(employee1);
        allEntities.add(employee2);
        allEntities.add(employee3);
        
        List<Employee> cl2Entities = new ArrayList<Employee>();
        cl2Entities.add(employee2);
        cl2Entities.add(employee3);
        
        when(employeeDao.getAllEmployee()).thenReturn(allEntities); //if in some case when implementation wants to call it
        when(employeeDao.getAllEmployeeWithHigherLevel(eq(cl2))).thenReturn(cl2Entities);
        List<EmployeeDTO> returnedEmployees = employeeService.getEmployeesByCompanyLevel(cl2dto);
        assertEquals(cl2Entities.size(), returnedEmployees.size());
        for(EmployeeDTO em : returnedEmployees){
            assertTrue(em.getCompanyLevel().getLevelValue() >= cl2dto.getLevelValue());
        }
        
        try{
            employeeService.getEmployeesByCompanyLevel(null);
            fail("Implementation accepts null value of company Level");
        }
        catch(IllegalArgumentException e){}
        

    }
    
    @Test
    public void testRemoveEmployee(){
       Employee employee = new Employee();
        employee.setId(new Long(1));
        employee.setName("Johny Bravo");

        when(employeeDao.getEmployeeById(new Long(1))).thenReturn(employee);
         
        employeeService.removeEmployee(new Long(1));
        verify(employeeDao, times(1)).remove(any(Employee.class));
        
        try{
            employeeService.removeEmployee(null);
            fail("accepted null id");
        }
        catch(IllegalArgumentException e) {}
    }
}

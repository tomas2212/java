package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import cz.muni.fi.pa165.vozovypark.service.EmployeeService;
import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tomas
 */
@UrlBinding("/company/{$event}/")
public class CompanyAdminActionBean implements ActionBean, LayoutPage {

    final static Logger log = LoggerFactory.getLogger(CompanyAdminActionBean.class);
    private ActionBeanContext context;
    @SpringBean(value = "mainMenu")
    private Menu mainMenu;
    @SpringBean(value = "companySubMenu")
    private Menu subMenu;
    @SpringBean(value = "CarService")
    private CarService carService;
    @SpringBean(value = "CompanyLevelService")
    private CompanyLevelService cls;
    @ValidateNestedProperties(value = {
        @Validate(on = {"createButtonCl", "saveButtonCl"}, field = "name", required = true)
    })
    private CompanyLevelDTO cld;
    @SpringBean(value = "EmployeeService")
    private EmployeeService employeeService;
    @ValidateNestedProperties(
    @Validate(on = {"createButtonEmployee", "saveButtonEmployee"}, field = "name", required = true))
    private EmployeeDTO employee;

    @Override
    public void setContext(ActionBeanContext abc) {
        context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public Menu getMainMenu() {
        mainMenu.setActiveItemByUrl("/company");
        return mainMenu;
    }

    @Override
    public void setMainMenu(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public Menu getSubMenu() {
        return subMenu;
    }

    @Override
    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    @DefaultHandler
    public Resolution employees() {
        this.subMenu.setActiveItemByName("/companyAdmin.employees");
        return new ForwardResolution("/companyAdmin/employees.jsp");
    }

    public Resolution addEmployee() {
        this.subMenu.setActiveItemByName("/companyAdmin.addEmployee");
        return new ForwardResolution("/companyAdmin/addEmployee.jsp");
    }

    public Resolution editCl() {
        this.subMenu.setActiveItemByUrl("/companyAdmin/addCompanyLevel");
        return new ForwardResolution("/companyAdmin/edit.jsp");
    }

    public Resolution editEmployee() {
        this.subMenu.setActiveItemByUrl("/carPark/addEmployee");
        return new ForwardResolution("/companyAdmin/editEmployee.jsp");
    }

    public Resolution cancelButtonCl() {
        return new ForwardResolution("/companyAdmin/companyLevels.jsp");
    }

    public Resolution cancelButtonEmployee() {
        return new ForwardResolution("/companyAdmin/employees.jsp");
    }

    public Resolution companyLevels() {
        this.subMenu.setActiveItemByName("/companyAdmin.companyLevels");
        return new ForwardResolution("/companyAdmin/companyLevels.jsp");
    }

    public Resolution addCompanyLevel() {
        this.subMenu.setActiveItemByName("/companyAdmin.addCompanyLevel");
        return new ForwardResolution("/companyAdmin/addCompanyLevel.jsp");
    }

    public List<CompanyLevelDTO> getAllCompanyLevels() {
        return cls.getAllCompanyLevels();
    }

    public CompanyLevelDTO getCompanyLevel() {
        return cld;
    }

    public void setCompanyLevel(CompanyLevelDTO cld) {
        this.cld = cld;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"editCl"})
    public void loadClFromDatabase() {
        String ids = context.getRequest().getParameter("companyLevel.id");
        if (ids == null) {
            return;
        }
        cld = cls.getCompanyLevelById(Long.parseLong(ids));
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public Resolution createButtonCl() {
        cls.createCompanyLevel(cld.getName());
        return new RedirectResolution(this.getClass(), "companyLevels");
    }

    public Resolution createButtonEmployee() {
        String cs = context.getRequest().getParameter("employee.companyLevel");
        if (cs != null) {
            CompanyLevelDTO cl = cls.getCompanyLevelById(Long.parseLong(cs));
            employee.setCompanyLevel(cl);
        }

        employeeService.createEmployee(employee);
        return new RedirectResolution(this.getClass(), "employees");
    }

    public Resolution saveButtonCl() {
        cls.updateCompanyLevel(cld);
        return new RedirectResolution(this.getClass(), "companyLevels");
    }

    public Resolution saveButtonEmployee() {
        String cs = context.getRequest().getParameter("employee.companyLevel");
        if (cs != null) {
            CompanyLevelDTO cl = cls.getCompanyLevelById(Long.parseLong(cs));
            employee.setCompanyLevel(cl);
        }
        employeeService.updateEmployee(employee);
        return new RedirectResolution(this.getClass(), "employees");
    }

    public Resolution deleteCl() {
        String ids = context.getRequest().getParameter("companyLevel.id");
        if (ids != null) {
            cls.removeCompanyLevel(Long.parseLong(ids));
        }
        return new RedirectResolution(this.getClass(), "companyLevels");
    }

    public Resolution deleteEmployee() {

        String ids = context.getRequest().getParameter("employee.id");
        if (ids != null) {
            employeeService.removeEmployee(Long.parseLong(ids));
        }
        return new RedirectResolution(this.getClass(), "employees");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"editEmployee"})
    public void loadEFromDatabase() {
        String ids = context.getRequest().getParameter("employee.id");
        if (ids == null) {
            return;
        }
        employee = employeeService.getEmployeeById(Long.parseLong(ids));
    }
}

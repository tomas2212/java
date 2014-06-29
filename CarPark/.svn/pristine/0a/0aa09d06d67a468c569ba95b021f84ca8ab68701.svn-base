package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.EmployeeService;
import cz.muni.fi.pa165.vozovypark.service.ReservationService;
import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import cz.muni.fi.pa165.vozovypark.web.menu.MenuItem;
import java.util.List;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author andrej
 */
@UrlBinding("/reservation/{$event}")
public class ReservationsActionBean extends LayoutPage{
    
    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(ReservationsActionBean.class);
    @SpringBean(value = "ReservationService")
    private ReservationService rs;
    
    @SpringBean(value = "EmployeeService")
    private EmployeeService employeeService;
    
    @SpringBean(value = "CarService")
    private CarService carService;
    
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "dateFrom", required = true),
        @Validate(on = {"create", "update"}, field = "dateTo", required = true),
        @Validate(on = {"create", "update"}, field = "car", required = true),
    })
    
    @SpringBean(value="reservationsSubMenu")
    private Menu subMenu;

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
        Menu menu = super.getMainMenu();
        menu.setActiveItemByUrl("/reservation");
        return menu;
    }

    @Override
    public Menu getSubMenu() {       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();         
      
        Menu authorisedMenu = new Menu();
        for(MenuItem item : subMenu.getMenuItems()){
            if(item.getUrl().equals("/reservation/acceptReservations")){
                for(GrantedAuthority auth : authentication.getAuthorities()){
                    if(auth.getAuthority().equals("manager")){
                        authorisedMenu.addMenuItem(item);
                    }
                }
            }
            else{
                authorisedMenu.addMenuItem(item);
            }
        }
        return authorisedMenu;
    }

    @Override
    public void setSubMenu(Menu subMenu) {        
        this.subMenu = subMenu;
    }
    
        
   @DefaultHandler
    public Resolution myReservations() {     
        this.subMenu.setActiveItemByName("reservationsSubMenu.myReservations");
        return new ForwardResolution("/reservations/myReservations.jsp");
    }
    
    public Resolution allReservations() {     
        this.subMenu.setActiveItemByName("reservationsSubMenu.allReservations");
        return new ForwardResolution("/reservations/reservations.jsp");
    }
     
    public Resolution newReservation(){
        this.subMenu.setActiveItemByUrl("/reservations/newReservation");
        return new ForwardResolution("/reservations/newReservation.jsp");
    }
    
    public Resolution acceptReservations(){
        this.subMenu.setActiveItemByUrl("/reservations/acceptReservations");
        return new ForwardResolution("/reservations/acceptReservations.jsp");
    }
    
    public List<ReservationDTO> getAllReservations() {
        //return rs.getAllReservations();
        return rs.getAllReservations();
    }
    
    public List<ReservationDTO> getMyReservations() {
        EmployeeDTO employee = employeeService.getEmployeeById(new Long(1));
        return rs.getReservationsByEmployee(employee);
    }
    
    public List<ReservationDTO> getUnconfirmedReservations() {
        return rs.getReservationsToConfirm();
    }
    
    public List<ReservationDTO> getAcceptedReservations() {
        return rs.getAcceptedReservations();
    }
    
    public List<CarDTO> getCars() {
        return carService.getAllCars();
    }
    
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }
    
    ReservationDTO resDTO;

    public ReservationDTO getResDTO() {
        return resDTO;
    }

    public void setResDTO(ReservationDTO resDTO) {
        this.resDTO = resDTO;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "confirm"})
    public void loadResFromDatabase() {
        String ids = context.getRequest().getParameter("resDTO.id");
        if (ids == null) {
            return;
        }
        resDTO = rs.getReservationById(Long.parseLong(ids));
    }
    
     public Resolution storno() {
        return new ForwardResolution("reservations/reservations.jsp");
    }
     
     public Resolution create() {
         String carId = context.getRequest().getParameter("resDTO.car");
         String employeeId = "1";
         if(carId != null) {
             CarDTO carDTO = carService.getCarById(Long.parseLong(carId));
             resDTO.setCar(carDTO);
         }
         
         if(employeeId !=null) {
             EmployeeDTO employeeDTO = employeeService.getEmployeeById(Long.parseLong(employeeId));
             resDTO.setEmployee(employeeDTO);
         }
         
         rs.createReservation(resDTO);
         return new RedirectResolution(this.getClass(), "myReservations");
     }

    public Resolution edit() {
        log.debug("edit() reservation={}", resDTO);
        return new ForwardResolution("/reservations/editReservation.jsp");
    }

    public Resolution update() {
        log.debug("save() reservation={}", resDTO);
        resDTO.setConfirmed(false);
        String carId = context.getRequest().getParameter("resDTO.car");
         String employeeId = context.getRequest().getParameter("resDTO.employee");
         if(carId != null) {
             CarDTO carDTO = carService.getCarById(Long.parseLong(carId));
             resDTO.setCar(carDTO);
         }
         
         if(employeeId !=null) {
             EmployeeDTO employeeDTO = employeeService.getEmployeeById(Long.parseLong(employeeId));
             resDTO.setEmployee(employeeDTO);
         }
        rs.updateReservation(resDTO);
        return new RedirectResolution(this.getClass(), "myReservations");
    }

    public Resolution delete() {
        log.debug("delete({})", resDTO.getId());
        rs.removeReservation(resDTO.getId());
        return new RedirectResolution(this.getClass(), "myReservations");
    }
    
    public Resolution confirm() {
        log.debug("confirm() reservation={}", resDTO);
        resDTO.setConfirmed(true);         
        rs.updateReservation(resDTO);
        return new RedirectResolution(this.getClass(), "myReservations");
    }   
}
package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import cz.muni.fi.pa165.vozovypark.service.EmployeeService;
import cz.muni.fi.pa165.vozovypark.service.ReservationService;
import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author andrej
 */
@UrlBinding("/carPark/{$event}/{car.id}")
public class CarParkAdminActionBean implements ActionBean, LayoutPage {

    private ActionBeanContext context;
    private boolean editing;
    @SpringBean(value = "mainMenu")
    private Menu mainMenu;
    @SpringBean(value = "carParkSubMenu")
    private Menu subMenu;
    @SpringBean(value = "CarService")
    private CarService carService;
    @SpringBean(value = "CompanyLevelService")
    private CompanyLevelService cs;
    @SpringBean(value = "ReservationService")
    private ReservationService rs;
    @SpringBean(value = "EmployeeService")
    private EmployeeService employeeService;
    private CarDTO car;

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
        mainMenu.setActiveItemByUrl("/carPark");
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
    public Resolution release() {
        this.subMenu.setActiveItemByName("carPark.release");

        return new ForwardResolution("/carAdmin/release.jsp");
    }

    public Resolution releaseCar() {
        String cls = context.getRequest().getParameter("carId");
        if (cls != null) {
            CarDTO carDTO = carService.getCarById(Long.parseLong(cls));
            carDTO.setAvailable(false);
            carService.updateCar(car);
        }

        return new RedirectResolution(this.getClass(), "release");
    }

    public Resolution receive() {
        this.subMenu.setActiveItemByName("carPark.receive");

        return new ForwardResolution("/carAdmin/receive.jsp");
    }

    public Resolution receiveCar() {
        String cls = context.getRequest().getParameter("carId");
        if (cls != null) {
            CarDTO carDTO = carService.getCarById(Long.parseLong(cls));
            carDTO.setAvailable(false);
            carService.updateCar(car);
        }

        return new RedirectResolution(this.getClass(), "receive");
    }

    public Resolution cars() {

        this.subMenu.setActiveItemByName("carPark.cars");
        return new ForwardResolution("/carAdmin/cars.jsp");
    }

    public Resolution addCar() {
        this.subMenu.setActiveItemByUrl("/carPark/addCar");
        return new ForwardResolution("/carAdmin/addCar.jsp");
    }

    public Resolution editCar() {
        this.subMenu.setActiveItemByUrl("/carPark/addCar");
        return new ForwardResolution("/carAdmin/editCar.jsp");
    }

    public Resolution storno() {
        return new ForwardResolution("/carAdmin/cars.jsp");
    }

    public Resolution create() {
        String cls = context.getRequest().getParameter("car.companyLevel");
        if (cls != null) {
            CompanyLevelDTO cl = cs.getCompanyLevelById(Long.parseLong(cls));
            car.setCompanyLevel(cl);
        }

        carService.createCar(car);
        return new RedirectResolution(this.getClass(), "cars");
    }

    public Resolution update() {
        String cls = context.getRequest().getParameter("car.companyLevel");
        if (cls != null) {
            CompanyLevelDTO cl = cs.getCompanyLevelById(Long.parseLong(cls));
            car.setCompanyLevel(cl);
        }
        carService.updateCar(car);

        return new RedirectResolution(this.getClass(), "cars");
    }

    public Resolution deleteCar() {

        String ids = context.getRequest().getParameter("car.id");
        if (ids != null) {

            carService.removeCar(Long.parseLong(ids));
        }
        return new RedirectResolution(this.getClass(), "cars");
    }

    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"editCar"})
    public void loadCarFromDatabase() {
        String ids = context.getRequest().getParameter("car.id");
        if (ids == null) {
            return;
        }

        car = carService.getCarById(Long.parseLong(ids));
    }

    public List<CompanyLevelDTO> getAllCompanyLevels() {
        return cs.getAllCompanyLevels();
    }

    public List<ReservationDTO> getCarsToRelease() {
        List<ReservationDTO> result = new ArrayList<ReservationDTO>();
        List<ReservationDTO> acceptedReservations = rs.getAcceptedReservations();
        Date now = new Date();
        for (ReservationDTO res : acceptedReservations) {
            if (res.getCar().getAvailable() && res.getDateTo().after(now)) {
                result.add(res);
            }
        }
        return result;
    }

    public List<ReservationDTO> getCarsToReceive() {
        List<ReservationDTO> result = new ArrayList<ReservationDTO>();
        List<ReservationDTO> acceptedReservations = rs.getAcceptedReservations();

        for (ReservationDTO res : acceptedReservations) {
            if (!res.getCar().getAvailable()) {
                result.add(res);
            }
        }
        return result;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}

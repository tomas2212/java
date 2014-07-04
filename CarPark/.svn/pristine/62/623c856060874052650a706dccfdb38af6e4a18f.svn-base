package cz.muni.fi.pa165.vozovypark.carparkrest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author andrej
 */
@WebServlet(urlPatterns = "/cars/*")
public class CarRest extends HttpServlet {

    final static Logger log = LoggerFactory.getLogger(CarRest.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    CompanyLevelService companyLevelService;
    CarService carService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Object obj = config.getServletContext().getAttribute("companyLevelService");
        companyLevelService = (CompanyLevelService) obj;
        obj = config.getServletContext().getAttribute("carService");
        carService = (CarService) obj;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(sdf);
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            if (req.getParameter("companyLevel") != null) {
                long id = Long.valueOf(req.getParameter("companyLevel")).longValue();
                try {
                    mapper.writeValue(resp.getOutputStream(), carCollectionToMap(getCarsByCompanyLevel(id)));
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("getCars");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            } else {
                try {
                    mapper.writeValue(resp.getOutputStream(), carCollectionToMap(getCars()));
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("getCars");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }

            }
        } else {
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if (pathArray[1] != null) {
                Long id = Long.parseLong(pathArray[1]);
                try {
                    CarDTO dto = carService.getCarById(id);
                    if (dto != null) {

                        mapper.writeValue(resp.getOutputStream(), dto);
                    } else {
                        resp.setStatus(404);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setOperation("getCars");
                    os.setStatus("failed");
                    os.setCausedBy(ex.getMessage());
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            OperationStatus os = new OperationStatus();
            os.setCausedBy("Deleting all cars is not supported");
            os.setOperation("delete");
            os.setStatus("failed");
            resp.setStatus(403);
            mapper.writeValue(resp.getOutputStream(), os);
        } else {
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if (pathArray[1] != null) {
                try {
                    Long id = Long.parseLong(pathArray[1]);
                    CarDTO dto = carService.getCarById(id);
                    if (dto != null) {
                        carService.removeCar(dto.getId());
                        OperationStatus os = new OperationStatus();
                        os.setOperation("delete");
                        os.setStatus("successful");
                        mapper.writeValue(resp.getOutputStream(), os);
                    } else {
                        OperationStatus os = new OperationStatus();
                        os.setCausedBy("Car not found");
                        os.setOperation("delete");
                        os.setStatus("failed");
                        resp.setStatus(404);
                        mapper.writeValue(resp.getOutputStream(), os);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("delete");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OperationStatus os = new OperationStatus();
        os.setOperation("create");
        os.setCausedBy("Input not valid");
        os.setStatus("failed");
        JsonNode jsonNode = mapper.readValue(request.getInputStream(), JsonNode.class);
        if (jsonNode != null && !jsonNode.isMissingNode()) {
            CarDTO carDTO = new CarDTO();

            if (jsonNode.get("spz") != null && jsonNode.hasNonNull("spz")
                    && jsonNode.get("brand") != null && jsonNode.hasNonNull("brand")
                    && jsonNode.get("model") != null && jsonNode.hasNonNull("model")
                    && jsonNode.get("creationYear") != null && jsonNode.hasNonNull("creationYear")
                    && jsonNode.get("available") != null && jsonNode.hasNonNull("available")) {
                try {
                    carDTO.setSpz(jsonNode.get("spz").asText());
                    carDTO.setBrand(jsonNode.get("brand").asText());
                    Date date = sdf.parse(jsonNode.get("creationYear").asText());
                    carDTO.setCreationYear(date);
                    carDTO.setAvailable(jsonNode.get("available").asBoolean());
                    carService.createCar(carDTO);
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    os.setCausedBy(ex.getMessage());
                    response.setStatus(500);
                    mapper.writeValue(response.getOutputStream(), os);
                }
                response.setStatus(201);
                mapper.writeValue(response.getOutputStream(), carDTO);
            } else {
                response.setStatus(500);
                mapper.writeValue(response.getOutputStream(), os);
            }
        }
        response.setStatus(500);
        mapper.writeValue(response.getOutputStream(), os);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            OperationStatus os = new OperationStatus();
            os.setCausedBy("Updating all cars is not supported");
            os.setOperation("update");
            os.setStatus("failed");
            resp.setStatus(403);
            mapper.writeValue(resp.getOutputStream(), os);
        } else {
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if (pathArray[1] != null) {
                Long id = Long.parseLong(pathArray[1]);
                try {
                    CarDTO dto = carCollectionToMap(getCars()).get(id);
                    if (dto != null) {
                        JsonNode jsonNode = mapper.readValue(req.getInputStream(), JsonNode.class);
                        if (jsonNode != null && !jsonNode.isMissingNode()) {
                                if (jsonNode.get("model") != null && jsonNode.hasNonNull("model")) {
                                dto.setModel(jsonNode.get("model").asText());
                            }
                            if (jsonNode.get("brand") != null && jsonNode.hasNonNull("brand")) {
                                dto.setBrand(jsonNode.get("brand").asText());
                            }
                            if (jsonNode.get("spz") != null && jsonNode.hasNonNull("spz")) {
                                dto.setSpz(jsonNode.get("spz").asText());
                            }
                            if (jsonNode.get("available") != null && jsonNode.hasNonNull("available")) {
                                dto.setAvailable(jsonNode.get("available").asBoolean());
                            }
                            if (jsonNode.get("companyLevel") != null && jsonNode.hasNonNull("companyLevel")) {
                                CompanyLevelDTO clDto = companyLevelService.getCompanyLevelById(Long.parseLong(jsonNode.get("companyLevel").asText()));
                                dto.setCompanyLevel(clDto);
                            }
                            if (jsonNode.get("creationYear") != null && jsonNode.hasNonNull("creationYear")) {
                                Date date = sdf.parse(jsonNode.get("creationYear").asText());
                                dto.setCreationYear(date);
                            }
                        }
                        carService.updateCar(dto);
                        OperationStatus os = new OperationStatus();
                        os.setOperation("update");
                        os.setStatus("successful");
                        mapper.writeValue(resp.getOutputStream(), os);
                    } else {
                        OperationStatus os = new OperationStatus();
                        os.setCausedBy("Car not found");
                        os.setOperation("update");
                        os.setStatus("failed");
                        resp.setStatus(404);
                        mapper.writeValue(resp.getOutputStream(), os);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("update");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            }
        }
    }

    private Map<Long, CarDTO> carCollectionToMap(Collection<CarDTO> cars) {
        Map<Long, CarDTO> map = new HashMap<Long, CarDTO>();
        for (CarDTO dto : cars) {
            map.put(dto.getId(), dto);
        }
        return map;
    }

    private Collection<CarDTO> getCars() {
        List<CarDTO> result = carService.getAllCars();
        return result;
    }

    private Collection<CarDTO> getCarsByCompanyLevel(long id) {
        CompanyLevelDTO clDTO = companyLevelService.getCompanyLevelById(id);
        if (clDTO != null) {
            List<CarDTO> cars = carService.getCarsByCompanyLevel(clDTO);
            return cars;
        } else {
            throw new IllegalArgumentException("Company level with given id does not exist");
        }
    }
}
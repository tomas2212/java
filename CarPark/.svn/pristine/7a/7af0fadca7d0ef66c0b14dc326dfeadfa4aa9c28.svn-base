package cz.muni.fi.pa165.vozovypark.carparkrest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
@WebServlet(urlPatterns = "/companyLevels/*")
public class CompanyLevelRest extends HttpServlet {
    final static Logger log = LoggerFactory.getLogger(CompanyLevelRest.class);
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
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            try {
                mapper.writeValue(resp.getOutputStream(), companyLevelCollectionToMap(companyLevelService.getAllCompanyLevels()));
            } catch (Exception e) {
                OperationStatus os = new OperationStatus();
                os.setCausedBy(e.getMessage());
                os.setOperation("getCompanyLevels");
                os.setStatus("failed");
                resp.setStatus(500);
                mapper.writeValue(resp.getOutputStream(), os);
            }
        } else {
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if (pathArray[1] != null) {
                try {
                    Long id = Long.parseLong(pathArray[1]);
                    CompanyLevelDTO dto = companyLevelService.getCompanyLevelById(id);
                    if (dto != null) {
                        mapper.writeValue(resp.getOutputStream(), dto);
                    } else {
                        resp.setStatus(404);
                    }
                } catch (Exception e) {
                    OperationStatus os = new OperationStatus();
                    os.setOperation("getCompanyLevels");
                    os.setStatus("failed");
                    os.setCausedBy(e.getMessage());
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
            os.setCausedBy("Deleting all companyLevels is not supported");
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
                    CompanyLevelDTO dto = companyLevelService.getCompanyLevelById(id);
                    if (dto != null) {
                        companyLevelService.removeCompanyLevel(dto.getId());
                        OperationStatus os = new OperationStatus();
                        os.setOperation("delete");
                        os.setStatus("successful");
                        mapper.writeValue(resp.getOutputStream(), os);
                    } else {
                        OperationStatus os = new OperationStatus();
                        os.setCausedBy("CompanyLevel not found");
                        os.setOperation("delete");
                        os.setStatus("failed");
                        resp.setStatus(404);
                        mapper.writeValue(resp.getOutputStream(), os);
                    }
                } catch (Exception e) {
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(e.getMessage());
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
            CompanyLevelDTO clDTO = new CompanyLevelDTO();

            if (jsonNode.get("name") != null && jsonNode.hasNonNull("name")) {
                clDTO.setName(jsonNode.get("name").asText());

                try {
                    companyLevelService.createCompanyLevel(clDTO.getName());
                } catch (Exception e) {
                    os.setCausedBy(e.getMessage());
                    response.setStatus(500);
                    mapper.writeValue(response.getOutputStream(), os);
                }
                response.setStatus(201);
                mapper.writeValue(response.getOutputStream(), clDTO);
                return;
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
            os.setCausedBy("Updating all companyLevels is not supported");
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
                    CompanyLevelDTO dto = companyLevelService.getCompanyLevelById(id);
                    if (dto != null) {
                        JsonNode jsonNode = mapper.readValue(req.getInputStream(), JsonNode.class);
                        if (jsonNode != null && !jsonNode.isMissingNode()) {
                            if (jsonNode.get("name") != null && jsonNode.hasNonNull("name")) {
                                dto.setName(jsonNode.get("name").asText());
                            }
                            if (jsonNode.get("levelValue") != null && jsonNode.hasNonNull("levelValue")) {
                                dto.setLevelValue(jsonNode.get("levelValue").asInt());
                            }
                            companyLevelService.updateCompanyLevel(dto);
                            OperationStatus os = new OperationStatus();
                            os.setOperation("update");
                            os.setStatus("successful");
                            mapper.writeValue(resp.getOutputStream(), os);
                        }
                    } else {
                        OperationStatus os = new OperationStatus();
                        os.setCausedBy("CompanyLevel not found");
                        os.setOperation("update");
                        os.setStatus("failed");
                        resp.setStatus(404);
                        mapper.writeValue(resp.getOutputStream(), os);
                    }
                } catch (Exception e) {
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(e.getMessage());
                    os.setOperation("update");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            }
        }
    }

    protected void getCompanyLevels(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    private Map<Long, CompanyLevelDTO> companyLevelCollectionToMap(Collection<CompanyLevelDTO> companyLevels) {
        Map<Long, CompanyLevelDTO> map = new HashMap<Long, CompanyLevelDTO>();
        for (CompanyLevelDTO dto : companyLevels) {
            map.put(dto.getId(), dto);
        }
        return map;
    }

    private Collection<CompanyLevelDTO> getCls() {
        List<CompanyLevelDTO> result = companyLevelService.getAllCompanyLevels();
        return result;
    }
}

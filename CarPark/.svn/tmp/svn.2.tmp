/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author andrej
 */

@WebServlet(urlPatterns = "/api/*")
public class ApiServlet  extends HttpServlet{
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        if(path.startsWith("/companyLevels")){
            getCompanyLevels(req, resp);
            
        }
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path.startsWith("/companyLevels")){
            deleteCompanyLevels(req, resp);
            
        }
    }
    
    protected void deleteCompanyLevels(HttpServletRequest req, HttpServletResponse resp){
        
    }
   
        
    
    protected void getCompanyLevels(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String path = req.getPathInfo();
        if(path.equals("/companyLevels") || path.equals("/companyLevels/")){
           mapper.writeValue(resp.getOutputStream(), companyLevelCollectionToMap(getCls()));
            
        }
        else{
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if(pathArray[1] != null){
                Long id = Long.parseLong(pathArray[2]);
                //tiez docasnu kod begin
                CompanyLevelDTO dto = companyLevelCollectionToMap(getCls()).get(id);
                //tiez docasny kod end
                if(dto != null){
                    mapper.writeValue(resp.getOutputStream(), dto);
                }
                else{
                    resp.setStatus(404);
                }
            }
            
        }
        
    }
    
    private Map<Long, CompanyLevelDTO> companyLevelCollectionToMap(Collection<CompanyLevelDTO> companyLevels){
        Map<Long, CompanyLevelDTO> map = new HashMap<Long, CompanyLevelDTO>();
        for(CompanyLevelDTO dto: companyLevels){
            map.put(dto.getId(), dto);
        }
        return map;
    }
    
    
    
    private Collection<CompanyLevelDTO> getCls(){
        List<CompanyLevelDTO> result = new ArrayList<CompanyLevelDTO>();
        CompanyLevelDTO cl = new CompanyLevelDTO();
        cl.setId(new Long(1));
        cl.setLevelValue(new Integer(1));
        cl.setName("fadsf");
        result.add(cl);
        cl = new CompanyLevelDTO();
        cl.setId(new Long(2));
        cl.setLevelValue(new Integer(2));
        cl.setName("asdf");
        result.add(cl);
        
        return result;
        
    }
    
    
    
}

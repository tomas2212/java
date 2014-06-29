package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author Andrej Bauer
 */
public interface CompanyLevelService {
    
    /**
     * Creates new Company Level
     * only parameters is name. CompanyLevel value will be automaticaly genereted as bottom value
     * @param CompanyLevelName
     * @return DTO of newly created companyLevel value
     */
    CompanyLevelDTO createCompanyLevel(String CompanyLevelName);
    
    /**
     * Updated existing company Level
     * @param CopmanyLevelDTO
     * @return return updated CompanyLevelDTO originally from persistance layer
     */
    CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel);
    
    /**
     * Set existing company level as root
     * @param id of company level
     * @return return updated CompanyLevelDTO originally from persistance layer
     */
    CompanyLevelDTO setCompanyLevelAsRoot(Long id);
    
    /**
     * Set company tu current position. It moved all other companyLevel if it is nessecery
     * @param id
     * @param position
     * @return return updated CompanyLevelDTO originally from persistance layer
     */
    CompanyLevelDTO setCompanyLevelToPosition(Long id, Integer position);
    
    /**
     * Gets comapny level by its ID
     * @param id
     * @return return CompanyLevelDTO originally from persistance layer
     */
    CompanyLevelDTO getCompanyLevelById(Long id);
    
    /**
     * Get all company levels
     * @return list of company levels
     */
    List<CompanyLevelDTO> getAllCompanyLevels();
    
    /**
     * Remove company level 
     * @param id of companyLevel
     */
    void removeCompanyLevel(Long id);
    
}

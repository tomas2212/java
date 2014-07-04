package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author Andrej Bauer
 */
public interface CompanyLevelService {

    /**
     * Creates new Company Level only parameters is name. CompanyLevel value
     * will be automatically generated as bottom value
     *
     * @param CompanyLevelName
     * @return DTO of newly created companyLevel value
     */
    CompanyLevelDTO createCompanyLevel(String CompanyLevelName);

    /**
     * Updated existing company Level
     *
     * @param CopmanyLevelDTO
     * @return return updated CompanyLevelDTO originally from persistence layer
     */
    CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel);

    /**
     * Set company to current position. It moved all other companyLevel if it is
     * necessary
     *
     * @param id
     * @param position
     * @return return updated CompanyLevelDTO originally from persistence layer
     */
    CompanyLevelDTO setCompanyLevelToPosition(Long id, Integer position);

    /**
     * Gets company level by its ID
     *
     * @param id
     * @return return CompanyLevelDTO originally from persistence layer
     */
    CompanyLevelDTO getCompanyLevelById(Long id);

    /**
     * Get all company levels
     *
     * @return list of company levels
     */
    List<CompanyLevelDTO> getAllCompanyLevels();

    /**
     * Remove company level
     *
     * @param id of companyLevel
     */
    void removeCompanyLevel(Long id);
}

package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrej Bauer
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyLevelService {

    CompanyLevelDAO companyLevelDao;
    
    Mapper mapper;

    public void setCompanyLevelDao(CompanyLevelDAO companyLevelDao) {
        this.companyLevelDao = companyLevelDao;
    }
    
    public void setMapper(Mapper mapper){
        this.mapper = mapper;
    }
    
    
    
    public CompanyLevelDTO createCompanyLevel(String companyLevelName) {
        if(companyLevelName == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        Integer levelValue = 0;
     if(companyLevelDao.getMaxLevelValue() != null){
            levelValue = companyLevelDao.getMaxLevelValue() + 1;
        }
        CompanyLevel companyLevel = new CompanyLevel();
        companyLevel.setName(companyLevelName);
        companyLevel.setLevelValue(levelValue);
        companyLevelDao.insert(companyLevel);
       
        return mapper.map(companyLevel, CompanyLevelDTO.class);
    }

    public CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("CompanyLevel ID is not specified");
        }
        CompanyLevel entity = mapper.map(companyLevel, CompanyLevel.class);
        companyLevelDao.update(entity);
        return mapper.map(entity, CompanyLevelDTO.class);
        
    }

    public CompanyLevelDTO setCompanyLevelAsRoot(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        Integer rootValue = companyLevelDao.getMinLevelValue();
        
        companyLevel.setLevelValue(rootValue - 1);
        companyLevelDao.update(companyLevel);
        return mapper.map(companyLevel, CompanyLevelDTO.class);
        
    }

    public CompanyLevelDTO setCompanyLevelToPosition(Long id, Integer position) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        List<CompanyLevel> allCompanyLevels = companyLevelDao.getAllCompanyLevels();
        for(CompanyLevel cl : allCompanyLevels){
            if(cl.getLevelValue() >= position){
                cl.setLevelValue(cl.getLevelValue() + 1);
                companyLevelDao.update(cl);
            }
        }
        companyLevel.setLevelValue(position);
        companyLevelDao.update(companyLevel);
        
        return mapper.map(companyLevel, CompanyLevelDTO.class);
    }

    public CompanyLevelDTO getCompanyLevelById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevelById = companyLevelDao.getCompanyLevelById(id);
        if(companyLevelById == null){
            return null;
        }
        return mapper.map(companyLevelById, CompanyLevelDTO.class);
    }

    public List<CompanyLevelDTO> getAllCompanyLevels() {
        List<CompanyLevelDTO> companyLevels = new ArrayList<CompanyLevelDTO>();
        List<CompanyLevel> allCompanyLevels = companyLevelDao.getAllCompanyLevels();
        for(CompanyLevel cl : allCompanyLevels){
            companyLevels.add(mapper.map(cl, CompanyLevelDTO.class));
        }
        return companyLevels;
    }

    public void removeCompanyLevel(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        companyLevelDao.remove(companyLevel);
        
    }
    
    
    
}

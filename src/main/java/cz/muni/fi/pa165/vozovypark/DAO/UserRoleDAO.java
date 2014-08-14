package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.UserRole;
import java.util.List;

/**
 * Dao for entity {@link UserRolel} provides data operations into database
 * @author Andrej Bauer
 */
public interface UserRoleDAO {
    
    /**
     * Create {@link UserRole} in the database
     * @param userRole 
     */
    void insert(UserRole userRole);
    
    /**
     * Update attributes of current {@link UserRole} in database
     * Parameter userRole must be identified with his attribute id
     * @param userRole 
     */
    void update(UserRole userRole);
    
    /**
     * Remove {@link UserRole} given in parameter from database
     * Parameter userRole must be identified with his attribute id
     * @param userRole 
     */
    void remove(UserRole userRole);
    
    /**
     * Finds {@link UserRole} with given id in database
     * @param id
     * @return {@link UserRole} if exist
     */
    UserRole getUserRoleByName(String name);
    
    /**
     * Returns all  {@link UserRole} entities from database
     * @return  {@link List} of all  {@link UserRole} in database
     */
    List<UserRole> getAllUserRoles();    
    
   
}

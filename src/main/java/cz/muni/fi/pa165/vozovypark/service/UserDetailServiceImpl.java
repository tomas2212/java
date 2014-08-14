package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DAO.UserRoleDAO;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrej
 */
@Service("userDetailsService") 
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    @Qualifier("employeeDao")
    private EmployeeDAO dao;
    
    @Autowired
    UserRoleDAO userRoleDao;
    
    public void setEmployeeDao(EmployeeDAO dao){
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {
        
        if(string.equals("superuser")){
            if(userRoleDao.getAllUserRoles().size() == 0){
                UserRole userRole = new UserRole();
                userRole.setName("manager");
                userRoleDao.insert(userRole);
                userRole = new UserRole();
                userRole.setName("sysAdmin");
                userRoleDao.insert(userRole);
                userRole = new UserRole();
                userRole.setName("carAdmin");
                userRoleDao.insert(userRole);
            }
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new GrantedAuthorityImpl("manager") );
            authorities.add(new GrantedAuthorityImpl("sysAdmin") );
            authorities.add(new GrantedAuthorityImpl("carAdmin") );
            return new User("superuser", "bed77bf881c52caf14da5d0b9cd84bc8", true, true, true, true, authorities);
            
        }
        
        Employee userEntity = dao.getEmployeeByLogin(string);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        

        return buildUserFromUserEntity(userEntity);

    }

    User buildUserFromUserEntity(Employee userEntity) {
      
        String username = userEntity.getLogin();
        String password = userEntity.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       for (UserRole role : userEntity.getRoles()) {
            authorities.add(new GrantedAuthorityImpl(role.getName()));
        }

        User user = new User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
    
    
}

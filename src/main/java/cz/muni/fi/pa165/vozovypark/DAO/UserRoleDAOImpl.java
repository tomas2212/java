package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.UserRole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrej Bauer
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public void insert(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("you must specify company level");
        }
        entityManager.merge(userRole);
    }

    public void update(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("you must specify company level");
        }
        if (userRole.getName() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
        entityManager.merge(userRole);
    }

    public void remove(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("you must specify company level");
        }
        if (userRole.getName() == null) {
            throw new IllegalArgumentException("cant remove not persit entity");
        }
        entityManager.remove(entityManager.merge(userRole));
    }

    public UserRole getUserRoleByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("you must specify id of userRole");
        }
        return entityManager.find(UserRole.class, name);
    }

    public List<UserRole> getAllUserRoles() {
        TypedQuery<UserRole> q = entityManager.createQuery("SELECT c FROM UserRole c", UserRole.class);
        return q.getResultList();
    }

}

package ir.mimdolt.store.persist.entity.role;

import ir.mimdolt.store.persist.AbstractDAO;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */
public interface RoleDao extends AbstractDAO<Role, Long> {

    Role find(String name) throws Exception;
}

package ir.mimdolt.store.persist.entity.user.address;

import ir.mimdolt.store.persist.AbstractDAO;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */
public interface AddressDao extends AbstractDAO<Address,Long> {

    List<Address> list() throws Exception;

    Address getDefaultAddress() throws Exception;
}

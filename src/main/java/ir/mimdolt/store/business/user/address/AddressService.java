package ir.mimdolt.store.business.user.address;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.model.Response;
import ir.mimdolt.store.persist.entity.user.address.Address;
import ir.mimdolt.store.web.dto.user.address.AddressDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */
@Component
public interface AddressService extends AbstractService<AddressDto,Long,Address>{

    void saveOrUpdated(AddressDto addressDto) throws Exception;

    List<Address> list() throws Exception;

    Response remove(Long id) throws Exception;

    void setDefaultAddress(Long id) throws Exception;

    AddressDto getDefaultAddress() throws Exception;
}

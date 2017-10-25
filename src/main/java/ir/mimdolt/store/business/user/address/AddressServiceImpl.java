package ir.mimdolt.store.business.user.address;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.model.Response;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.persist.entity.user.address.Address;
import ir.mimdolt.store.persist.entity.user.address.AddressDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.user.address.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */

@Transactional
@Service
public class AddressServiceImpl extends AbstractServiceImpl<AddressDto, Long, Address> implements AddressService {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressDao addressDao;

    @Override
    public void saveOrUpdated(AddressDto addressDto) throws Exception {
        User user = userService.getLoggedInUser();
        Address address = new Address();

        if (addressDto.getId() != null)
            address = addressDao.find(addressDto.getId());

        address.setUser(user);
        address.map(addressDto);
        if (addressDto.getId() == null)
            addressDao.add(address);
        else
            addressDao.update(address);
    }

    @Override
    public List<Address> list() throws Exception {
        return addressDao.list();
    }

    @Override
    public Response remove(Long id) throws Exception {
        Address address = addressDao.find(id);
        if (!address.getUser().equals(userService.getLoggedInUser())) {
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        }
        addressDao.remove(address);
        return new Response(200, "ok", null);
    }

    @Override
    public void setDefaultAddress(Long id) throws Exception {
        Address address = addressDao.find(id);
        if (address == null) {
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        }
        addressDao.list().stream().filter(address1 -> !address1.equals(address)).forEach(address1 -> {
            address1.setDefaultAddress(Boolean.FALSE);
        });
        address.setDefaultAddress(Boolean.TRUE);
    }

    @Override
    public AddressDto getDefaultAddress() throws Exception {
        Address address = addressDao.getDefaultAddress();
        return new AddressDto().map(address);
    }
}

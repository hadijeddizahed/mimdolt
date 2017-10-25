package ir.mimdolt.store.web.dto.user.address;

import ir.mimdolt.store.persist.entity.user.address.Address;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */
public class AddressDto {

    private Long id;

    private String fullName;

    private String mobile;

    private String phone;

    private Integer province;

    private Integer city;

    private String postalAddress;

    private String postalCode;

    public AddressDto() {
    }

    public AddressDto(Long id, String fullName, String mobile, String phone, Integer province, Integer city, String postalAddress, String postalCode) {
        this.id = id;
        this.fullName = fullName;
        this.mobile = mobile;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.postalAddress = postalAddress;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public AddressDto map(Address address) {
        if (address == null)
            return new AddressDto();
        this.setId(address.getId());
        this.setFullName(address.getFullName());
        this.setMobile(address.getMobile());
        this.setPhone(address.getPhone());
        this.setCity(address.getCity());
        this.setProvince(address.getProvince());
        this.setPostalAddress(address.getPostalAddress());
        this.setPostalCode(address.getPostalCode());
        return this;
    }
}

package ir.mimdolt.store.persist.entity.user.address;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.web.dto.user.address.AddressDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */

@Entity
@Table(name = "address")
public class Address extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PROVINCE")
    private Integer province;

    @Column(name = "CITY")
    private Integer city;

    @Column(name = "POSTAL_ADDRESS")
    private String postalAddress;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "DEFAULT_ADDRESS")
    private boolean defaultAddress;

    @ManyToOne(targetEntity = User.class)
    @JsonManagedReference
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Address() {
    }

    public Address(String fullName, String mobile, String phone, Integer province, Integer city, String postalAddress, String postalCode) {
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

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Address map(AddressDto addressDto) {
        if (addressDto == null)
            return new Address();
        this.setId(addressDto.getId());
        this.setFullName(addressDto.getFullName());
        this.setMobile(addressDto.getMobile());
        this.setPhone(addressDto.getPhone());
        this.setCity(addressDto.getCity());
        this.setProvince(addressDto.getProvince());
        this.setPostalAddress(addressDto.getPostalAddress());
        this.setPostalCode(addressDto.getPostalCode());
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getFullName() != null ? !getFullName().equals(address.getFullName()) : address.getFullName() != null)
            return false;
        if (getMobile() != null ? !getMobile().equals(address.getMobile()) : address.getMobile() != null) return false;
        if (getPhone() != null ? !getPhone().equals(address.getPhone()) : address.getPhone() != null) return false;
        if (getProvince() != null ? !getProvince().equals(address.getProvince()) : address.getProvince() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        return !(getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null);

    }

    @Override
    public int hashCode() {
        int result = getFullName() != null ? getFullName().hashCode() : 0;
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getProvince() != null ? getProvince().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
        return result;
    }
}

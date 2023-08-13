package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Column(name = "city", insertable = false, updatable = false)
    private String city;
    @Column(name = "city", insertable = false, updatable = false)
    private String street;
    @Column(name = "city", insertable = false, updatable = false)
    private String zipcode;
   // private Member member; // Embedded type이 Entity 가질 수 있음!

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Orders order;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIPCODE")
    private String zipCode;

    @Column(name = "STATUS")
    private DeliveryStatus status;
}

package jpa.jpa.shop.jpql;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS_J")
public class Order_j {

    @Id @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address_j address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product_j product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
}

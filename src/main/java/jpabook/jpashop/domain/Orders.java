package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // name 속성 : 말 그래도 Order 엔티티에 존재하는 member 라는 필드를 어떤
    private Member member;          // 이름으로 Order 테이블에 컬럼명으로 설정할 것인지를 나타내주는 것

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

}

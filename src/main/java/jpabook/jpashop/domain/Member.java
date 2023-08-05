package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member") // Order 테이블과 양방향 연결 for member가 무슨 order 했는지 알기 위해
    private List<Orders> orders = new ArrayList<>();

    @Column(name = "USERNAME") // @Column(name = "") Mapping할 Column의 이름을 지정.
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIPCODE")
    private String zipCode;
}

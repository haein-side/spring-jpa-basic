package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME") // @Column(name = "") Mapping할 Column의 이름을 지정.
    private String name;

    // 기간 Period
    @Embedded
    private Period workPeriod;

    @Embedded
    // 주소 Address
    private Address homeAddress;

}

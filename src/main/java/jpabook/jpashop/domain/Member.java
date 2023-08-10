package jpabook.jpashop.domain;

import javax.persistence.*;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
            column=@Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street",
                    column=@Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
                    column=@Column(name = "WORK_ZIPCODE"))
    })
    // 주소 Address
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}

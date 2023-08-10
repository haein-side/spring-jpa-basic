package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade =  CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    // 양방향 연관관계 맺어주는 메소드 (하나의 메소드에 양측에 관계 설정)
    // 객체 양방향 연관관계는 양쪽 모두 관계 맺어주어야
    // 순수한 객체 상태에서도 정상 동작 - 다대 "일"쪽에서도
    // 자식이 부모 추가했을 때 부모 입장에서 자식 추가해주는 것
    public void addChild(Child child) {
        childList.add(child); // 부모에 자식 추가
        child.setParent(this); // 자식에 부모 추가
    }

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
}

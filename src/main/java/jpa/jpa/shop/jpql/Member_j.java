package jpa.jpa.shop.jpql;

import javax.persistence.*;

@Entity
public class Member_j {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // 외래키 매핑 : Team_j의 pk를 TEAM_ID라는 이름으로 컬럼 가짐
    private Team_j team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

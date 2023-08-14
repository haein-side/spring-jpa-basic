package jpa.jpa.shop.jpql;

import javax.persistence.*;

@Entity
public class Member_j {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    // 다대일 항상 주의해서 Fetch를 LAZY로 잡아둬야 함!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID") // 외래키 매핑 : Team_j의 pk를 TEAM_ID라는 이름으로 컬럼 가짐
    private Team_j team;

//    @Enumerated(EnumType.STRING)
//    private MemberType_j type;

    public void changeTeam(Team_j team) {
        this.team = team;
        team.getMembers().add(this);
    }

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

//    public MemberType_j getType() {
//        return type;
//    }
//
//    public void setType(MemberType_j type) {
//        this.type = type;
//    }

    // Team 양방향 지울 것!
    @Override
    public String toString() {
        return "Member_j{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

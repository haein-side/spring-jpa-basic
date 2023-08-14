package jpabook.jpashop.domain;

import jpa.jpa.shop.jpql.Address_j;
import jpa.jpa.shop.jpql.MemberDTO;
import jpa.jpa.shop.jpql.Member_j;
import jpa.jpa.shop.jpql.Team_j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team_j team = new Team_j();
            team.setName("teamA");
            em.persist(team);

            Member_j member = new Member_j();
            member.setUsername("member1");
            member.setAge(10);

            // 양방향 관계를 만들어주기 위한 연관관계 편의 메소드 생성
            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // 페이징
            String query = "select m from Member_j m left join m.team t on t.name = 'teamA'";

            List<Member_j> result = em.createQuery(query, Member_j.class)
                    .getResultList();

            System.out.println("result.size = " + result.size());

            for (Member_j member1 : result) {
                System.out.println("member1 = " + member1);
            }

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}

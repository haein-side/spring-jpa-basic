package jpabook.jpashop.domain;

import jpa.jpa.shop.jpql.*;

import javax.persistence.*;
import java.util.List;

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
            member.setUsername("teamA");
            member.setAge(10);
//            member.setType(MemberType_j.ADMIN);

            // 양방향 관계를 만들어주기 위한 연관관계 편의 메소드 생성
            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end " +
                    "from Member_j m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
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

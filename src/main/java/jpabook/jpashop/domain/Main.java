package jpabook.jpashop.domain;

import jpa.jpa.shop.jpql.*;

import javax.persistence.*;
import java.util.Collection;
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
            member.setUsername("관리자");
            member.setAge(10);
//            member.setType(MemberType_j.ADMIN);

            // 양방향 관계를 만들어주기 위한 연관관계 편의 메소드 생성
            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // 컬렉션 값 연관 경로
            // 더이상 탐색 못함
            // 컬렉션으로 들어가기 때문에 쓸 수 있는 게 size 정도밖에 없음
            //String query = "select t.members from Team_j t";

            // From 절에서 명시적 조인을 통해 별칭을 얻으면 별칭 통해 탐색 가능
            String query = "select m.username from Team_j t join t.members m";

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : resultList) {
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

package jpabook.jpashop.domain;

import jpa.jpa.shop.jpql.Address_j;
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
            Member_j member = new Member_j();
            member.setUsername("member_hi");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            // 영속성 컨텍스트에서 모두 관리됨
            List<Member_j> result = em.createQuery("select m from Member_j m", Member_j.class)
                    .getResultList();

            Member_j findMember = result.get(0);
            findMember.setAge(20); // 수정이 되면 영속성 컨텍스트에서 관리가 되고 안 되면 관리 안 됨

            // 묵시적 조인
            // Member 테이블과 Team 테이블이 inner join 되어서 쿼리 날라감
            List<Team_j> result2 = em.createQuery("select m.team from Member_j m", Team_j.class)
                    .getResultList();

            // 명시적 조인
            // 위와 같이 작성하면 안 됨
            // 최대한 SQL문과 비슷하게 작성해야 튜닝 등 할 수 있으므로 더 좋음!
            // JOIN 명시적으로 작성할 것
            List<Team_j> result3 = em.createQuery("select t from Member_j m join m.team t", Team_j.class)
                    .getResultList();

            // 임베디드 타입 프로젝션
            // 소속이 엔티티이기 때문에 어디 소속인지 꼭 정해줘야 한다 ex from Order o
            em.createQuery("select o.address from Order_j o", Address_j.class)
                            .getResultList();

            // 스칼라 타입 프로젝션
            // 원하는 거 다 가져오는 것
            em.createQuery("select distinct m.username, m.age from Member_j m")
                            .getResultList();

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

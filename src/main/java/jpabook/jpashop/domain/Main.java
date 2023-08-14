package jpabook.jpashop.domain;

import jpa.jpa.shop.jpql.Member_j;
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

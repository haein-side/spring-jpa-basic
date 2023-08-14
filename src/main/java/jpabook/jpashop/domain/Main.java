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
            for (int i = 0; i < 100; i++) {
                Member_j member = new Member_j();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            // 페이징
            List<Member_j> result = em.createQuery("select m from Member_j m order by m.age desc", Member_j.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
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

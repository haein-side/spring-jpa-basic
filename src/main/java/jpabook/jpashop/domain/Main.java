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
            member.setUsername("member_jpql");
            em.persist(member);

            // 반환타입 설정 가능 -> TypeQuery
            // 기본적으로 엔티티 설정
            TypedQuery<Member_j> query = em.createQuery("select m from Member_j m", Member_j.class);

            List<Member_j> resultList = query.getResultList();

            for (Member_j member_j : resultList) {
                System.out.println("members = " + member_j);
            }

            TypedQuery<Member_j> query1 = em.createQuery("select m from Member_j m where m.username = : username", Member_j.class);
            query1.setParameter("username", "member_jpql");

            List<Member_j> resultList2 = query1.getResultList();

            for (Member_j member_j : resultList2) {
                System.out.println("members_2 = " + member_j);
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

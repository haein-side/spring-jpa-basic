package jpabook.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("hello");
            
            em.persist(member);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member.getId());
            System.out.println("reference = " + reference.getClass());

            Member m1 = em.find(Member.class, member.getId()); // 프록시는 한 번 조회가 되면 프록시 반환함
            System.out.println("m1 = " + m1.getClass());

            System.out.println("a == a : " + (m1 == reference)); // 항상 true를 보장함!

            // reference = class jpabook.jpashop.domain.Member$HibernateProxy$UZXRp4wj
            // m1 = class jpabook.jpashop.domain.Member$HibernateProxy$UZXRp4wj

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

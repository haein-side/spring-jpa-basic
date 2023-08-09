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

            Member m1 = em.find(Member.class, member.getId());
            System.out.println("m1 = " + m1.getClass());

            Member reference = em.getReference(Member.class, member.getId()); // 27라인에서 Member 객체가 영속성 컨텍스트의 1차 캐시에 이미 저장되어 있는 상태
            System.out.println("reference = " + reference.getClass());

            // 둘 다 동일한 Member 나옴!
            // m1 = class jpabook.jpashop.domain.Member
            // reference = class jpabook.jpashop.domain.Member

            System.out.println("a == a : " + (m1 == reference)); // true 보장

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

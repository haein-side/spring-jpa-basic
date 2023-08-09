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

//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId()); // 프록시 객체 조회한 것 (null)
            System.out.println("findMember = " + findMember.getClass()); // class jpabook.jpashop.domain.Member$HibernateProxy$K72bfG9W
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName()); // 실제 Member entity 생성해서 target 변수가 객체 가짐
            System.out.println("findMember.name = " + findMember.getName()); // 프록시 객체는 처음 사용할 때 한 번만 초기화됨 (또 쿼리 날리는 것 아님)
            System.out.println("findMember = " + findMember.getClass()); // 프록시 객체가 실제 엔티티로 바뀌는 것이 아님 - 프록시 객체를 통해서 실제 엔티티에 접근 가능


            Member mem1 = new Member();
            em.persist(mem1);
            Member mem2 = new Member();
            em.persist(mem2);

            em.flush();
            em.clear();

            Member m1 = em.getReference(Member.class, mem1.getId());
            Member m2 = em.find(Member.class, mem2.getId());

            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass())); // false - 프록시 객체로 넘어올지 실제 객체로 넘어올지 모름
            System.out.println("m1 == m2 : " + (m1 instanceof Member)); // true
            System.out.println("m1 == m2 : " + (m2 instanceof Member)); // true

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

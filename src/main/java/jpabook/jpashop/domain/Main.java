package jpabook.jpashop.domain;

import org.hibernate.Hibernate;

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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId()); // member만 가져옴

            System.out.println("m = " + m.getTeam().getClass()); // m = class jpabook.jpashop.domain.Team$HibernateProxy$G4LZvTx8

            System.out.println("============"); // 쿼리 나옴
            m.getTeam(); // 프록시 객체 가져옴
            m.getTeam().getName(); // 메소드 사용
            System.out.println("============"); // 쿼리 나옴

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

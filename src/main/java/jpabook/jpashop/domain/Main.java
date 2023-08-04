package jpabook.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member); // Member.setTeam()하는 시점에 set되도록 연관관계 편의 메소드를 생성
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team); // Member.team 연관관계 맵핑, 연관관계 편의 메소드
            em.persist(member);

            // team.addMember(member); // team 입장에서 연관관계 편의 메소드 생성

            // DB에 데이터 반영하고 영속성 컨텍스트 지움
            em.flush();
            em.clear();

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

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
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); // PK 뽑아서 insert할 때 FK로 활

            em.persist(member);

//            영속성 콘텍스트 지우고 쿼리 생성
//            em.flush();
//            em.clear();
            
            Member findMember = em.find(Member.class, member.getId());

//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 연관관계 수정
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            tx.commit(); // 엔티티가 변경되었는지 JPA가 트랜잭션 커밋하는 시점에 체크하고 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

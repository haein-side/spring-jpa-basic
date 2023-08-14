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

            List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member_j m")
                    .getResultList();

            // Object[] 타입으로 조회
            Object[] result = resultList.get(0);
            System.out.println("result = " + result[0]);
            System.out.println("age = " + result[1]);

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

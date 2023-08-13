package jpabook.jpashop.domain;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            Member member = new Member(); // 값 타입 컬렉션은 field username 처럼 lifecycle이 다 member에 의존
            member.setName("it's me");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "10000"));
            member.getAddressHistory().add(new Address("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============= START ==============");
            // 값 타입 컬렉션들은 지연로딩으로 쿼리 안 나감
            Member findMember = em.find(Member.class, member.getId());

            // "homeCity -> newCity" 수정
            // 값 타입은 immutable해야 함
            // findMember.getHomeAddress().setCity(""); 이렇게 하면 안됨
            // 완전히 새로운 인스턴스로 통으로 갈아끼워야 함!
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
            // String 자체가 값 타입이므로 통째로 갈아끼워야 함!
            // 컬렉션의 값만 변경해도 실제 쿼리가 날라감
            // 마치 영속성 전이가 되는 것처럼..!
            // Member 안에 있는 속성이라고 생각할 것
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // 대상을 먼저 찾아야! equals를 사용하는 경우 많음!
            // equals와 hashcode overriding한 것을 반드시 만들어줘야 함
            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

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

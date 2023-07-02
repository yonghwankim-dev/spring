package com.hello.jpabook_ch10_objected_query._01_objected_query;

import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook_objected_query");
    }

    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(new Member(null, "kim", 20));
        em.flush();
        transaction.commit();
    }

    @AfterEach
    public void afterEach() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.createQuery("delete from Member").executeUpdate();
        transaction.commit();
    }

    @Test
    @DisplayName("회원 엔티티를 대상으로 JPQL 사용")
    public void test() {
        // given
        String jpql = "select m from Member as m where m.username = 'kim'";
        // when
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("criteria 쿼리 사용")
    public void test2() {
        // Criteria 사용 준비
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        // 루트 클래스(조회를 시작할 클래스)
        Root<Member> m = query.from(Member.class);
        // 쿼리 생성
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("QueryDSL 코드 사용")
    public void test3() {
        // 준비
        JPAQuery<Member> query = new JPAQuery(em);
        QMember member = QMember.member;
        // 쿼리, 결과조회
        List<Member> members = query.from(member)
            .where(member.username.eq("kim"))
            .fetch();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(members.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("native sql 사용")
    public void test4() {
        // given
        String sql = "SELECT ID, NAME FROM MEMBER WHERE NAME = 'kim'";
        // when
        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("TypeQuery 사용")
    public void test5() {
        // given
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        // when
        List<Member> resultList = query.getResultList();
        // then
        resultList.forEach(System.out::println);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("Query 사용")
    public void test6() {
        // given
        Query query = em.createQuery("SELECT m FROM Member m");
        // when
        List resultList = query.getResultList();
        // then
        for (Object o : resultList) {
            Member member = (Member) o;
            System.out.println("member : " + member);
        }
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("이름 기준 파라미터 사용(권장)")
    public void test7() {
        // given
        String usernameParam = "kim";
        // when
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class);
        // then
        query.setParameter("username", usernameParam);
        List<Member> resultList = query.getResultList();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("위치 기준 파라미터 사용")
    public void test8() {
        // given
        String usernameParam = "kim";
        // when
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.username = ?1", Member.class);
        // then
        query.setParameter(1, usernameParam);
        List<Member> resultList = query.getResultList();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultList.size()).isEqualTo(1L);
            softAssertions.assertAll();
        });
    }

    @Test
    @DisplayName("여러 프로젝션")
    public void test9() {
        // given
        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        // when
        List resultList = query.getResultList();
        // then
        ListIterator iterator = resultList.listIterator();
        while (iterator.hasNext()) {
            Object[] row = (Object[]) iterator.next();
            String username = (String) row[0];
            Integer age = (Integer) row[1];
            System.out.println("username : " + username + ", age : " + age);
        }
    }

    @Test
    @DisplayName("여러 프로젝션 Object[]로 조회")
    public void test10() {
        // given
        String sql = "SELECT m.username, m.age FROM Member m";
        // when
        List<Object[]> resultList = em.createQuery(sql).getResultList();
        // then
        for (Object[] row : resultList) {
            System.out.println("username : " + row[0] + ", age : " + row[1]);
        }
    }
}

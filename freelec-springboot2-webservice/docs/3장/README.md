# 스프링 부트에서 JPA로 데이터베이스 다뤄보자

## 목차

- JPA 소개
- 프로젝트에 Spring Data Jpa 적용하기

## JPA 소개

### Spring Data JPA

JPA는 인터페이스로서 자바의 표준 명세서입니다.

JPA 인터페이스를 구현한 클래스가 **Hibernate**, Eclipse Link 등이 있습니다.

하지만 Spring에서 JPA를 사용할 때는 Hibernate와 같은 구현체를 직접 다루지 않습니다.

구현체들을 좀 더 쉽게 사용하고자 추상화 시킨 Spring Data JPA라는 모듈을 이용하여 JPA 기술을 다룹니다.

이러한 관계는 다음과 같습니다.

```
JPA <- Hibernate <- Spring Data JPA
```

### Spring Data JPA를 사용하는 이유

- 구현체 교체의 용이성
    - Hibernate 외에 다른 구현체로 쉽게 교체하기 위해서입니다.
- 저장소 교체의 용이성
    - 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체하기 위해서입니다.

## 프로젝트에 Spring Data Jpa 적용하기

### 실습에서 사용한 애노테이션 설명

- @Entity
    - 테이블과 링크될 클래스임을 나타냅니다.
    - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네미이(_)으로 테이블 이름을 매칭합니다.
    - ex) SalesManager.java -> sales_manager table
- @Id
    - 해당 테이블의 PK 필드를 나타냅니다.
- @GeneratedValue
    - PK 생성 규칙을 나타냅니다.
    - Spring Boot 2.0에서는 GenerateType.IDENTITY 옵션을 추가해야만 auto_increment가 됩니다.
- @Column
    - 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.
    - @Column을 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
    - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나
      등의 경우에 사용됩니다.
- @NoArgsConstructor
    - 기본 생성자 자동 추가
    - public Posts(){}와 같은 효과입니다.
- @Getter
    - 클래스 내 모든 필드에 getter 메소드를 자동 생성합니다.
- @Builder
    - 해당 클래스의 빌더 패턴 클래스를 생성합니다.
    - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함됩니다.

### Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다.

Setter 메소드를 사용하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 파악할 수 없습니다.

대신 **해당 필드의 값 변경이 필요하면 그 목적과 의도를 나타낼 수 있는 메소드를 추가**해야만 합니다.

#### 잘못 사용 예

```java
public class Order {

    public void setStatus(boolean status) {
        this.status = status;
    }
}

    public void 주문서비스의_취소이벤트() {
        order.setStatus(false);
    }
```

#### 올바른 사용 예

```java
public class Order {

    public void cancelOrder() {
        this.status = false;
    }
}

    public void 주문서비스의_취소이벤트() {
        order.cancelOrder();
    }
```

### JpaRepository 인터페이스를 상속하는 저장소 인터페이스

```java
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // ...
}
```

- @Repository를 추가할 필요가 없습니다.
- Entity 클래스와 기본 Entity Repository는 한 디렉토리에 있어야합니다. (권장)
    - Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없기 때문입니다.
    - 나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면 Entity 클래스와 Repository는 함께 움직여야 하므로
      **도메인 패키지에서 관리**해야 합니다.

## 등록/수정/조회 API 만들기

API를 만들기 위한 3개의 클래스

- Request 받기 위한 Dto
- API 요청을 받을 Controller
- 트랜잭션 도메인 기능 간의 순서를 보장하는 Service

비즈니스 로직은 도메인이 처리하는 것이 적절합니다.

### Spring 웹 계층

- Web Layer
    - 컨트롤러와 JSP/Freemarker 등의 뷰 템플릿 영역
    - 필터, 인터셉터, 컨트롤러 어드바이스 등 외부 요청과 응답에 대한 전반적인 영역
- Service Layer
    - @Service에 사용되는 서비스 영역
    - 컨트롤러와 Dao의 중간 영역에서 사용됩니다.
    - @Transaction이 사용되어야 하는 영역입니다.
- Repository Layer
    - 데이터베이스와 같이 저장소에 접근하는 영역
    - Dao 영역과 동일함
- Dtos
    - Dto는 계층 간 데이터 교환을 위한 객체
    - Dtos는 이들의 영역을 이야기합니다.
    - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기합니다.
- Domain Model
    - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화 시킨 모델입니다.
    - 택시 앱이라면 배차, 탑승, 요금 등이 모두 도메인입니다.
    - @Entity를 사용하는 영역이 도메인 모델이라고 할 수 있습니다.
    - I/O처럼 값 객체들도 이 영역에 해당할 수 있습니다.

Entity는 데이터베이스와 맞닿은 핵심 클래스입니다.

Entity 클래스를 Request/Response 클래스로 사용해서는 안됩니다.

뷰의 사소한 변경으로 인하여 Entity 클래스를 변경하는 것은 많은 영향을 미칩니다.

### 영속성 컨텍스트
엔티티를 영구히 저장하는 환경입니다. JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈립니다.

JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가

유지된 상태입니다.

이 상타에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 싲머에 해당 테이블에 변경분을 반영합니다.



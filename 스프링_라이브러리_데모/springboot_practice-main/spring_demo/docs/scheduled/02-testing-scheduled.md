## How to Test the @Scheduled Annotation

### 1. 소개(Introduction)

One of the available annotations in the Spring Framework is @Scheduled. We can use this annotation to execute tasks in a
scheduled way.

> 스프링 프레임워크에서 사용가능한 애노테이션 중 하나인 @Scheduled가 있습니다.
> 우리는 스케줄링된 방법으로 테스크를 시랭하기 위해서 애노테이션을 사용할 수 있습니다.

In this tutorial, we'll explore how to test the @Scheduled annotation.

> 이번 튜토리얼에서는 우리는 @Scheduled 애노테이션을 테스트하는 방법에 대해서 알아볼 것입니다.

### 2. 의존성(Dependencies)

```xml

<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <relativePath/>
</parent>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>
<scope>test</scope>
</dependency>

<dependency>
<groupId>org.junit.jupiter</groupId>
<artifactId>junit-jupiter-api</artifactId>
</dependency>

<dependency>
<groupId>org.awaitility</groupId>
<artifactId>awaitility</artifactId>
<version>3.1.6</version>
<scope>test</scope>
</dependency>
```

### 3. 간단한 @Scheduled 샘플(Simple @Scheduled Sample)

Let's start by creating a simple Counter class:

> 간단한 Counter 클래스를 생성해봅시다.

```java

@Component
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 5)
    public void scheduled() {
        this.count.incrementAndGet();
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}
```

We'll use the scheduled method to increase our count. Note that we've also added the @Scheduled annotation to execute it
in a fixed period of five milliseconds.

> 우리는 count를 증가시키는 스케줄링된 메소드를 사용할 것입니다.
> 우리는 또한 5밀리초 고정된 간격으로 실행하는 @Scheduled 애노테이션 또한 추가하였습니다.

Also, let's create a ScheduledConfig class to enable scheduled tasks using the @EnableScheduling annotation:

> @EnableScheduling 애노테이션을 사용하여 스케줄링된 테스크를 사용하기 위하여 ScheduledConfig 클래스를 생성합니다.

```java

@Configuration
@EnableScheduling
public class ScheduledConfig {
}
```

### 4. 통합 테스팅 사용하기(Using Integration Testing)

One of the alternatives to test our class is using integration testing. To do that, we need to use the
@SpringJUnitConfig annotation to start the application context and our beans in the testing environment:

> 우리의 클래스를 테스트하기 위한 방법중 하나는 통합 테스팅을 사용하기입니다.
> 그러기 위해서는 우리는 테스팅 환경안에 빈과 애플리케이션 컨텍스트를 시작하기 위한 @SpringJUnitConfig 애노테이션이 필요합니다.

```java

@SpringJUnitConfig(ScheduledConfig.class)
public class ScheduledIntegrationTest {

    @Autowired
    Counter counter;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero()
            throws InterruptedException {
        Thread.sleep(100L);

        assertThat(counter.getInvocationCount()).isGreaterThan(0);
    }
}
```

In this case, we start our Counter bean and wait for 100 milliseconds to check the invocation count.

> 이 테스트의 경우 우리는 우리의 Counter 빈을 시작하고 invocation count 값을 체크하기 위해서 100 밀리초동안 대기하였습니다.

### 5. Using Awaitility

Another approach to testing scheduled tasks is using Awaitility. We can use the Awaitility DSL to make our test more
declarative:

> 스케줄링된 테스크를 테스팅하기 위한 또다른 방법은 Awaitility를 사용하는 것입니다.
> Awaitility DSL를 사용하여 테스트를 보다 확실하게 수행할 수 있습니다.

```java

@SpringJUnitConfig(ScheduledConfig.class)
public class ScheduledAwaitilityIntegrationTest {

    @SpyBean
    private Counter counter;

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> verify(counter, atLeast(10)).scheduled());
    }
}

```

In this case, we inject our bean with the @SpyBean annotation to check the number of times that the scheduled method is
called in the period of one second.

> 이 경우에는 우리는 우리의 빈에게 1초 간격의 스케줄링된 메소드의 시간을 체크하기 위해서 @SpyBean 애노테이션을 주입하였습니다.

### 6. 결론(Conclusion)

In this tutorial, we showed some approaches to test scheduled tasks using integration testing and the Awaitility
library.

> 이번 튜토리얼에서는 우리는 통합 테스팅과 Awaitility 라이브러리를 사용하여 스케줄링된 테스크를 테스트하는 몇가지 방법을 보여주었습니다.

We need to take into account that, although integration tests are good, it's generally better to focus on the unit
testing of the logic inside the scheduled method.

> 통합 테스트는 좋지만 일반적으로 스케줄링 메소드 내에서 로직의 단위 테스트에 집중하는 것이 더 좋습니다.

### References

- [How to Test the @Scheduled Annotation](https://www.baeldung.com/spring-testing-scheduled-annotation)

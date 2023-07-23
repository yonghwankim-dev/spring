# A Guide To Logback

## 1. Overview

Logback is one of the most widely used logging frameworks in the Java Community. It's a replacement for its
predecessor, Log4j. Logback offers a faster implementation, provides more options for configuration, and more
flexibility in archiving old log files.

> Logback은 자바 커뮤니티에서 가장 넓게 사용하는 로깅 프레임워크중 하나입니다. Logback은 이전 버전인 Log4j를 대체한 것입니다. Logback은 빠른 구현, 설정에 대한 더 많은 옵션과 예전 로그
> 파일들을 저장하는 것에 많은 융통성을 제공합니다.

In this tutorial, we'll introduce Logback's architecture and examine how we can use it to make our applications better.

> 이번 튜토리얼에서는 우리는 Logback의 아키텍처와 우리의 애플리케이션 더 낫게 만들기 위해서 어떻게 사용하지를 설명합니다.

## 2. Logback Architecture

The Logback architecture is comprised of three classes: Logger, Appender, and Layout.

> Logback 아키텍처는 3개의 클래스들로 구성되어있습니다. : Logger, Appender, Layout

A Logger is a context for log messages. This is the class that applications interact with to create log messages.

> Logger는 로그 메시지들에 대한 컨텍스트입니다. Logger는 애플리케이션들이 상호 작용하여 로그 메시지를 만드는 클래스입니다.

Appenders place log messages in their final destinations. A Logger can have more than one Appender. We generally
think of Appenders as being attached to text files, but Logback is much more potent than that.

> Appender들은 그들의 최종 목적지에 로그 메시지들을 작성합니다. Logger는 하나 이상의 Appender를 가질 수 있습니다. 우리는 일반적으로 Appenders를 텍스트 파일에 첨부되어 있다고
> 생각합니다. 하지만 Logback은 그보다 훨씬 강력합니다.

Layout prepares messages for outputting. Logback supports the creation of custom classes for formatting messages, as
well as robust configuration options for the existing ones.

> Layout은 출력을 위해 메시지들을 준비합니다. Logback은 형식화된 메시지들 위해 커스텀 클래스들의 생성을 지원합니다. 뿐만 아니라 기존 클래스에 대한 강력한 설정 옵션을 지원합니다.

## 3. Setup

### 3.1. Maven Dependency

Logback uses the Simple Logging Facade for Java (SLF4J) as its native interface. Before we can start logging
messages, we need to add Logback and SLF4J to our pom.xml:

> Loback은 인터페이스로서 SLF4J를 사용합니다. 우리가 로깅 메시지를 시작할 수 있기 전에 우리는 Logback과 SLF4J 의존성을 추가해야 합니다.

```xml

<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.3.5</version>
</dependency>

<dependency>
<groupId>org.slf4j</groupId>
<artifactId>slf4j-api</artifactId>
<version>2.0.4</version>
<scope>test</scope>
</dependency>
```

### 3.2. Classpath

Logback also requires logback-classic.jar on the classpath for runtime.

> Logback은 또한 런타임 시점에 클래스패스에 logback-classic.jar 파일을 요구합니다.

We'll add this to pom.xml as a test dependency:

> 우리는 테스트 의존성으로서 이것을 추가할 것입니다.

```xml

<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.3.5</version>
</dependency>
```

## 4. Basic Example and Configuration

Let's start with a quick example of using Logback in an application.

> 애플리케이션에서 Logback을 사용한 예제를 빠르게 시작해봅시다.

First, we need a configuration file. We'll create a text file named logback.xml and put it somewhere in our
classpath:

> 첫번째로 우리는 설정 파일이 필요합니다. 우리는 logback.xml라는 이름의 텍스트 파일을 생성하고 classpath안에 저장합니다.

```xml

<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

Next, we need a simple class with a main method:

> 다음으로 우리는 main 메소드를 가진 간단한 클래스가 필요합니다.

```java
public class Example {

	private static final Logger logger
		= LoggerFactory.getLogger(Example.class);

	public static void main(String[] args) {
		logger.info("Example log from {}", Example.class.getSimpleName());
	}

}

```

This class creates a Logger and calls info() to generate a log message.
When we run Example, we see our message logged to the console:

> 이 클래스는 Logger를 생성하고 로그 메시지를 생성하기 위해서 info() 메소드를 호출하였습니다. 우리가 Example 클래스를 실행하면 우리는 콘솔에 로깅된 메시지를 볼 것입니다.

```
20:34:22.136 [main] INFO Example - Example log from Example
```

This configuration and code give us a few hints as to how this works:

> 이 설정과 코드는 우리에게 어떻게 동작하는지가지 힌트를 줍니다.

1. We have an appender named STDOUT that references class name ConsoleAppender.
2. There is a pattern that describes the format of our log message.
3. Our code creates a Logger and we passed our message to it via an info() method.

> 1. 우리는 ConsoleAppender 클래스 이름을 참조하는 STDOUT이라는 이름의 appender를 가집니다.
> 2. STDOUT appender에는 로그 메시지의 형식을 설명하는 패턴이 있습니다.
> 3. 우리의 코드는 로거를 생성하고 info() 메소드를 통하여 우리의 메시지를 로거에게 전달합니다.

## 5. Logger Contexts

### 5.1. Creating a Context

To log a message to Logback, we initialize a Logger from SLF4J or Logback:

> Logback에 메시지를 로깅하기 위해서는 우리는 SLF4J 또는 Logback으로부터 로거를 초기화해야 합니다.

```shell
private static final Logger logger = LoggerFactory.getLogger(Example.class);
```

Then we use it:

> 그런다음 우리는 이렇게 사용합니다.

```shell
logger.info("Example log from {}", Example.class.getSimpleName());
```

This is our logging context. When we created it, we passed LoggerFactory our class. This gives the Logger a name (there
is also an overload that accepts a String).

> 이것이 우리의 로깅 컨텍스트입니다. 우리가 로거를생성했을때 , 우리는 LoggerFactory에게 Example 클래스를 전달했습니다. LoggerFactory는 Logger에게 이름을 부여합니다.

Logging contexts exist in a hierarchy that closely resembles the Java object hierarchy:

> 로깅 컨텍스트는 자바 객체 계층구조와 매우 유사한 계층구조로 존재합니다.

1. A logger is an ancestor when its name, followed by a dot, prefixes a descendant logger‘s name
2. A logger is a parent when there are no ancestors between it and a child

> 1. 후손 로거의 이름 점 접두사 앞에 이름이 위치할때 로거는 조상입니다.
> 2. 로거는 자손 사이에 조상이 없을때 로거는 부모입니다.

For example, the Example class below is in the com.baeldung.logback package. There's another class named ExampleAppender
in the com.baeldung.logback.appenders package.

> 예를 들어, com.baeldung.logback.package에 Example 클래스가 있습니다. 거기에는 com.baeldung.logback.appenders 패키지에 또다른 클래스 이름인
> ExampleAppender가 있습니다.

ExampleAppender's Logger is a child of Example's Logger.

> ExampleAppender의 Logger는 Example Logger의 자손입니다.

All loggers are descendants of the predefined root logger.

> 모든 로거들은 앞서 정의된 루트 로거의 자손들입니다.

A Logger has a Level, which can be set either via configuration or with Logger.setLevel(). Setting the level in code
overrides configuration files.

> Logger는 Logger.setLevel()이나 설정을 통하여 설정할 수 있는 레벨을 가지고 있습니다.
> 코드 안에서 레벨 설정을 하는 것은 설정 파일들을 재정하는 것입니다.

The possible levels are, in order of precedence: TRACE, DEBUG, INFO, WARN and ERROR. Each level has a corresponding
method that we use to log a message at that level.

> 가능한 레벨들은 우선순위에 따라 TRACE, DEBUG, INFO, WARN, ERROR가 있습니다.
> 각각의 레벨은 해당 헤레벨에서 메시지를 로깅하기 위해서 사용되는 메소드들이 있습니다.

If a Logger isn't explicitly assigned a level, it inherits the level of its closest ancestor. The root logger defaults
to DEBUG. We'll see how to override this below.

> 만약 Logger가 명시적으로 레벨이 설정되어 있찌 않으면 Logger는 가까운 조상으로부터 레벨을 상속받습니다. 루트 Logger는 기본적으로 DEBUG 레벨입니다. 우리는 어떻게 재정의할 것인지 볼것입니다.

### 5.2. Using a Context

Let's create an example program that demonstrates using a context within logging hierarchies:

> 로깅 계층 내에서 컨텍스를 사용하는 예제 프로그램을 만들어보겠습니다.

```
ch.qos.logback.classic.Logger parentLogger=
	(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback");

parentLogger.setLevel(Level.INFO);

Logger childlogger=
(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback.tests");

parentLogger.warn("This message is logged because WARN > INFO.");
parentLogger.debug("This message is not logged because DEBUG < INFO.");
childlogger.info("INFO == INFO");
childlogger.debug("DEBUG < INFO");
```

When we run this, we see these messages:

> 우리는 실행했을 때 다음 메시지를 볼것입니다.

```shell
20:31:29.586 [main] WARN com.baeldung.logback - This message is logged because WARN > INFO.
20:31:29.594 [main] INFO com.baeldung.logback.tests - INFO == INFO
```

We start by retrieving a Logger named com.baeldung.logback and cast it to a ch.qos.logback.classic.Logger.

> 우리는 com.baeldung.logback이라는 이름의 Logger를 검색함으로써 시작하고 ch.gos.logback.classic.Logger에 캐스팅합니다.

A Logback context is needed to set the level in the next statement; note that the SLF4J's abstract logger does not
implement setLevel().

> Logback 컨텍스트는 다음 구문에서 레벨을 설정해야 합니다. SLF4J의 추상 logger는 setLevel()을 구현하지 않았기 때문입니다.

We set the level of our context to INFO. Then we create another logger named com.baeldung.logback.tests.

> 우리는 INFO 레벨로 설정하였습니다. 그런다음 com.baeldung.loback.tests라는 이름의 또다른 로거를 생성하였습니다.

Finally, we log two messages with each context to demonstrate the hierarchy. Logback logs the WARN and INFO messages,
and filters the DEBUG messages.

> 마지막으로 우리는 계층을 시연하기 위해서 각각의 로거 컨텍스트에 두개의 메시지들을 남겼습니다.
> Logback은 WARN과 INFO 레벨 메시지를 로그를 남깁니다. 그리고 DEBUG 메시지들은 필터링합니다.

Now let's use the root logger:

> 지금 root logger를 사용해봅시다.

```
ch.qos.logback.classic.Logger logger =
(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback");
logger.debug("Hi there!");

Logger rootLogger =
(ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
logger.debug("This message is logged because DEBUG == DEBUG.");

rootLogger.setLevel(Level.ERROR);

logger.warn("This message is not logged because WARN < ERROR.");
logger.error("This is logged.");
```

We see these messages when we execute this snippet:

> 이 코드를 실행했을때 다음과 같은 결과를 볼것입니다.

```shell
20:44:44.241 [main] DEBUG com.baeldung.logback - Hi there!
20:44:44.243 [main] DEBUG com.baeldung.logback - This message is logged because DEBUG == DEBUG.
20:44:44.243 [main] ERROR com.baeldung.logback - This is logged.
```

To summarize, we started with a Logger context and printed a DEBUG message.

> 정리하면 Logger 컨텍스트를 시작하였고 DEBUG 메시지가 출력되었습니다.

Then we retrieved the root logger using its statically defined name, and set its level to ERROR.

> 그런다음 정적으로 정의된 이름을 사용하여 root logger를 검색하고 레벨을 ERROR로 설정합니다.

Finally, we demonstrated that Logback does actually filter any statement less than an error.

> 마지막으로 우리는 Logback이 실제로 error 레벨보다 작은 레벨(WARN, INFO, DEBUG, TRACE)을 필터링했다는 것을 시연했습니다.

### 5.3. Parameterized Messages

Unlike the messages in the sample snippets above, most useful log messages require appending Strings. This entails
allocating memory, serializing objects, concatenating Strings, and potentially cleaning up the garbage later.

> 위의 간단한 예제에서 메시지와는 다르게 대부분의 유요한 로그 메시지들은 문자열을 추가하는 것을 요구합니다. 이 로그 메시지는 메모리를 할당하고 객체를 직렬화화하고 문자열을 연결하고 나중에 가비지를 정리할 수
> 있습니다.

Consider the following message:

> 다음 메시지를 생각해보세요.

```
log.debug("Current count is " + count);
```

We incur the cost of building the message whether the Logger logs the message or not.

> 위 코드에서 Logger가 메시지를 로깅했든 하지 않든 메시지를 만드는 비용은 발생합니다.

Logback offers an alternative with its parameterized messages:

> Logback은 파라미터화된 메시지를 대체제로써 다음과 같이 제안합니다.

```
log.debug("Current count is {}", count);
```

The braces {} will accept any Object and uses its toString() method to build a message only after verifying that the log
message is required.

> 중괄호 {}에는 객체가 들어가고 그 객체의 toString() 메소드의 반환 결과가 들어갈 것입니다.

Let's try some different parameters:

> 몇몇 다른 파라미터들을 시도해봅시다.

```
String message = "This is a String";
Integer zero = 0;

try {
logger.debug("Logging message: {}", message);
logger.debug("Going to divide {} by {}", 42, zero);
int result = 42 / zero;
} catch (Exception e) {
logger.error("Error dividing {} by {} ", 42, zero, e);
}
```

This snippet yields:

이것은 스니펫은 다음과 같은 결과를 출력합니다.

```
21:32:10.311 [main] DEBUG com.baeldung.logback.LogbackTests - Logging message: This is a String
21:32:10.316 [main] DEBUG com.baeldung.logback.LogbackTests - Going to divide 42 by 0
21:32:10.316 [main] ERROR com.baeldung.logback.LogbackTests - Error dividing 42 by 0
java.lang.ArithmeticException: / by zero
at com.baeldung.logback.LogbackTests.givenParameters_ValuesLogged(LogbackTests.java:64)
```

We see how a String, an int, and an Integer can be passed in as parameters.

> 위 예제를 통하여 우리는 어떻게 문자열, 정수, Integer가 파라미터에 전달되는지 알아보았습니다.

Also, when an Exception is passed as the last argument to a logging method, Logback will print the stack trace for us.

> 또한 예외가 로깅 메소드에 마지막 매개변수로 전달됬을 때 Logback은 stack trace를 우리에게 출력하였습니다.

## 6. Detailed Configuration

In the previous examples, we were using the 11-line configuration file we created in section 4 to print log messages
to the console. This is Logback's default behavior; if it can't find a configuration file, it creates a
ConsoleAppender and associates it with the root logger.

> 이전 예제에서 우리는 콘솔에 로그 메시지들을 출력하기 위해서 4장에서 우리가 생성한 11줄의 설정 파일을 사용하였습니다. 이 설정 파일은 Logback의 기본 동작입니다. 만약 Logback이 설정 파일을 찾지
> 못한다면 Logback은 root logger를 가지고 ConsoleAppender를 생성합니다.

### 6.1. Locating Configuration Information

A configuration file can be placed in the classpath and named either logback.xml or logback-test.xml.

> 설정 파일은 classpath에 저장될 수 있습니다. 그리고 logback.xml 또는 logback-test.xml와 같이 저장합니다.

Here's how Logback will attempt to find configuration data:

> Logback이 어떻게 설정 데이터를 찾는지 알아봅시다.

1. Search for files named logback-test.xml, logback.groovy, or logback.xml in the classpath, in that order
2. If the library doesn't find those files, it will attempt to use Java's ServiceLoader to locate an implementor of the
   com.qos.logback.classic.spi.Configurator.
3. Configure itself to log output directly to the console

> 1. logback-test.xml, logback.groovy, logback.xml 파일을 classpath에서 순차적으로 탐색합니다.
> 2. 만약 라이브러리가 logback 파일들을 찾을 수 없다면 com.qos.logback.classic.spiConfigurator의 구현체를 위치시키기 위해서 자바의 ServiceLoader를 사용하도록
     시도합니다.
> 3. 콘솔에 직접 출력을 로그하기 위해서 자체 구성합니다.

Important Note: Due to the official documentation of Logback, they have stopped supporting logback.groovy. So if you
want to configure Logback in your application, it's better to use the XML version.

> Important Node: Logback의 공식 문서 때문에 logback.groovy 지원을 중단하였습니다. 그래서 만약 애플리케이션에 Logback 설정을 원한다면 XML 버전을 사용하는 것보다 더
> 낫습니다.

### 6.2. Basic Configuration

Let's take a closer look at our example configuration.

> 예제 설정을 봅시다.

The entire file is in <configuration> tags.

> 전체 파일은 <configuration> 태그 안에 있습니다.

We see a tag that declares an Appender of type ConsoleAppender, and names it STDOUT. Nested within that tag is an
encoder. It has a pattern with what looks like sprintf-style escape codes:

> 우리는 STDOUT이라는 이름의 ConsoleAppender 타입의 Appender를 정의하는 태그를 보았습니다.
> 해당 태그 내에 중첩된 인코더(encoder)가 있습니다. 인코더는 sprintf-style 이스케이프 코드와 같은 어떤 패턴을 가지고 있습니다.

```
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
<encoder>
<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
</encoder>
</appender>
```

Finally, we see a root tag. This tag sets the root logger to DEBUG mode, and associates its output with the Appender
named STDOUT:

> 마지막으로 우리는 root tag를 보았습니다. 이 태그는 DEBUG 모드로 설정되어 있꼬 출력으로 STDOUT 이름의 Appender와 관계되어 있습니다.

```
<root level="debug">
<appender-ref ref="STDOUT" />
</root>
```

### 6.3. Troubleshooting Configuration

Logback configuration files can get complicated, so there are several built-in mechanisms for troubleshooting.

> Logback 설정 파일은 복잡해질 수 있으므로 문제 해결을 위한 몇가지 기본 제공 원리(메커니즘) 있습니다.

To see debug information as Logback processes the configuration, we can turn on debug logging:

> Logback이 설정을 처리하기 때문에 디버그 정보를 보기 위해서는 우리는 debug 로깅을 킬 수 있습니다.

```
<configuration debug="true">
...
</configuration>
```

Logback will print status information to the console as it processes the configuration:

> Logback은 설정을 처리할때 콘솔에 정보를 출력할 것입니다.

```shell
23:54:23,040 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback-test.xml]
at [file:/Users/egoebelbecker/ideaProjects/logback-guide/out/test/resources/logback-test.xml]
23:54:23,230 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender
of type [ch.qos.logback.core.ConsoleAppender]
23:54:23,236 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
23:54:23,247 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type
[ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
23:54:23,308 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to DEBUG
23:54:23,309 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to
Logger[ROOT]
23:54:23,310 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
23:54:23,313 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@5afa04c - Registering current configuration
as safe fallback point
```

If warnings or errors are encountered while parsing the configuration file, Logback writes status messages to the
console.

> 만약 경고(warnings) 또는 에러(errors)가 설정 파일 추출중에 마주친다면, Logback은 콘솔에 상태 메시지를 작성합니다.

There is a second mechanism for printing status information:

> 상태 정보를 출력하기 위해서 두번째 원리가 있습니다.

```xml

<configuration>
    <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener"/>
    ...
</configuration>
```

The StatusListener intercepts status messages and prints them during configuration, as well as while the program is
running.

> StatusListener는 상태 메시지를 가로채고 설정 동안 출력합니다. 뿐만 아니라 프로그램이 실행중에도 출력합니다.

The output from all configuration files is printed, making it useful for locating “rogue” configuration files on the
classpath.

> 모든 설정 파일들이 출력되기 때문에 classpath에서 잘못된 설정 파일을 찾는데 유용합니다.

### 6.4. Reloading Configuration Automatically

Reloading logging configuration while an application is running is a powerful troubleshooting tool. Logback makes this
possible with the scan parameter:

> 애플리케이션이 실행되는 동안 로깅 설정을 재로딩하는 것은 강력하게 문제해결을 하는 도구입니다. Logback은 스캔 파라미터를 가지고 이것을 가능하게 해줍니다.

```xml

<configuration scan="true">
    ...
</configuration>
```

The default behavior is to scan the configuration file for changes every 60 seconds. We can modify this interval by
adding scanPeriod:

> 기본적인 행동은 매 60초마다 변경에 대해서 설정 파일이 스캔하는 것입니다. 우리는 scanPeriod를 추가함으로써 간격을 수정할 수 있습니다.

```xml

<configuration scan="true" scanPeriod="15 seconds">
    ...
</configuration>
```

We can specify values in milliseconds, seconds, minutes, or hours.

> 우리는 밀리세컨트, 초, 분, 시 단위로 값을 명세할 수 있습니다.

### 6.5. Modifying Loggers

In our sample file above, we set the level of the root logger and associated it with the console Appender.

> 위와 같은 샘플 파일에서 우리는 root logger의 레벨을 설정하고 콘솔Appender와 관계된 것을 설정하였습니다.

We can set the level for any logger:

> 우리는 logger의 레벨을 설정할 수 있습니다.

```
<configuration>
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder>
         <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>
   <logger name="com.baeldung.logback" level="INFO" />
   <logger name="com.baeldung.logback.tests" level="WARN" />
   <root level="debug">
      <appender-ref ref="STDOUT" />
   </root>
</configuration>
```

Let's add this to our classpath and run the code:

> 위 설정을 classpath에 추가하고 코드를 실행해봅시다.

```
Logger foobar =
(ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.foobar");
Logger logger =
(ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback");
Logger testslogger =
(ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback.tests");

foobar.debug("This is logged from foobar");
logger.debug("This is not logged from logger");
logger.info("This is logged from logger");
testslogger.info("This is not logged from tests");
testslogger.warn("This is logged from tests");
```

We see this output:

> 우리는 다음과 같은 결과를 볼것입니다.

```
00:29:51.787 [main] DEBUG com.baeldung.foobar - This is logged from foobar
00:29:51.789 [main] INFO com.baeldung.logback - This is logged from logger
00:29:51.789 [main] WARN com.baeldung.logback.tests - This is logged from tests
```

By not setting the level of our Loggers programmatically, the configuration sets them; com.baeldung.foobar inherits
DEBUG from the root logger.

> Logger들은 프로그래밍적으로 레벨을 설정하지 않았음에도 설정이 되었습니다.
> com.baeldung.foobar는 root logger로부터 DEBUG 레벨을 상속받았습니다.

Loggers also inherit the appender-ref from the root logger. As we'll see below, we can override this.

> 로거들은 또한 root logger로부터 appender-ref를 상속받았습니다. 우리는 이것 또한 재정의 할 수 있습니다.

### 6.6. Variable Substitution

Logback configuration files support variables. We define variables inside the configuration script or externally. A
variable can be specified at any point in a configuration script in place of a value.

> Logback 설정 파일은 변수들을 지원합니다. 우리는 설정 스크립트 안에서나 외부적으로 변수들을 정의합니다. 변수는 설정 스크립트에서 변수를 명세할 수 있습니다.

For example, here is the configuration for a FileAppender:

> 예를 들어 FileAppender에 대한 설정입니다.

```
<property name="LOG_DIR" value="/var/log/application" />
<appender name="FILE" class="ch.qos.logback.core.FileAppender">
<file>${LOG_DIR}/tests.log</file>
<append>true</append>
<encoder>
<pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
</encoder>
</appender>
```

At the top of the configuration, we declared a property named LOG_DIR. Then we used it as part of the path to the file
inside the appender definition.

> 설정 맨위에서 우리는 LOG_DIR라는 이름의 속성을 정의하였습니다. 그런다음 우리는 appender 정의 안에 파일에 경로로써 사용하였습니다.

Properties are declared in a <property> tag in configuration scripts, but they're also available from outside sources,
such as system properties. We could omit the property declaration in this example and set the value of LOG_DIR on the
command line:

> 설정 스크립트에서 <property> 태그 안에 속성들을 정의합니다. 그러나 속성들 또한 외부 소스로부터 가져올 수 있습니다. 우리는 이 예제에서 프로퍼티 정의를 생략할 수 있고 명령어 라인에서 LOG_DIR
> 변수의
> 값을 설정할 수 있습니다.

```
$ java -DLOG_DIR=/var/log/application com.baeldung.logback.LogbackTests
```

We specify the value of the property with ${propertyname}. Logback implements variables as text replacement. Variable
substitution can occur at any point in a configuration file where a value can be specified.

> 우리는 ${propertyname}을 가지고 속성의 값을 명세합니다.
> Logback은 텍스트를 대체함으로써 변수들을 구현합니다.
> 변수 대체는 변수가 명세될때설정 파일에 어떤 한 지점에서 발생할 수 있습니다.

## 7. Appenders

Loggers pass LoggingEvents to Appenders. Appenders do the actual work of logging. We usually think of logging as
something that goes to a file or the console, but Logback is capable of much more. Logback-core provides several
useful appenders.

> Logger들은 Appedners에 LoggingEvents를 전달합니다. Appenders는 로깅의 실제 작업을 수행합니다. 일반적으로 로깅은 파일이나 콘솔로 이동하는 것으로 생각하지만, Logback은 훨씬
> 더 많은 기능을 제공합니다. Logback-core는 여러 유용한 appender들을 제공합니다.

### 7.1. ConsoleAppender

We've seen ConsoleAppender in action already. Despite its name, ConsoleAppender appends messages to System.out or
System.err.

> 우리는 이미 ConsoleAppender가 작동하는 것을 보았습니다. ConsoleAppender 이름에도 불구하고 ConsoleAppender는 System.out이나 System.error에 메시지들을
> 추가합니다.

It uses an OutputStreamWriter to buffer the I/O, so directing it to System.err doesn't result in unbuffered writing.

> ConsoleAppender는 입출력 버퍼를 위하여 OutputStreamWriter를 사용합니다. 그래서 System.err에 지정해도 버퍼링되지 않은 쓰기가 발생하지 않습니다.

### 7.2. FileAppender

FileAppender appends messages to a file. It supports a broad range of configuration parameters. Let's add a file
appender to our basic configuration:

> FileAppender가 파일에 메시지를 추가합니다. FileAppender는 설정 파라미터의 광범위한 범위를 지원합니다. 우리의 기본 설정에 파일 appender를 추가해봅시다.

```
<configuration debug="true">
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
   <!-- encoders are assigned the type
        ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
   <encoder>
   <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
   </encoder>
   </appender>

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>tests.log</file>
        <append>true</append>
        <encoder>
            <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="com.baeldung.logback" level="INFO" />
    <logger name="com.baeldung.logback.tests" level="WARN">
        <appender-ref ref="FILE" />
    </logger>

    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>

</configuration>
```

The FileAppender is configured with a file name via <file>. The <append> tag instructs the Appender to append to an
existing file rather than truncating it. If we run the test several times, we see that the logging output is appended to
the same file.

> FileAppender는 <file>을 통하여 파일 이름을 통하여 설정되었습니다.
> <append> 태그는 Appender에 기존 파일을 비우는 대신 파일에 추가하도록 지시합니다.
> 만약 우리가 테스트를 여러번 수행하면 로깅 출력이 같은 파일에 추가된 것을 볼 수 있습니다.

If we re-run our test from above, messages from com.baeldung.logback.tests go to both the console and to a file named
tests.log. The descendant logger inherits the root logger's association with the ConsoleAppender with its association
with FileAppender. Appenders are cumulative.

> 만약 우리가 위 상태에서 테스트를 다시 실행하면 com.baeldung.logback.tests의 메시지가 콘솔과 tests.log 파일로 모두 전송됩니다.

We can override this behavior:

> 우리는 이 로거를 재정의할 수 있습니다.

```xml

<logger name="com.baeldung.logback.tests" level="WARN" additivity="false">
    <appender-ref ref="FILE"/>
</logger>

<root level="debug">
<appender-ref ref="STDOUT"/>
</root>
```

Setting additivity to false disables the default behavior. Tests won't log to the console, and neither will any of its
descendants.

> additivity=false로 설정합니다. Tests 패키지에서는 콘솔에 로그가 되지 않습니다. 그리고 콘솔의 하위 항목에도 기록되지 않습니다.

### 7.3. RollingFileAppender

Often, appending log messages to the same file is not the behavior we need. We want files to “roll” based on time, log
file size, or a combination of both.

> 종종 같은 파일에 로그 메시지들을 추가하는 것은 우리가 필요한 것이 아닐 수 있습니다. 우리는 시간, 로그 파일 사이즈, 또는 시간 및 로그 파일 사이즈 조합으로 파일에 작성하기를 원할 수 있습니다.

For this, we have RollingFileAppender:

> 시간 및 로그 파일 사이즈로 파일을 기록하기 위해서는 우리는 RollingFileAppender를 사용합니다.

```
<property name="LOG_FILE" value="LogFile" />
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${LOG_FILE}.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.gz</fileNamePattern>

        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
    <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
</appender>
```

A RollingFileAppender has a RollingPolicy. In this sample configuration, we see a TimeBasedRollingPolicy.

> RollingFileAppender는 RollingPolicy를 가지고 있습니다. 위 간단한 설정에서 우리는 TimeBasedRollingPolicy를 볼 수 있습니다.

Similar to the FileAppender, we configured this appender with a file name. We declared a property and used it because
we'll be reusing the file name below.

> FileAppender와 비슷하게 우리는 파일 이름을 가진 appender를 설정하였습니다. 우리는 프로퍼티를 정의하고 사용하였습니다. 왜냐하면 우리는 파일 이름을 아래에서 재사용할 것입니다.

We defined a fileNamePattern inside the RollingPolicy. This pattern defines not just the name of files, but also how
often to roll them. TimeBasedRollingPolicy examines the pattern and rolls at the most finely defined period.

> 우리는 RollingPolicy 안에서 fileNamePattern을 정의하였습니다. 이 패턴은 파일의 이름뿐만 아니라 롤링 빈도수를 정의합니다. TimeBasedRollingPolicy는 패턴을 설명하고 특정
> 시간 간격으로 정의할 수 있습니다.

For example:

> 예를 들어 다음과 같습니다.

```
<property name="LOG_FILE" value="LogFile" />
<property name="LOG_DIR" value="/var/logs/application" />
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${LOG_DIR}/${LOG_FILE}.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>${LOG_DIR}/%d{yyyy/MM}/${LOG_FILE}.gz</fileNamePattern>
        <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
```

The active log file is /var/logs/application/LogFile. This file rolls over at the beginning of each month into /Current
Year/Current Month/LogFile.gz and RollingFileAppender creates a new active file.

> 아카이브 로그 파일은 /var/logs/application/LogFile입니다. 이 파일은 매월 초에 /현재년도/현재월/LogFile.gz로 롤오버되고 RollingFileAppender가 새활성 파일을
> 만듭니다.

When the total size of archived files reaches 3GB, RollingFileAppender deletes archives on a first-in-first-out basis.

> 아카이브된 파일의 전체 사이즈가 3GB가 될때 RollingFileAppender는 선입선출 방식으로 아카이브를 삭제합니다.

There are codes for a week, hour, minute, second, and even millisecond. Logback has a reference here.

> 일주일, 시간, 분, 초, 밀리세건드에 대한 코드가 있습니다. Logback은 여기를 참고해주세요.

RollingFileAppender also has built-in support for compressing files. It compresses our rolled files because we named
them LogFile.gz.

> RollingFileAppender는 또한 압축 파일을 기본으로 지원합니다. RollingFileAppender는 롤링된 파일들을 압축합니다. LogFile.gz로 압축파일 이름을 지정합니다.

TimeBasedPolicy isn't our only option for rolling files. Logback also offers SizeAndTimeBasedRollingPolicy, which will
roll based on current log file size as well as time. It also offers a FixedWindowRollingPolicy, which rolls log file
names each time the logger is started.

> TimeBasedPolicy는 롤링 파일에 대한 유일한 옵션이 아닙니다. Logback 또한 현재 로그 파일 사이즈 뿐만 아니라 시간을기준으로ndTimeBasedRollingPolicy를 제안합니다.
> Logback은 또한 로거가 시작할때마다 매 시간이 로그 파일 이름인 FixedWindowRollingPolicy를 제안합니다.

We can also write our own RollingPolicy.

> 우리는 또한 우리의 소유의 RollingPolicy 정책을 작성할 수 있습니다.

### 7.4. Custom Appenders

We can create custom appenders by extending one of Logback's base appender classes. We have a tutorial for creating
custom appenders here.

> 우리는 커스텀한 appender를 Loback의 기본 appender 클래스들중 하나를 상속받음으로써 생성할 수 있습니다. 우리는 커스텀한 appenders를 위한 튜토리얼이 여기 있습니다.

## 8. Layouts

Layouts format log messages. Like the rest of Logback, Layouts are extensible and we can create our own. However, the
default PatternLayout offers what most applications need and then some.

> Layout은 로그 메시지를 형식화합니다. Logback의 rest와 같이 Layout은 연장가능하고 자신만의 것을 생성할 수 있습니다. 그러나, 기본 PatternLayout은 대부분의 애플리케이션들이 필요한
> 것이
> 무엇인지 제공합니다.

We've used PatternLayout in all of our examples so far:

> 지금가지 우리는 모든 예제에서 PatternLayout을 사용했습니다.

```
<encoder>
<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
</encoder>
```

This configuration script contains the configuration for PatternLayoutEncoder. We pass an Encoder to our Appender, and
this encoder uses the PatternLayout to format the messages.

> 이 설정 스크립트는 PatternLayoutEncoder를 위한 설정이 포함되어 있습니다. 우리는 Appender에 Encoder를 전달하였습니다. 그리고 이 encoder는 메시지를 형식화하기 위해서
> PatternLayout을 사용합니다.

The text in the <pattern> tag defines how log messages are formatting. PatternLayout implements a large variety of
conversion words and format modifiers for creating patterns.

> `<pattern>` 태그 안에 텍스트는 로그 메시지가 어떻게 형식화될지를 정의합니다.
> PatternLayout은 컨벤션 워드의 많은 다양성을 구현하고 패턴을 생성하기 위한 형식 수정자입니다.

Let's break this one down. PatternLayout recognizes conversion words with a %, so the conversions in our pattern
generate:

> 이것을 분석해봅시다. PatternLayout은 %가 있는 변환 단어를 인식하므로 패턴의 변화는 다음을 생성합니다.

- %d{HH:mm:ss.SSS} – a timestamp with hours, minutes, seconds and milliseconds
- [%thread] – the thread name generating the log message, surrounded by square brackets
- %-5level – the level of the logging event, padded to 5 characters
- %logger{36} – the name of the logger, truncated to 35 characters
- %msg%n – the log messages followed by the platform dependent line separator character

> - %d{HH:mm:ss.SSS} - 시,분,초,밀리초에 대한 시간 정보입니다.
> - [%thread] - 로그 메시지를 생성하는 쓰레드 이름, 대괄호로 둘러싸여 있습니다.
> - %-5level - 5글자로 패딩된 로깅 이벤트의 레벨
> - %logger{36} - 로거의 이름, 35글자부터는 생략됩니다.
> - %msg%n - 플랫폼에 따른 라인 구분자 글자가 뒤따르는 로그 메시지

So we see messages similar to this:

> 그래서 우리는 다음과 같이 비슷하게 볼수 있습니다.

```
21:32:10.311 [main] DEBUG com.baeldung.logback.LogbackTests - Logging message: This is a String
An exhaustive list of conversion words and format modifiers can be found here.
```

> 컨벤션 단어와 형식 수정자의 전체 목록은 여기서 확인할 수 있습니다.

## 9. Conclusion

In this extensive article, we covered the fundamentals of using Logback in an application.

> 이 광범위한 기사에서는 애플리케이션에서 Logback을 사용한것의 기본 사항을 다룹니다.

We looked at the three major components in Logback's architecture: Logger, Appender, and Layout. Logback has powerful
configuration scripts, which we used to manipulate components for filtering and formatting messages. We also
discussed the two most commonly used file appenders to create, roll over, organize, and compress log files.

> Logback의 아키텍처에서 3가지 주요 구성을 살펴봤습니다. Logger, Appender, Layout을 살펴봤습니다. Logback은 필터링과 형식 메시지들을 위해서 컴포넌트들을 조작하는데스사용되는크립트를
> 가지고 있습니다. 또한 로그 파일을 생성, 롤오버, 구성 및 압축하는데 가장 일반적으로 사용되는 두가지 file appender에 대해서도 설명하였습니다.

As usual, code snippets can be found over on GitHub.

> 일반적으로 코드 스니펫은 GitHub에서 찾을 수 있습니다.

## References

> source code : [source code](/스프링_라이브러리_데모/springboot_practice-main/spring_demo/src/main/java/com/baeldung/logback/)
> [A Guide To Logback](https://www.baeldung.com/logback)


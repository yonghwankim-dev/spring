## 학습 키워드

- @GeneratedValue(strategy = GenerationType.IDENTITY)
- @Enumerated(EnumType.STRING)
- @EnableWebSecurity
- csrf().disable().headers().frameOptions().disable()
- [authorizeRequests](#authorizerequests)
- [antMatchers](#antmatchers)
- [anyRequest](#anyrequest)
- logout().logoutSuccessUrl("/")
- oauth2Login
- userInfoEndpoint
- userService
- spring-boot-starter-oauth2-client
- registrationId
- userNameAttributeName
- OAuthAttributes
- sessionUser

## @Enumerated(EnumType.STRING)

- JPA에서 데이터베이스로 저장할때 **Enum 값을 어떤 형태로 저장할지 결정**하는데 설정합니다.
- 기본적으로 int로된 숫자가 저장됩니다. 하지만 숫자로 저장되면 데이터베이스에서 이 숫자가 무슨 의미인지 알수가 없습니다.
- 문자열(EnumType.STRING)로 저장될 수 있도록 선업합니다. 즉, EnumType.String으로 설정하면 **enum 타입의 값이
  데이터베이스에 문자열로 저장**됩니다.

## @EnableWebSecurity

- Spring Security 설정들을 활성화 시켜줍니다.

## csrf().disable().headers().frameOptions().disable()

- csrf().disabled() : CSRF(Cross-Site Request Forgery) 공격 방어 기능을 끄는 설정
    - CSRF 공격은 인증된 사용자의 권한을 도용하여 악성 요청을 실행하는 공격입니다.
- headers().frameOptions().disable() : X-Frame-Options 헤더를 사용하여, 웹 페이지를 다른 도메인에서
  iframe으로 불러들일 수 있도록 제한하는 기능을 끄는 설정
    - 기본적으로 웹 페이지를 다른 도메인에서 iframe으로 불러 들일 수 있도록 제한합니다.
- 실습에서는 h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.

## authorizeRequests

- URL별 권한 관리를 설정하는 옵션의 시작점입니다.
- authorizeRequests가 선언되어야지 antMatchers 옵션을 사용할 수 있습니다.

## antMatchers

- 권한 관리 대상을 지정하는 옵션입니다.
- URL, HTTP 메소드별로 관리가 가능합니다.
- "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줄 수 있습니다.
- "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 했습니다.

## anyRequest

- 설정된 값들 이외의 나머지 URL들을 나타냅니다.
- authenicated()를 추가하여 나머지 URL들은 모두 인증된 사람들에게만 허용하게 합니다.
- 인증된 사람들은 로그인한 사람들을 의미합니다.

## logout().logoutSuccessUrl("/")

- 로그아웃 기능에 대한 여러 설정의 진입점입니다.
- 로그아웃 성공시 리다이렉션할 URL을 설정합니다.

## oauth2Login

- OAuth2 로그인 기능에 대한 여러 설정의 진입점입니다.

## userInfoEndpoint

- OAuth2 로그인 성공 이후 사용자 정보를 가져올때 설정들을 담당합니다.

## userService

- 소셜 로그인 성공시 후속 조치를 시행할 UserService 인터페이스의 구현체를 등록합니다.
- 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시합니다.

## spring-boot-starter-oauth2-client

- 소셜 로그인 등 클라이언트 입장에서 소셜 기능 구현시 필요한 의존성 라이브러리입니다.
- spring-security-oauth2-client와 spring-security-oauth2-jose를 기본으로 관리해줍니다.

## registrationId

- 현재 로그인이 진행중인 서비스(구글, 로그인)를 구분하는 코드입니다.

## userNameAttributeName

- OAuth2 로그인 진행 시 키가 되는 필드값을 이야기합니다. Primary Key와 같은 의미입니다.
- 구글 : 기본적으로 코드를 지원 (기본 코드=sub)
- 네이버, 카카오 등 : 기본 지원하지 않습니다.
- 네이버 로그인과 구글 로그인을 동시 지원할때 사용됩니다.

## OAuthAttributes

- OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다.
- 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용합니다.

## sessionUser

- 세션에 사용자 정보를 저장하기 위한 Dto 클래스입니다.

## @Target(ElementType.PARAMETER)

- 이 애노테이션이 생성될 수 있는 위치를 지정합니다.
- PARAMETER로 지정되어서 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다.
- 이 외에도 클래스 선언문에 쓸수 있는 TYPE 등이 있습니다.

## @interface

- 이 파일을 애노테이션 클래스로 지정합니다.

## HandlerMethodArgumentResolver.supportsParameter()

- 컨트롤러 메소드의 특정 파라미터를 지원하는 판단합니다.
- 예를 들어 파라미터에 @LoginUser 애노테이션이 붙어 있고 파라미터 클래스 타입이 SessionUser.class인 경우 true를 반환합니다.

## HandlerMethodArgumentResolver.resolveArgument()

- 파라미터에 전달할 객체를 생성합니다.
- 여기서는 세션에서 객체를 가져옵니다.

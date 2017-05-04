# ProtoType Vaadin

****

새로개발해야 하는 파트너에게 제공할 백오피스 시스템을 개발하기 위한 플랫픔으로 바딘Vaadin 을 고려중이다.

* Spring Boot 1.5.3
  * Vaadin
  * Spring Data JPA
  * Spring Security

`Partner` 엔티티를 만들어 가입(`Signup`), 로그인(`Login`), 비밀번호초기화(`ResetPassword`, 아직 구현 중) 기능을 만들었다.

로그인을 하면 MainUI 를 보여주고, 거기서 파트너에 대한 그리드(Grid) 처리를 해볼 예정이다.

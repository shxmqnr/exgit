package exgit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .anyRequest().permitAll()
	        );
	    return http.build();
	}  // 진짜 최소 테스트용(aws 연결용), 로그인호면 안나오게 하기
	
	//FilterChain없이 실행하면 signup, ex 를 url에 침 -> 로그인 페이지가 나옴
	// Spring Security는 SecurityFilterChain이 하나도 없으면 "기본 보안 설정"을 자동으로 적용함
	// 기본 보안 규칙 : 모든 요청 -> 인증 필요, 
	// 커스텀 로그인 페이지가 없을 경우 Spring Security기본 로그인 페이지 강제 사용 그래서
	/* /signup 요청
	 → 인증 필요
	 → 로그인 페이지로 리다이렉트
	 → (네가 만든 로그인 페이지 없음)
	 → 기본 로그인 화면 등장   */
	
	// passwordencoder만 있어도 시큐리티가 켜지는 이유
	// implementation 'org.springframework.boot:spring-boot-starter-security' 얘때문에
    // 이게 있으면 security의존성을 감지 -> SecurityFilterChain이 없으면 Spring Boot가 기본 SecurityConfig 자동 생성
	
	/* SecurityFilterChain이 없으면
		→ “보안 꺼짐”이 아니라
		→ “기본 보안 켜짐”이다  */


}

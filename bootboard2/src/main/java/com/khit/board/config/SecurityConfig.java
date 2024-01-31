package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration    //환경 설정에 사용하는 어노테이션
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customService;
	
	//@Bean는 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할때 필요함
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//인증 및 권한 서비스 실행
		http.userDetailsService(customService);
		
		//인증 설정 -> 권한 설정
		// 로그인이 필요없음: /", "/css/**", "/images/**", "/auth/main", "/member/**"
		// 로그인이 필요: 그외의 경로
		http
		  .authorizeHttpRequests(authorize -> authorize
				  .requestMatchers("/", "/login", "/css/**", "/images/**", "/js/**").permitAll()
				  .requestMatchers("/board/write").authenticated()
				  .requestMatchers("/member/list").hasAnyAuthority("ADMIN")
				  .requestMatchers("/member/**", "/board/**", "/public-data/**").permitAll()
				  .anyRequest().authenticated()
				  )
		          .formLogin(form -> form
		        		.loginPage("/login")
		          	    .defaultSuccessUrl("/")
		          	    .permitAll()
		          );
		
		     //접근 권한 페이지
		     http.exceptionHandling().accessDeniedPage("/auth/accessDenied");
		          
		     http.logout().logoutUrl("/logout")
		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		            .invalidateHttpSession(true) //세션 무효화
		            .logoutSuccessUrl("/");
		
		return http.build();
	}
	
	//암호화 설정
	//PasswordEncoder를 상속받은 BCryptPasswordEncoder를 반환
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

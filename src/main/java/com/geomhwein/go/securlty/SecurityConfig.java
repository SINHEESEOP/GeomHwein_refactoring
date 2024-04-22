package com.geomhwein.go.securlty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private UserAuthService userAuthService;


	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UserAuthService userAuthService) throws Exception {

		http.csrf().disable();


		http.formLogin()
				.loginPage("/sign_in") //우리가 만들어 놓은 커스터마이징된 페이지의 경로를 로그인페이지로 사용함
				.loginProcessingUrl("/signInForm") //로그인을 시도하는 경로
				.usernameParameter("userId") //username파라미터 변경시
				.passwordParameter("userPwHash") //password파라미터 변경시
				.defaultSuccessUrl("/") //로그인 성공후 이동하고 싶은 경로
				.failureUrl("/sign_in?err=true") //로그인 실패시 이동하고 싶은 경로
				.failureHandler( authenticationFailureHandler() )
				.and()
				.exceptionHandling().accessDeniedPage("/") //권한이 없을 시에 처리
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/sign_in"); //로그아웃 주소 /myLogout으로, 로그아웃 이후에는 /hello로


		http.authorizeRequests( (authorize) -> authorize.antMatchers("/normaluser/**").authenticated()
														.antMatchers("/user/**").hasAnyRole("USER", "CREATOR", "ADMIN")
														.antMatchers("/creator/**").hasAnyRole("CREATOR", "ADMIN")
														.antMatchers("/admin/**").hasRole("ADMIN")
														.anyRequest().permitAll() );


//		나를기억해
		http.rememberMe()
				.key("projectGO") //리멤버미를 쿠키로 동작시키는데 그때, 쿠키에 저장되는 토큰값을 만들 비밀키
				.tokenValiditySeconds(360000000) //1시간 동안 유효한 토큰
				.rememberMeParameter("remember-me") //화면에서 전달되는 checkbox 파라미터 값
				.userDetailsService(userAuthService ) //리멤버미 토큰이 있을때 실행시킬 로그인시도 서비스
				.authenticationSuccessHandler(authenticationSuccessHandler());

		return http.build();
	}

	//인증실패핸들러
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		CustomAuthenticationFailure custom = new CustomAuthenticationFailure();
		custom.setRedirectURL("/sign_in?err=true");
		return custom;
	}

	//리멤버미핸들러
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomRememberMeHandler();
	}

}
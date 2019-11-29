package com.cos.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.shop.handler.myLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	//1. Bean 어노테이션은 메서드에 붙여서 객체 생성시 사용
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}


	//2. 필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/admin/**","/board/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.usernameParameter("username")	//둘다 기본값은 username, password이지만 일단 적어두겠음
		.passwordParameter("password")  //둘다 기본값은 username, password이지만 일단 적어두겠음
		.loginPage("/user/loginForm")
		.loginProcessingUrl("/user/loginProcess")
		.defaultSuccessUrl("/home")
		.and()
		.logout().logoutSuccessHandler(new myLogoutSuccessHandler());
		//.and().authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().hasAnyRole("ADMIN")
	}

	@Autowired
	private UserDetailsService userDetailsService;

	
	
	//내가 인코딩 하는게 아니라, 어떤 인코딩으로 패스워드가 만들어졌는지 알려주는거임!!!!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}

}
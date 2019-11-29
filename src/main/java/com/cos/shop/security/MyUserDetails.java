package com.cos.shop.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.shop.model.MyUser;

//Principal (접근 주체) 세션처럼 사용 -> Spring Security Context에 저장됨
public class MyUserDetails implements UserDetails{

	private MyUser user;

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		// true를 리턴하면 만료되지 않음
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//true를 리턴하면 계정이 잠겨있지 않음
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// true를 리턴하면 패스워드가 만료되지 않음
		return true;
	}

	@Override
	public boolean isEnabled() {
		// true를 리턴하면 사용가능한 계정임
		return true;
	}
	
	
	
	
	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Role. 권한을 가지고 있음
		// 계정이 가지고 있는 권한 목록을 리턴
		return null;
	}

	
}

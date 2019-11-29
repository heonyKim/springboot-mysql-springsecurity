package com.cos.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.shop.model.MyUser;
import com.cos.shop.repository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private MyUserRepository mRepo;
	
	// loginForm에서 action="/user/loginProcess"
	// 스프링필터 체인이 낚아채서 loadUserByUsername함수를 호출한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user= mRepo.findByUsername(username);
		MyUserDetails userDetails = null;
		
		if(user !=null) {

			userDetails = new MyUserDetails();
			userDetails.setUser(user);

		}else {
			throw new UsernameNotFoundException("username is null [" + username +"]");
		}
	
		return userDetails;
	}

}

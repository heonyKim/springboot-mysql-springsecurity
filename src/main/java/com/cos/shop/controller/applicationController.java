package com.cos.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.shop.model.MyUser;
import com.cos.shop.repository.MyUserRepository;
import com.cos.shop.security.MyUserDetails;

@Controller //제어의 역전, IOC
public class applicationController {
	
	@Autowired //DI
	private MyUserRepository mRepo;
 	
 	@Autowired
 	private BCryptPasswordEncoder passwordEncoder;
 	
	@GetMapping("/home")
	public @ResponseBody String home() {
		return "<h1>HOME</h1>";
	}
	
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/user/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@GetMapping("/admin/test")
	public @ResponseBody String adminTest(@AuthenticationPrincipal MyUserDetails userDetails) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("id : "+userDetails.getUser().getId() +"<br>");
		sb.append("id : "+userDetails.getUsername() +"<br>");		
		sb.append("id : "+userDetails.getPassword() +"<br>");
		sb.append("id : "+userDetails.getUser().getEmail() +"<br>");
		
		return sb.toString();
	}
	
	@GetMapping("/board/test")
	public @ResponseBody String boardTest() {
		return "<h1>board</h1>";
	}
	
	
	
	//첫번재 : csrf설정
	//두번째 : password 인코더
	@PostMapping("/user/create")
	public String create(MyUser user) {	//jackson bind가 발동됨
		System.out.println(user);
		
		//패스워드 암호화는 시큐리티 적용시 필수!!!!
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		mRepo.save(user);
		return "redirect:/home";
	}
	
}

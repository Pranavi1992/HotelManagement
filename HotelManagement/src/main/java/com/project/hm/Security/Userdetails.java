package com.project.hm.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

 
import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.UserRepository;

public class Userdetails implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserRegistration reg=       userRepository.findByUserName(username);
		List<Authorities>authority=reg.getAuthorities();
		System.out.println(authority);
		if(reg==null)
		{
			throw new RuntimeException("exception raised in my userdetails");
		}
		
		return new UserDetailImple(reg);

	}
	

}

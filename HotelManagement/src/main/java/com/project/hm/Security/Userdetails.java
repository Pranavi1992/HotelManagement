package com.project.hm.Security;


import java.util.Collection;
import java.util.Collections;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


 
import com.project.hm.entity.UserRegistration;


public class Userdetails implements UserDetails{
	
	private UserRegistration userRegistration;

	public Userdetails(UserRegistration user) {
		super();
		this.userRegistration=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(userRegistration.getRole()));
	}

	@Override
	public String getPassword() {
		
		return userRegistration.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userRegistration.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	

}

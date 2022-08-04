package com.project.hm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.hm.customexceptions.RegistrationCustomException;
import com.project.hm.entity.AdminRegistration;
import com.project.hm.entity.Authorities;
import com.project.hm.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository registrationRepo;
	
	public AdminRegistration addReg(AdminRegistration registrationdto) {

	
		
		
		if (registrationdto == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepo.existsByUserName(registrationdto.getUserName())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter unique");
		} else

		{
			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

			registrationdto.setPassword(bCryptPasswordEncoder.encode(registrationdto.getPassword()));
			Authorities authority = new Authorities();
			authority.setRole("ADMIN");
			List<Authorities> authorities = new ArrayList<Authorities>();
			authorities.add(authority);
			registrationdto.setAuthorities(authorities);
			registrationRepo.save(registrationdto);
			return registrationdto;
		}

	}
}

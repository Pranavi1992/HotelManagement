package com.project.hm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hm.entity.UserRegistration;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration,Integer> {
	
	public UserRegistration findByUserName(String userName);

	public Boolean existsByUserName(String userName);

	//public Boolean existsByEmail(String email);
	
	//public Boolean existsByPhoneNumber(String email);

	public List<UserRegistration> findByPassword(String password);

	//public RegistrationEntity findByEmail(String s);

	//public RegistrationEntity findByPhoneNumber(String s);

}

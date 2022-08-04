package com.project.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hm.entity.AdminRegistration;


@Repository
public interface AdminRepository  extends JpaRepository<AdminRegistration, Integer>{

	public AdminRegistration findByUserName(String userName);

	public Boolean existsByUserName(String userName);

}

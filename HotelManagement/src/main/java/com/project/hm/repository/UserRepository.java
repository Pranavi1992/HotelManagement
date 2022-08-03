package com.project.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hm.entity.UserRegistration;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration,Integer> {

}

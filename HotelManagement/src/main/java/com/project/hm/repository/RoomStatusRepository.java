package com.project.hm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.Rooms;

public interface RoomStatusRepository  extends JpaRepository<RoomBookingStatus, Long>{

	//public List<RoomBookingStatus> findByRoomNumber(Integer roomNumber);


} 

package com.project.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hm.entity.Rooms;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long> {

}

package com.project.hm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roomDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetails {
@Id
	private int roomId;

	private String photos;
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="room_fk")
	private Rooms rooms;
	
}

package com.project.hm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	private int roomNumber;
	private String photos;
	private String description;
}

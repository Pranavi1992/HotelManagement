package com.project.hm.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rooms {

	@Id
	private int roomId;
	private int roomNumber;
	private double roomPrice;
	
	@Enumerated(value=EnumType.STRING)
	private Type type;
	
	
	
}

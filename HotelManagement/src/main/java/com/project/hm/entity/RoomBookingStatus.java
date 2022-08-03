package com.project.hm.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="roomBookingStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingStatus {
	@Id
  private int roomId;
	private boolean roomStatus;
	private LocalDate checkIn;
	private LocalDate checkOut;
}

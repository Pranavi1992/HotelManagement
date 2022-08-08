package com.project.hm.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
  private Long id;
	private boolean roomStatus;
	//private LocalDate checkIn;
	//private LocalDate checkOut;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    @NotNull(message = "Please enter start date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    @NotNull(message = "Please enter end date")
    private Date endDate;

	@ManyToOne()
	@JoinColumn(name="room_fk")
	private Rooms rooms;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id_fk")
	private UserRegistration userRegistration;
	
}

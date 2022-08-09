package com.project.hm.controller;

import java.security.Principal;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.RoomRepository;
import com.project.hm.repository.RoomStatusRepository;
import com.project.hm.repository.UserRepository;
import com.project.hm.service.RoomStatusService;

@RestController
@RequestMapping("/api/booking")
public class RoomStatusController {
	@Autowired
	private RoomStatusService  roomStatusService;
	@Autowired
	private RoomStatusRepository roomStatusRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/room-booking")
    public ResponseEntity<RoomBookingStatus> createBooking(@RequestBody RoomBookingStatus booking,  
    		                                                @RequestParam Long roomId,
    		                                                Principal principal) {
		String username=principal.getName();
		
	UserRegistration user=this.userRepository.findByUsername(username);
	System.out.println("--------------"+user);
		
		   this.roomRepository.findById(roomId).map(id->{
			   booking.setRooms(id);
		   
			return id;
		});
		   this.userRepository.findById(user.getId()).map(id->{
			   booking.setUserRegistration(id);
			   return id;
		   });
		   
		    if(booking.getStartDate()!=null && booking.getEndDate()!=null) {
		    	booking.setRoomStatus(true);
		 
		    }
		
		    Long days=ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
		    
		    System.out.println("=========="+days);
		   roomStatusRepository.save(booking);
		    
		    
		    return new ResponseEntity<RoomBookingStatus>(roomStatusRepository.save(booking),HttpStatus.OK);
		
    }
	
	
	

}

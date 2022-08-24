package com.project.hm.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.Rooms;
import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.RoomRepository;
import com.project.hm.repository.RoomStatusRepository;
import com.project.hm.repository.UserRepository;
import com.project.hm.response.AvailableRoomResponse;
import com.project.hm.service.RoomService;
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
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/room-booking")
    public ResponseEntity<?> createBooking(@RequestBody RoomBookingStatus booking,  
    		                                                @RequestParam Long roomId,
    		                                                Principal principal) {
		String username=principal.getName();
		
	UserRegistration user=this.userRepository.findByUsername(username);

		
    
		Optional<Rooms> room=this.roomRepository.findById(roomId).map(id->{

			   booking.setRooms(id);
		   
			return id;
		});
		   this.userRepository.findById(user.getId()).map(id->{
			   booking.setUserRegistration(id);
			   return id;
		   });
		  
		
		   Double roomPrice=room.get().getRoomPrice();
		   if(booking.getCheckIn()!=null && booking.getCheckOut()!=null) {
		    	//Optional<Rooms> room=this.roomRepository.findById(roomId);
		    
		    	if(room.isPresent())
		          room.get().setRoomStatus(true);
		 this.roomRepository.save(room.get());
		    }

        /*   LocalDate date = LocalDate.now();
           if(booking.getCheckIn().isAfter(date)&& booking.getCheckOut().isBefore(date) && (booking.getCheckIn()==date || booking.getCheckOut()==date)) {
               System.out.println("Room Unavailable");

           }
           else {
               System.out.println("Room Available");
           }*/
		   LocalDate date = LocalDate.now();
		   if(booking.getCheckIn().isBefore(date))//||booking.getCheckOut().isBefore(booking.getCheckIn()))
		   {
			   System.out.println("CANNOT BOOK BEFORE TODAY");
			   return new ResponseEntity<>("cannot book",HttpStatus.BAD_REQUEST);
		   }
		   else {
		    Long days=ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut()); 
		    
		    
		    
		    booking.setTotal(roomPrice*days);
		    booking.setTotalDays(days);
		    booking.setGstTax((booking.getTotal()*18/100));
		    booking.setTotalPrice(booking.getTotal()+booking.getGstTax());
		    System.out.println("=========="+days);
		     roomStatusRepository.save(booking);
		    
		   }
	//   return new ResponseEntity<>(booking,HttpStatus.OK);
		
	/*
	 * if(booking.getRooms().isRoomStatus()==true ) {
	 * 
	 * return new ResponseEntity<>("Already Booked",HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 * 
	 * else {
	 * 
	 * roomStatusRepository.save(booking);
	 * 
	 * return new ResponseEntity<RoomBookingStatus>(booking,HttpStatus.OK);
	 * 
	 * }
	 * 
	 */
       

        return new ResponseEntity<RoomBookingStatus>(booking,HttpStatus.OK);

   

/*}

	 
	 public ResponseEntity RoomBooking(Authentication authentication, @RequestBody RoomBookingStatus booking) 
	 {
*/
//	// String username = authentication.getName();
//	UserRegistration registration = userRepository.findByUsername(username);
//	 booking.setId(registration.getId());
//	Long roomIds=room.get().getId();
//	List<Rooms> listOfBookings = roomRepository.findById(roomIds);
//	 Iterator iterator = listOfBookings.iterator();
//	 int booked = 0;
//	 while (iterator.hasNext())
//	 {
//		 RoomBookingStatus slotDateEntity = (RoomBookingStatus) iterator.next();
//		 booking.setId(slotDateEntity.getId());
//		 if (slotDateEntity.getId() == null && roomRepository.existsById(booking.getId())
//		              && booking.getCheckIn().equals(slotDateEntity.getCheckIn())
//                      // && bookSlot.getDate().equals(slotTimeEntity.getDate())
//                         && booking.getCheckOut().equals(slotDateEntity.getCheckOut())) 
//		 {
//			 //booking.set("");
//			  roomStatusRepository.save(booking);
//		 booked++;
//		 }
//		}
//
//	 if(booked > 0)
//	 {
//		 return new ResponseEntity("Booked",HttpStatus.ACCEPTED);
//		 
//	 }
//	 else
//	 {
//		 return new ResponseEntity("Already Booked",HttpStatus.BAD_REQUEST);
//	 }
	}
	 @GetMapping("/a")
		public ResponseEntity<List<AvailableRoomResponse>> getAllRoom() {
			List<AvailableRoomResponse> room=this.roomRepository.getAllAvailabeRooms();
			return new ResponseEntity<List<AvailableRoomResponse>>(room,HttpStatus.OK);
			
		}
	}
	
	
	
	



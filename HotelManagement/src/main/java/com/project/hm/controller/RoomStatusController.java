package com.project.hm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.service.RoomStatusService;

@RestController
@RequestMapping("/api/booking")
public class RoomStatusController {
	@Autowired
	private RoomStatusService  roomStatusService;
	@PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> createReservation(@Valid @RequestBody RoomBookingStatus booking) {
		roomStatusService.createBooking();
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
	
	
	

}

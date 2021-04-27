package com.squareshift.airplaneseats.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squareshift.airplaneseats.request.AirplaneSeatsRequest;
import com.squareshift.airplaneseats.response.AirplaneSeatsResponse;
import com.squareshift.airplaneseats.service.AirplaneSeatsService;

@RestController
@RequestMapping("v1/")
public class AirplaneSeatsController {
	
	@Autowired
    private AirplaneSeatsService service;
	
	@CrossOrigin
	@PostMapping("fillSeats")
    public AirplaneSeatsResponse fillAirplaneSeats(@Valid @RequestBody AirplaneSeatsRequest req) throws Exception {
        return service.fillSeats(req);
    }
    
    @CrossOrigin
	@GetMapping("ping")
    public String pingFunc() {
        return "pong ";
    }
}

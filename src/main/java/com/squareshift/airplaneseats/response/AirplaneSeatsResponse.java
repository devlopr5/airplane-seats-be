package com.squareshift.airplaneseats.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirplaneSeatsResponse {
	private List<int[][]> seatsArrangement;
	private int totalSeats;
}

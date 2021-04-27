package com.squareshift.airplaneseats.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirplaneSeatsRequest {
	private List<int[]> rowsAndColumns;
	private int noOfPassengers;
}

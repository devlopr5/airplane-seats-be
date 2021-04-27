package com.squareshift.airplaneseats.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.squareshift.airplaneseats.request.AirplaneSeatsRequest;
import com.squareshift.airplaneseats.response.AirplaneSeatsResponse;

@Service
public class AirplaneSeatsService {
	public AirplaneSeatsResponse fillSeats(AirplaneSeatsRequest req){
		
		List<int[]> seats = req.getRowsAndColumns();
		int p = req.getNoOfPassengers();
		int maxRows = 0;
		int count = 1;
		int totalSeats = 0;
		
		List<int[][]> seatsFill = new ArrayList<int[][]>(); 
		for( int[] z : seats ){
			int[] t = new int[z[0]];
			
			int[][] arr = new int[z[1]][z[0]];
			for(int i =0 ; i< z[1]; i++) arr[i] = Arrays.copyOf(t, t.length); 
			 // initializing each seat as '0' - denoting unoccupied seat
			/* Eg:  [2,3] [3,4] we will have 
			 * Zone1 	Zone2 
			 * - 0 0 	- 0 0 0
			 * - 0 0	- 0 0 0
			 * - 0 0 	- 0 0 0
			 * 			- 0 0 0
			 * */
			totalSeats += z[0] * z[1];
			seatsFill.add(arr);
			maxRows = Math.max(z[1], maxRows);
		}
		boolean isAisle = false; // Flag to check if aisle seats are filled
		boolean isWindow = false; // Flag to check if window seats are filled
		boolean isMiddle = false;  // Flag to check if MiddleSeats are filled
		
		if(!isAisle){
			while(!isAisle && count <= p){
				// Iterate till last row is reached & check zone-wise
				for(int row = 0; row < maxRows && count <= p ; row++){ 
					
					//Fill appr row in each zone.
					for(int z = 0; z < seatsFill.size() && count <= p; z++){ 
						int[][] zone = seatsFill.get(z);
						
						if( row < zone.length){ // skip zones with lesser rows ( since it is iterating every zone till max rows available)
							if(z==0 ) 
								zone[row][zone[row].length-1] = count++;
							else if(z == seatsFill.size()-1)
								zone[row][0] = count++;
							else{
								zone[row][0] = count++;
								if(count > p) break;
								zone[row][zone[row].length-1] = count++;
							}
						}else continue;
					}
					
				}
				if(count <= p ) isAisle = true;
			}
		}
		if(!isWindow){
			while(!isWindow && count <= p){
				for(int row = 0; row < maxRows && count <= p; row++){ // Iterate till last row is reached & check zone-wise
					
					int[][] leftMostZone = seatsFill.get(0); // Fill left side window seat first 
					if( row < leftMostZone.length) //check if row exists for this zone
						leftMostZone[row][0] = count++;

					if(count > p) break;
					int[][] rightMostZone = seatsFill.get(seatsFill.size()-1); //Fill right side window seat next
					if( row < rightMostZone.length ) ///check if row exists for this zone
						rightMostZone[row][rightMostZone[row].length-1] = count++;
					
				}
				if(count <= p ) isWindow = true;
			}
		}
		if(!isMiddle){
			while(!isMiddle && count <= p){
				for(int row = 0; row < maxRows && count <= p ; row++){ // Iterate till last row is reached & check zone-wise
					
					//Fill appr row in each zone.
					for(int z = 0; z < seatsFill.size() && count <= p; z++){ 
						int[][] zone = seatsFill.get(z);
						
						if(row < zone.length ){ // skip zones with lesser rows ( since we are iterating every zone till max rows available)
							int temp = 1;
							while(temp < zone[row].length-1 && count <= p){
								zone[row][temp++] = count++;
								
							}
						} else continue;
					}
				}

				if(count <= p) isMiddle = true;
			}
		}

		AirplaneSeatsResponse res = new AirplaneSeatsResponse();
		res.setSeatsArrangement(seatsFill);
		res.setTotalSeats(totalSeats);
		return res;
	} 
}

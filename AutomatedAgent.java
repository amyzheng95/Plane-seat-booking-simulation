package A2;

import java.util.*;

public class AutomatedAgent implements Runnable{
	//ID of agent
	private String ID;
	//Number of seats need to be automated booked
	private int count;
	private planeSeatGrid plane;
	//delay time for sleeping
	private static final int DELAY = 1000;

	/**
	 * Constructor for creating an automated agent for seat booking
	 * @param ID the ID of the agent
	 * @param count the number of times that the agent will try booking for the tickets
	 * @param p the planeSeatGrid for the plane
	 */
	public AutomatedAgent(String ID,int count,planeSeatGrid p){
		this.ID = ID;	
		this.count = count;
		this.plane = p;
	}
	
	/**
	 * runs the thread for randomly reserving a seat from the plane
	 */
	public void run(){
		while (!Thread.interrupted()) {
			try{
				int i = 1;
				
				while(i<= count){
					System.out.println(ID +"Seat available");
					if(!plane.isFullPlane()){
						
						int randomIndex = (int) (Math.random()*200);
						this.plane.reserveSeat(randomIndex, this.ID);
										
					}else{
						Thread.currentThread().interrupt();
						
					}
					
					
					Thread.sleep((int)(Math.random()*DELAY));
					
				}
			}
			catch(InterruptedException exception){
				System.out.println("interuption");
				Thread.currentThread().interrupt();
			}
		}
	}
}

package A2;

import java.util.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

public class planeSeatGrid{
	
	//Create an ArrayList to store the seats (buttons) in the right columns
	private final List<JButton> list = new ArrayList<JButton>(200);

	/**
	 * Constructor for displaying the GUI of the reservation platform 
	 */
	public planeSeatGrid(){
		this.display();
	}
	
	/**
	 * Generates all the seats for the plane
	 * @return the seat panel of 4 rows and 50 columns with a isle in between
	 */	
	private JPanel createSeatGrid() {
		//Create the Panel of the program
		JPanel planeSeatPanel = new JPanel();
		
		//Set the form of the panel
		planeSeatPanel.setLayout(new BorderLayout());
		
		//Set the size of the grid
		JPanel p = new JPanel(new GridLayout(5,50));
		
		//loop through rows and columns to generate the seats
		for (int i = 0; i < 4; i++) {//row
			for(int j = 0; j < 50; j++){//column
				
				int row = i;
				int col = j;
				//Create buttons
				JButton gb = createAeroplaneSeats(row,col);
				list.add(gb);
				
				//this adds the button to the GUI
				p.add(gb);
				
				//Create the isle
				if(i==1 && j==49){
					for(int x = 0; x < 50 ; x++){
						p.add(new JLabel());
					}//end for			
				}//end if
			}//end for
		}//end for
    
    return p;
	}
	
	/**
	 * Get the seat/ Button by index
	 * @param r the row of the seat
	 * @param c the column of the seat
	 * @return the seat (JButton) at the given row and column
	 */
	private JButton getSeat(int r, int c) {
		System.out.println("r is: " + r +", " + "c is : " + c );
		int index = r*50 + c ;
		System.out.println("The index is: " + index);
		return list.get(index);		
	}
	
	
	/**
	 * Create the buttons for the seats
	 * @param row the row of the seat
	 * @param col the column of the seat
	 * @return the seat button
	 */
	
	//Prof said on Facebook we are allowed to do a clicking GUI for mannual agent
	
	private JButton createAeroplaneSeats(final int row, final int col) {
		final JButton seatButton = new JButton();
		
		seatButton.addActionListener(new ActionListener() {    		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = planeSeatGrid.this.getSeat(row, col);
				//If button is pressed manually, ID would be set to 3
				button.setText("3");
				//Button will then be disabled
				button.setEnabled(false);
			}
		});
		return seatButton;
	}
	
	
	/**
	 * This is for displaying the seat grid onto the monitor
	 */
	private void display() {
        JFrame f = new JFrame("Seat Reservation System - Click on the available seat you want to reserve");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(createSeatGrid());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
	
	
	/**
	 * This method is for seat reservation by the automated agent
	 * @param index the position of the seat which starts with 0 to 199
	 * @param ID the id of the agent
	 * @return a boolean which is true when seat is successfully reserved
	 * @throws InterruptedException
	 * (precondition plane is not full)
	 * (precondition seat must be available)
	 * (postcondition it's either true or false depending whether the seat
	 * 					was successfully reserved)
	 */
		public synchronized boolean reserveSeat(int index, String ID)
				throws InterruptedException{
			//Checks if plane is full before reservation
			if(!isFullPlane()){		
				JButton b = list.get(index);
				//Check if the seat is available
				if(isEmptySeat(b) == true){	
					//System.out.println(ID +"Seat available");
					b.setText(ID);
					b.setEnabled(false);				
					return true;					
				}
			}
			notifyAll();
			return false;
		}
	
	
	/**
	 * Check whether the plane is full
	 * @return a boolean of true if the plane is full
	 */
	//Check if all seat are taken	
	public boolean isFullPlane(){
		//Seat taken
		Boolean seatTaken = true;
		//counter for keeping the index of the seats
		int counter = 0;
	
		//goes through the whole plane to check for empty seats
		while(seatTaken && counter < 200){
			seatTaken = !isEmptySeat(list.get(counter));
			counter++;
		}
		//if there is one seat that is not taken
		if(!seatTaken){
			return false;
			
		}
		JOptionPane.showMessageDialog(null,"Plane is full");
		return true;
	}
	
	
	/**
	 * Check if the seat is taken
	 * @param b takes in the seat(JButton) for checking
	 * @return boolean is the seat is empty
	 */
	private boolean isEmptySeat(JButton b){
		//System.out.println(b.isEnabled());
		return b.isEnabled();
	}
	
		
}
	
		



package A2;

public class Tester {

	public static void main(String[] args) {
		/*System.out.println("r" + row + ",c" + col
	    + " " + (seatButton == gb)
	    + " " + (seatButton.equals(gb)));
	    */

		planeSeatGrid g = new planeSeatGrid();
		//the number of times the automated agent reserve tickets
		final int count = 200;
		
		Runnable r1 = new AutomatedAgent("1",count,g);
		Runnable r2 = new AutomatedAgent("2",count,g);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		
		/*EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	
                new planeSeatGrid();
                
            }
        });*/
		
		
		
		
	}

}

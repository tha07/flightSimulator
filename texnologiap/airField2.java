import java.io.*;
import java.util.*;
import java.lang.*;

public class airField2 extends airplane
{

	public static void main(String [] args)
	{	
		String airPlane;
		
		
    	final int arrivalTimeMin = 2; //used to test queue order
    	final int arrivalTimeMax = 4; //used to test queue order	
    	//final int arrivalTimeMin = 4; 
    	//final int arrivalTimeMax = 10; 
    	final int departTimeMin = 3; 
    	final int departTimeMax = 15;
    	final int removalArriveTime = 4; 
    	final int removalDepartTime = 6;
    	final int min = 100;
		 
		int addArrive = RandomInRange(arrivalTimeMin, arrivalTimeMax);
		int removeArriveTemp = genRandNum(removalArriveTime);
		int addDepart = RandomInRange(departTimeMin, departTimeMax);
		int removeDepartTemp = genRandNum(removalDepartTime);
		
		int addArriveTemp, addDepartTemp, temp;
		
		int counter = 0;// num of mins simulation has ran
		int numAirplanes = 0;// counts the number of planes initialized
		int numCrashes = 0;//counts the number of planes crashed
		int fieldInUseTime = 0;
		int remTemp = 0;
		
		LinkedList<ArrivingPlane> arrivingList = new LinkedList<ArrivingPlane>();
		LinkedList<DepartingPlane> DepartingList = new LinkedList<DepartingPlane>();
       
        while(counter <=min){
        	remTemp = 0;
        	fieldInUseTime--;
       		System.out.println("min " + counter);

	      	if(counter == addDepart){//add a departing plane
	        	numAirplanes++;
	        	airPlane = "Departing plane";
	        	DepartingList.offer(new DepartingPlane());
	        	System.out.println("added " + airPlane + " to the list");
	        	addDepartTemp= RandomInRange(departTimeMin, departTimeMax);
	        	addDepart += addDepartTemp;
				System.out.println("Next departing airPlane should be added in " + addDepartTemp + " minutes");
	      	}//end if add departing plane
	      	
			if(counter  == addArrive){//add an arriving plane
	        	numAirplanes++;
	        	airPlane = "Arriving Plane";
	        	arrivingList.offer(new ArrivingPlane());
	        	System.out.println("added " + airPlane + " to the list");
	        	addArriveTemp= RandomInRange(arrivalTimeMin, arrivalTimeMax);
	        	addArrive += addArriveTemp;
				System.out.println("Next ariving airPlane should be added in " + addArriveTemp + " minutes");
	      	}//end if add arriving plane

			if(fieldInUseTime <= 0 && !arrivingList.isEmpty()){//plane lands
				try{
						fieldInUseTime = 4;
				        System.out.println("Update: " + arrivingList.poll());
				        removeArriveTemp = genRandNum(removalArriveTime);
				}	//end try
				catch(NoSuchElementException noSuchElementException){
					System.out.println("list is empty");
		         	noSuchElementException.printStackTrace();
				}//end catch
			}//end if remove arriving plane

			if(fieldInUseTime <= 0 && !DepartingList.isEmpty()){//plane takes off
				try{
					fieldInUseTime = 6;
					System.out.println("Update: " + DepartingList.poll());
					removeDepartTemp = genRandNum(removalDepartTime);
				}//end try
				catch(NoSuchElementException noSuchElementException){
			        System.out.println("list is empty");
		         	noSuchElementException.printStackTrace();
				}//end catch
		    }//end if remove departing plane
		      
			for (ArrivingPlane entry : arrivingList){
        		if(entry.getFuelTime() <= 0){
					//entry.poll();
					System.out.println("A plane has crashed");
					numCrashes++;
				}//end if 
			}//end for loop
			
			for(ArrivingPlane entry : arrivingList)
			{
				remTemp++;
				temp = entry.getFuelTime();
				entry.setFuelTime(temp - 1);
				if(entry.getFuelTime() <= 4){//removes plane from queue if it runs out of fuel
					arrivingList.subList(remTemp, remTemp).clear();
					System.out.println("Plane has run out of fuel and crashed");//would remove if could figure out how
				}//end if	
			}//end for loop
			
			
			for(ArrivingPlane entry : arrivingList)
			{
				System.out.println("Planes in arriving queue: " + entry);//would remove if could figure out how
			}
	      counter++;
	      System.out.println();
      }// end while loop
	}//end main
}//end class


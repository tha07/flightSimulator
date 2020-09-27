import java.io.*;
import java.util.*;
import java.lang.*;

public class ArrivingPlane extends airplane 
{
	public int fuelTime;
        
    public ArrivingPlane()
    {
    	fuelTime = 6 + genRandNum(54);
    }
        
    public ArrivingPlane(int ft, String n) 
    {
    	super(n);
 		fuelTime = ft;
    }
        
	public int getFuelTime() {
		return fuelTime;
	}

	public void setFuelTime(int fuelTime) {
		this.fuelTime = fuelTime;
	}
	
	public int compareTo (Object obj) {
		ArrivingPlane that = (ArrivingPlane) obj; 	
		int a = this.getFuelTime();
        int b = this.getFuelTime();
        
        if (a<b) {return 1;}
        if (a>b) {return -1;}
        return 0;
	}
	
	public String toString(){
		return "Arriving plane: " + getFuelTime() + " mins fuel.";
	}
}


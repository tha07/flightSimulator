import java.io.*;
import java.util.*;
import java.lang.*;

public class DepartingPlane extends airplane
{
	public int beforeLate;
        
    public DepartingPlane()
    {
    	beforeLate = 4 + genRandNum(34);
    }
        
    public DepartingPlane(int bf, String n) 
    {
    	super(n);
 		beforeLate = bf;
    }
        
	public int getBeforeLate() {
		return beforeLate;
	}

	public void setBeforeLate(int beforeLate) {
		this.beforeLate = beforeLate;
	}

	public String toString(){
		return "Departing plane: " + getBeforeLate() + " mins before late.";
	}
}


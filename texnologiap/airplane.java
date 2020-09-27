import java.io.*;
import java.util.*;
import java.lang.*;

public class airplane 
{	
	public String status;
	
	public static int RandomInRange(int min, int max){
		int temp = max - min;
		Random randomNumbers = new Random();
		int num = 1 + min + randomNumbers.nextInt(temp);
		return(num);
	}
	
	public static int genRandNum(int range)
	{
		Random randomNumbers = new Random();
		int num = randomNumbers.nextInt(range);
		num++;
		return(num);
	}
	
	public airplane()
	{
		status = "on time";
	}
	
	public airplane (String n) 
	{
		status = n;
	}

	public String getStatus() 
	{
		return status;
    }
    
    public void setStatus(String status) 
    {
    	this.status = status;
    }	

	public String toString()
	{
		return ("The airplane is " + getStatus());
	}
}


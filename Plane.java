
import java.util.Random;
//import java.lang;
public class Plane implements Comparable<Plane>
{
	public int fuel;
  private int arrivalTime;
	private int flightNumber;

	public Plane(int fuel, int arrivalTime, int flightNumber)
	{
		this.fuel = fuel;
		this.arrivalTime = arrivalTime;
		this.flightNumber = flightNumber;
	} // end constructor

	public int getFuelAmount()
	{
		return fuel;	}

	public int getFlightNumber()
{
	return flightNumber;
}
public int getArrivalTime(){
	return this.arrivalTime;
}

	public int compareTo(Plane newPlane)
	{
		if (this.fuel > newPlane.fuel) { return -1;} //the first plane has more
		else { return 1;} //second plane has more or equal

	}
} // end plane

//plane now has 3 parameters, update that everywhere. See if flight number var needs to be added to AirportSimulation..
//get stuff to land.

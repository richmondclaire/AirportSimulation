import java.io.*;
import java.util.*;
import java.util.Scanner;

public class AirportSimulation
{
    public static void main(String[] args)
    {
	AirportSimulationRunner myAirport = new AirportSimulationRunner();

  //Get input parameters from the command line.
  Scanner scanner = new Scanner(System.in);
  int duration = Integer.parseInt(scanner.nextLine());
  double takeoffProb = Double.parseDouble(scanner.nextLine());
  double landingProb = Double.parseDouble(scanner.nextLine());
  int maxFuel = Integer.parseInt(scanner.nextLine());
  myAirport.simulate(duration, takeoffProb, landingProb, maxFuel);
  myAirport.displayResults();

    }  // end main
}

class AirportSimulationRunner{

      //set our variables we must keep track of
      private QueueInterface<Plane> lineToTakeoff;
      private PriorityQueueInterface<Plane> lineToLand;
      private int totalNumTakeoffs;
      private int totalNumLandings;
      private int AverageWaitTakeoff;
      private int AverageWaitLanding;
      public static int flightNumber;
      private int totalTimeWaitedToTakeoff;

      public AirportSimulationRunner(){ //constructor
        lineToTakeoff = new LinkedQueue<Plane>();
        lineToLand = new LinkedPriorityQueue<Plane>();
        reset(); //initialize the simulation
      }


    public void simulate(int duration, double takeoffProb, double landingProb, int maxFuel){
          for ( int clock = 0; clock < duration; clock ++){

              if (Math.random() < landingProb ){ //a plane wants to land
                //totalNumLandings++;
                flightNumber++;
                int randFuel1 = (int) (Math.random()*maxFuel +1);
                Plane newArrival = new Plane(randFuel1, clock, flightNumber);
                // add it into the priority queue and rearrange the queue
                lineToLand.add(newArrival);
                System.out.println("Flight " + flightNumber
                         + " entered landing queue at time " + clock);
                }

              if (Math.random() < takeoffProb){ // a plane wants to depart
                flightNumber++;
                int randFuel2 = (int) (Math.random()*maxFuel +1);
                Plane newTakeoff = new Plane(randFuel2, clock, flightNumber);
                //add this plane to the regular queue
                lineToTakeoff.enqueue(newTakeoff);
                System.out.println("Flight " + flightNumber
                         + " is waiting for takeoff at time " + clock);
              }

              if (lineToLand.isEmpty() == false){ //if planes need to land, first in queue lands
                Plane nextToLand = lineToLand.remove();
                //  int timeWaited = clock - nextToLand.getArrivalTime();
                //  totalTimeWaitedToLand = totalTimeWaitedToLand + timeWaited;
                  totalNumLandings++;
        System.out.println("Fight " + nextToLand.getFlightNumber()
                         + " landed at time " + clock + " with " + nextToLand.getFuelAmount() + " fuel left.");
              }

              else if((lineToLand.isEmpty())&(!lineToTakeoff.isEmpty()))
              {
                Plane nextToTakeoff = lineToTakeoff.dequeue(); //takes off!
                totalTimeWaitedToTakeoff = totalTimeWaitedToTakeoff + (clock - nextToTakeoff.getArrivalTime());
                totalNumTakeoffs++;
      System.out.println("Fight " + nextToTakeoff.getFlightNumber() + " took off at time " + clock);
              } //end of takeoff else

if (duration > 0) { duration--;} //
//need to go thru line to land and subtract one from the fuel of each of them

//int lineToLandSize = lineToLand.getSize();
//int fuelChanger[] = new int[lineToLandSize];
//for ( int i = 0; i < lineToLandSize; i++){
  //fuelChanger[i] = lineToLand.remove().fuel - 1;
//}

          } //ends for loop
    } // end simulate

    public void displayResults()
 {
   System.out.println();
   System.out.println("Number of landings = " + totalNumLandings);
   System.out.println("Number of takeoffs = " + totalNumTakeoffs);
   System.out.println("Total time waited to takeoff = " + totalTimeWaitedToTakeoff);
   double averageTimeWaitedtoTakeoff = ((double)totalTimeWaitedToTakeoff) / totalNumTakeoffs;
   System.out.println("Average time waited to takeoff = " + averageTimeWaitedtoTakeoff);
   //double averageTimeWaitedToLand = ((double)totalTimeWaitedToLand) / totalNumTakeoffs;
   //System.out.println("Average time waited to takeoff = " + averageTimeWaitedToLand);
   int leftInLineToLand = lineToLand.getSize();



 } // end displayResults



    public final void reset(){
      lineToLand.clear();
      lineToTakeoff.clear();
      totalNumTakeoffs = 0;
      totalNumLandings = 0;
      AverageWaitTakeoff = 0;
      AverageWaitLanding = 0;
      flightNumber = 0;
      totalTimeWaitedToTakeoff = 0;

    } //end reset

  }

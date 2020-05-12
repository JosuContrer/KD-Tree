package kdtree.tests;

import kdtree.KDNode;
import kdtree.KDTree;
import kdtree.Point;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class TimeEval {

	public static void main(String[] args) {

		// Need to run time evaluation for insert method for each data set (2^5 -> 2^18)
		
		// For each N:
		// Add N nodes with randomly generated point values to K-d tree
		// Do x trials where a point is randomized and 
		nearestNeighborTrial();
		
		
	}
	
	static void nearestNeighborTrial() {
		Random rd = new Random();
		
		System.out.println(" N\t\tAvg\t\t\tMin\t\t\tMax");
		
		for (int ex = 2; ex <= 18; ex++) { // Looping over exponents 2-18 but only keeping 5-18
			
			int NUM_TRIAL = 10000; // Number of trials
			KDTree kd = new KDTree(); // Creating a new kd tree for every exponent
			
			for(long n = 1; n <= (long) Math.pow(2, ex); n++) { // Adding 2^ex random points to kd tree
				Point p = new Point(rd.nextDouble(), rd.nextDouble());
				kd.insert(p);
			}
			
			long minTime = Integer.MAX_VALUE; // Minimum time so far
			long totalTime = 0; // Total time for average
			long maxTime = 0;
			
			for (int trial = 0; trial < NUM_TRIAL; trial++) { // For 20 trials
				
				Point p1 = new Point(rd.nextDouble(), rd.nextDouble()); // Creating a new point

				long start = System.nanoTime(); // Starting stopwatch
				kd.nearest(p1); // Method being timed
				long elapsed = System.nanoTime() - start; // Stopping stopwatch
				
				totalTime += elapsed;
				if (elapsed < minTime) { minTime = elapsed; }
				if (elapsed > maxTime) {
					maxTime = elapsed;
				}
			}
			double avgTime = totalTime/(double)NUM_TRIAL; // Now we have min time and avg time
			
			// report results
			System.out.println(String.format("%2d\t\t%f\t%f\t%f", ex, avgTime / 1000.0, minTime / 1000.0, maxTime / 1000.0));
		}
		
	}
	

}

package kdtree.tests;

import kdtree.KDNode;
import kdtree.KDTree;
import kdtree.Point;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class TimeEval {

	public static void main(String[] args) {
		nearestNeighborTrial();
	}
	
	static void nearestNeighborTrial() {
		Random rd = new Random();
		
		System.out.println(" N\t\tAvg\t\tMin\t\tMax\t\tBruteAvg\tBruteMin\tBruteMax");
		
		for (int ex = 2; ex <= 18; ex++) { // Looping over exponents 2-18 but only keeping 5-18
			
			int NUM_TRIAL = 10000; // Number of trials
			KDTree kd = new KDTree(); // Creating a new kd tree for every exponent
			Point[] points = new Point[(int)Math.pow(2, ex)];
			
			for(long n = 1; n <= (long) Math.pow(2, ex); n++) { // Adding 2^ex random points to kd tree
				Point p = new Point(rd.nextDouble(), rd.nextDouble());
				kd.insert(p);
				points[(int)n-1] = p;
			}
			
			long minTime = Integer.MAX_VALUE; // Minimum time so far
			long totalTime = 0; // Total time for average
			long maxTime = 0;
			
			long bruteMinTime = Integer.MAX_VALUE;
			long bruteTotalTime = 0;
			long bruteMaxTime = 0;
			
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
				
				long startBrute = System.nanoTime();
				// measure time to get find smallest
				double smallestBrute = p1.distTo(points[0]);
				for(int x = 1; x < points.length; x++) {
					double currentDistance = p1.distTo(points[x]);
					if(currentDistance < smallestBrute) {
						smallestBrute = currentDistance;
					}
				}
				// Now smallestBrute is the smallest point from the array.
				long elapsedBrute = System.nanoTime() - startBrute;
				
				bruteTotalTime += elapsedBrute;
				if (elapsedBrute < bruteMinTime) { bruteMinTime = elapsedBrute; }
				if (elapsedBrute > bruteMaxTime) {
					bruteMaxTime = elapsedBrute;
				}
				
			}
			double avgTime = totalTime/(double)NUM_TRIAL; // Now we have min time and avg time
			double bruteAvgTime = bruteTotalTime/NUM_TRIAL;
			
			// report results
			System.out.println(String.format("%2d\t\t%f\t%f\t%f\t%f\t%f\t%f", ex, avgTime / 1000.0, minTime / 1000.0, maxTime / 1000.0, bruteAvgTime / 1000.0, bruteMinTime/1000.0, bruteMaxTime/1000.0));
		}
		
	}
	

}

package automationFramework;

import java.util.Random;

public class RandomNumberGenerator {

	public int RandomInteger(){
		int end = 10, start = 3;
		Random random = new Random();
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)end - (long)start + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * random.nextDouble());
	    int randomNumber =  (int)(fraction + start);
	    System.out.println("\n" + randomNumber * 1000 + "\n");
	    return randomNumber;
	  }

}

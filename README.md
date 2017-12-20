# Tea-Drinkers-Problem
Tea Drinkers Problem: A group of n tea drinkers sit around a table to drink tea and talk. To drink tea, each tea
drinker requires a cup, a saucer, and a spoon and tea drinker cannot drink tea until the tea drinker has all three.
There are only n-1 cups, n-1 saucers, and n-1 spoons but any tea drinker can take any cup, saucer, or spoon as long
as it is free. Each tea drinker is represented by a runnable class that looks like:

import java.util.concurrent.*;
public class TeaParty {
	class TeaDrinker implements Runnable {
		private int myID;
		private int mySleepTimeNS;
		public TeaDrinker(int id, int sleep) {
			myID = id;
			mySleepTimeNS = sleep;
		}
		private void sleep(int t) {
			try {
			Thread.sleep(t);
			} catch (InterruptedException ie) {
			System.out.println("Sleep interrupted; should not happen");
			}
		}
		public void run() {
			System.out.println("Tea drinker " + myID +
			" starting after sleep of " + mySleepTimeNS + "ns");
			sleep(mySleepTimeNS);
			while (true) { // run for ever
				sleep(mySleepTimeNS);
				System.out.println("Tea drinker " + myID + " getting cup, saucer, and spoon");
				// add code to get the cup, saucer, and spoon here
				System.out.println("Tea drinker " + myID +
				" drinking tea for " + mySleepTimeNS + "ns");
				sleep(mySleepTimeNS);
				System.out.println("Tea drinker " + myID +
				" finished drinking tea, releasing cup, saucer, and spoon");
				// add code to release the cup, saucer, and spoon here
				System.out.println("Tea drinker " + myID +
				" will talk for " + mySleepTimeNS + "ns");
				sleep(mySleepTimeNS);
			}
		}
	}
	public static void main(String [] args) {
	}
}


You will use this class as an inner class of a public class called TeaParty. This class will also define a set of
semaphores that are used to synchonize the tea drinker threads in their use of the cups, saucers, and spoons. The
main method of TeaParty will initialize the semaphore counts (you will use counting semaphores, one for each of
the three items the tea drinkers need) and then the main method will create and start n TeaDrinker threads (you
main method should get the value for n from the String array parameter of the main method.


Add code in the highlighted area to use the semaphores to synchronize the tea drinkers to use the n-1 sets of cup,
saucers, and spoons.
Submit your Java program TeaParty.java for this part of the project as part of your zip archive file.
The Java Semaphore class is defined in the java.util.concurrent package. The semaphore methods that you will
need to use are:
• Semaphore(int permits), the constructor. permits is the intial value for the semaphore count.
• void acquire() Acquires a permit from this semaphore, blocking until one is available, or the thread
is interrupted. acquire() throws the InterruptedException and must be used in a try-catch block.
• void release() Releases a permit, returning it to the semaphore. No exception is thrown by this
method.

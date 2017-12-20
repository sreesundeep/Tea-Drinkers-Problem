import java.util.concurrent.*;

public class TeaParty {

  static int nTeaDrinkers;
  static Cup cup;
  static Saucer saucer;
  static Spoon spoon;  
  static Semaphore mutex = new Semaphore(1);  

  static class Cup {
       public Semaphore cupLock = new Semaphore(nTeaDrinkers-1);
    
       void pickCup() {
         try {
           cupLock.acquire();
         }
         catch (InterruptedException e) {
           System.out.println(e.getMessage());
         }
       }

       void releaseCup() {
         cupLock.release();
       }
  }

  static class Saucer {
       public Semaphore saucerLock = new Semaphore(nTeaDrinkers-1);
          
       void pickSaucer() {
         try {
           saucerLock.acquire();
         }
         catch (InterruptedException e) {
           System.out.println(e.getMessage());
         }
       }

       void releaseSaucer() {
         saucerLock.release();
       }
  }
  

  static class Spoon {
       public Semaphore spoonLock = new Semaphore(nTeaDrinkers-1);      
              
       void pickSpoon() {
         try {
           spoonLock.acquire();
         }
         catch (InterruptedException e) {
           System.out.println(e.getMessage());
         }
       }

       void releaseSpoon() {
         spoonLock.release();
       }
  }  

  public static class TeaDrinker implements Runnable {
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
         System.out.println("Tea drinker " + myID +" starting after sleep of " + mySleepTimeNS + "ns");
         sleep(mySleepTimeNS);
         try{
               while (true) { // run for ever
                  sleep(mySleepTimeNS);
                  System.out.println("Tea drinker " + myID + " getting cup, saucer, and spoon");
                  
                  // acquire mutex lock and pick cup, saucer and spoon
                  mutex.acquire();          
                  cup.pickCup();
                  saucer.pickSaucer();
                  spoon.pickSpoon();
                  mutex.release(); 
                  
                  System.out.println("Tea drinker " + myID +
                  " drinking tea for " + mySleepTimeNS + "ns");
                  sleep(mySleepTimeNS);
                  System.out.println("Tea drinker " + myID +
                  " finished drinking tea, releasing cup, saucer, and spoon"); 
                             
                  // release the cup, saucer, and spoon 
                  cup.releaseCup();
                  saucer.releaseSaucer();
                  spoon.releaseSpoon();
                  
                  System.out.println("Tea drinker " + myID +
                  " will talk for " + mySleepTimeNS + "ns");
                  sleep(mySleepTimeNS);
               }
            }catch(Exception e){
              System.out.println(e.getMessage());
            }
      }
      
   }


   public static void main(String args[]) {   
       int threadSleepTime = 1000;
       //reading number of teadrinkers from args parameter
       try{
          nTeaDrinkers = Integer.parseInt(args[0]);
       }catch(ArrayIndexOutOfBoundsException e){
          System.out.println("No Input! for number of teadrinkers");
          System.exit(0); 
       }
          
       System.out.println("Total Number of Tea Drinkers : "+nTeaDrinkers); 
       
       //Instantiate the resource classes
       cup = new Cup();
       saucer = new Saucer();
       spoon = new Spoon();
       
       //Create the threads (Tea Drinkers)
       for (int i = 0; i < nTeaDrinkers; i++) {
         new Thread(new TeaDrinker(i, threadSleepTime)).start();
       }  
  }

}
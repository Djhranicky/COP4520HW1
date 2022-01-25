// David Hranicky
// COP 4520 

// Imports
import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;




// Main thread class, spawns child threads that sieve concurrently
public class AllPrimes{

    
    // Variables that the child threads need access to
    // Sieve holds the sieve information from the Sieve of Eratosthenes Algorithm

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Boolean[] sieve;
        int limitNumber;
        SharedCounter counter = new SharedCounter(2);
        long beginTime, endTime, sumOfPrimes = 0;
        double totalTime;
        TreeSet<Integer> primes, lastTenPrimes;
        int numPrimes;
        final int numThreads = 8;
        Thread[] threads = new Thread[numThreads];

        primes = new TreeSet<Integer>();
        
        

        System.out.println("Enter a number:");
        limitNumber = sc.nextInt();
        beginTime = System.currentTimeMillis();

        sieve = new Boolean[limitNumber];
        Arrays.fill(sieve, true);

        long threadBegin = System.currentTimeMillis();

        for(int i = 0; i < numThreads; i++){
            threads[i] = new Thread(new PrimeChecker(sieve, limitNumber, counter, i));
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e){
                ;
            }
        }
        
        long threadEnd = System.currentTimeMillis();
        double threadTime = threadEnd - threadBegin;

        for(int i = 2; i < limitNumber; i++){
            if(sieve[i]){
                primes.add(i);
                sumOfPrimes += i;
            }
        }
        
        lastTenPrimes = new TreeSet<Integer>();
        numPrimes = primes.size();
        System.out.println("Total number of primes: "+numPrimes);
        System.out.println("Sum of all primes: "+sumOfPrimes);
        System.out.println("Ten greatest primes: ");
        for(int i = 0; i < 10; i++){
            lastTenPrimes.add(primes.pollLast());
        }
        System.out.println(lastTenPrimes);

        endTime = System.currentTimeMillis();
        totalTime = (endTime - beginTime);
        System.out.printf("Execution Time (seconds): %.3f\n",totalTime/1000);
        System.out.printf("Thread Time (seconds): %.3f\n", threadTime/1000);


        sc.close();
    }

}
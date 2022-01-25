// David Hranicky
// COP 4520

import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;

public class PrimesSequential {
    
    public static void main(String[] args){
        // Variable Decleration
        Scanner sc = new Scanner(System.in);
        long beginTime;
        int limitNumber;
        Boolean sieve[];
        TreeSet<Integer> primes = new TreeSet<Integer>();
        int numPrimes;
        long sumOfPrimes = 0;
        TreeSet<Integer> lastTenPrimes = new TreeSet<Integer>();

        // Capture number from user
        System.out.println("Enter number to find all primes less than that number");
        limitNumber = sc.nextInt();
        beginTime = System.currentTimeMillis();

        // Initialize the Prime Sieve, using the Sieve of Eratosthenes algorithm
        sieve = new Boolean[limitNumber];
        Arrays.fill(sieve, true);

        long algBegin = System.currentTimeMillis();

        // Implementation of the algorithm
        for(int i = 2; i*i < limitNumber; i++){
            if(sieve[i]){
                for(int j = i*i; j < limitNumber; j+=i){
                    sieve[(int)j] = false;
                }
            }
        }

        long algEnd = System.currentTimeMillis();
        double algTime = algEnd - algBegin;

        // Run through and find the primes, put them in a sorted set
        for(int i = 2; i < limitNumber; i++){
            if(sieve[i]){
                primes.add(i);
                sumOfPrimes += i;
            }
        }
        
        // Calculate the requested information and print
        numPrimes = primes.size();
        System.out.println("Total number of primes: "+numPrimes);
        System.out.println("Sum of all primes: "+sumOfPrimes);
        System.out.println("Ten greatest primes: ");
        for(int i = 0; i < 10; i++){
            lastTenPrimes.add(primes.pollLast());
        }
        System.out.println(lastTenPrimes);

        long endTime = System.currentTimeMillis();
        double totalTime = (endTime - beginTime);
        System.out.printf("Execution Time (seconds): %.3f\n",totalTime/1000);
        System.out.printf("Algorithm TIme (seconds): %.3f\n",algTime/1000);

        sc.close();
    }

}

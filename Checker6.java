// David Hranicky
// COP 4520

public class Checker6 implements Runnable{
    Boolean[] sieve;
    int limitNumber;
    SharedCounter counter;
    int threadNum;

    public Checker6(Boolean[] sieve, int limitNumber, SharedCounter counter, int threadNum){
        this.sieve = sieve;
        this.limitNumber = limitNumber;
        this.counter = counter;
        this.threadNum = threadNum;
        System.out.println("Thread "+threadNum+" started");
    }

    @Override
    public void run(){
        boolean running = true;
        int candidate;
        while(running){
            candidate = counter.getAndIncrement();

            if(candidate*candidate > limitNumber){
                running = false;
            }
            else if(sieve[candidate]){
                for(int i = candidate*candidate; i < limitNumber; i+=candidate){
                    sieve[i] = false;
                }
            }
        }
    }
    
}

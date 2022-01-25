// David Hranicky
// COP 4520

public class SharedCounter {
    int value;

    public SharedCounter(int initialValue){
        this.value = initialValue;
    }

    public void set(int newValue){
        this.value = newValue;
    }

    public synchronized int getAndIncrement(){
        int temp = value;
        value +=1;
        return temp;
    }
}

import java.util.Arrays;
import java.util.Scanner;

class Demo implements Runnable {
    String name;
    Thread t;
    int sum = 0;
    int[] partition;

    Demo(String threadName, int[] partition) {
        this.partition = partition;
        name = threadName;
        t = new Thread(this, name);
        System.out.println("Starting thread: " + name);
        t.start();
    }

    public int getSum() {
        for (int j : partition) {
            sum += j;
        }
        return sum;
    }

    public void run() {
        System.out.println(name + " summing...");
        sum = getSum();
        System.out.println("Sum for " + name + ": " + sum);
    }
}

public class MultiThreadArray {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Scanner obj2 = new Scanner(System.in);
        System.out.println("Enter amount of elements in the array:");
        int size = Integer.parseInt(obj.nextLine());
        System.out.println("Enter amount of threads in the array:");
        int threads = Integer.parseInt(obj2.nextLine());
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 31);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
        int partSize = size / threads;
        for (int i = 0; i < array.length; i += partSize) {
            int[] partition = Arrays.copyOfRange(array, i, Math.min(i + partSize, array.length));
            System.out.println("Partition: " + Arrays.toString(partition));
        }
        Demo[] demos = new Demo[threads];
        for (int i = 0; i < threads; i++) {
            int[] partition = Arrays.copyOfRange(array, i * partSize, Math.min((i + 1) * partSize, array.length));
            Demo d = new Demo("Thread " + i, partition);
            demos[i] = d;
        }
        try {
            System.out.println("Main thread is waiting...");
            for (Demo thread : demos) {
                thread.t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted");
        }
        System.out.println("Main Thread total summing...");
        int totalSum = 0;
        for (Demo thread : demos) {
            totalSum += thread.sum;
        }
        System.out.println("Total Sum: " + totalSum);
        System.out.println("Main thread finished.");

    }
}

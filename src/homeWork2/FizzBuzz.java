package homeWork2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    private final int n = 30;
    public static volatile AtomicInteger number = new AtomicInteger(1); //volatile - доступ з багатьох потоків
    public BlockingQueue<String> queue =new  LinkedBlockingQueue<>();
    public synchronized void add(String element){
        queue.add(element);
    }
    public synchronized void fizz(){
        while (number.get()<=n){
            if (number.get() % 3 == 0 && number.get() % 5 != 0){
                add("fizz");
                number.incrementAndGet();
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void buzz(){
        while (number.get()<=n){
            if (number.get() % 5 == 0 && number.get() % 3 != 0){
                add("buzz");
                number.incrementAndGet();
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void fizzbuzz(){
        while (number.get()<=n){
            if (number.get() % 3 == 0 && number.get() % 5 == 0){
                add("fizzbuzz");
                number.incrementAndGet();
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void number(){
        while (number.get()<=n){
            if (number.get() % 3 != 0 && number.get() % 5 != 0){
                add(String.valueOf(number));
                number.incrementAndGet();
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!fizzBuzz.queue.isEmpty()) {
            try {
                System.out.println(fizzBuzz.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

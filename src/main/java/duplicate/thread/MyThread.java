package duplicate.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread..." + Thread.currentThread().getName());
    }
}

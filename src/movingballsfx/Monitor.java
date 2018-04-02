package movingballsfx;

import java.util.concurrent.locks.ReentrantLock;

public class Monitor implements RWMonitorInterface {

    private ReentrantLock writeLock;

    public Monitor(){
        writeLock = new ReentrantLock();
    }

    /**
     * should be called when Reader wants to enter the critical section.
     * the method allows a thread to enter the critical section, or it will
     * block the thread.
     *
     * @throws InterruptedException when a thread receives an interrupt during
     *                              entering, or while being blocked during entering.
     */
    @Override
    public void enterReader() throws InterruptedException {

    }

    /**
     * should be called when Writer wants to enter the critical section.
     * the method allows a thread to enter the critical section, or it will
     * block the thread.
     *
     * @throws InterruptedException when a thread receives an interrupt during
     *                              entering, or while being blocked during entering.
     */
    @Override
    public void enterWriter() throws InterruptedException {

    }

    @Override
    public void exitReader() {

    }

    @Override
    public void exitWriter() {

    }
}

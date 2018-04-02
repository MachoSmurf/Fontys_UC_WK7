package movingballsfx;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor implements RWMonitorInterface {

    private int readersActive;
    private int readersWaiting;
    private int writersActive;
    private ReentrantLock monLock;
    private Condition okToRead;
    private Condition okToWrite;

    public Monitor(){
        readersActive = 0;
        writersActive = 0;
        readersWaiting = 0;
        monLock = new ReentrantLock();
        okToWrite = monLock.newCondition();
        okToRead = monLock.newCondition();
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
        monLock.lock();
        try{
            while(!(writersActive == 0)) {
                readersWaiting++;
                okToRead.await();
            }
            readersActive++;
            System.out.println("Reader entering CS: " + readersActive);
        }
        catch (InterruptedException e){
            throw new InterruptedException();
        }
        finally {
            monLock.unlock();
        }
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
        monLock.lock();
        try{
            while(!(writersActive == 0 && readersActive == 0)){
                okToWrite.await();
            }
            writersActive++;
            System.out.println("Writer entering CS: " + writersActive);
        }
        catch (InterruptedException e){
            throw new InterruptedException();
        }
        finally {
            monLock.unlock();
        }
    }

    @Override
    public void exitReader() {
        monLock.lock();
        try {
            readersActive--;
            if (readersActive == 0){
                okToWrite.signal();
            }
            System.out.println("Reader leaving CS: " + readersActive);
        }
        finally {
            monLock.unlock();
        }
    }

    @Override
    public void exitWriter() {
        monLock.lock();
        try{
            writersActive--;
            System.out.println("Writer leaving CS: " + writersActive);
            if (readersWaiting > 0){
                okToRead.signal();
            }
            else{
                okToWrite.signal();
            }
        }
        finally {
            monLock.unlock();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingballsfx;

/**
 *
 * @author erik
 */
public interface RWMonitorInterface {

    // 
    /**
     * should be called when Reader wants to enter the critical section.
     * the method allows a thread to enter the critical section, or it will
     * block the thread.
     * @throws InterruptedException when a thread receives an interrupt during
     * entering, or while being blocked during entering.
     */
    void enterReader() throws InterruptedException;

    /**
     * should be called when Writer wants to enter the critical section.
     * the method allows a thread to enter the critical section, or it will
     * block the thread.
     * @throws InterruptedException when a thread receives an interrupt during
     * entering, or while being blocked during entering.
     */
    void enterWriter() throws InterruptedException;

    // Should be called when Reader exits critical section. is non-blocking.
    void exitReader();

    // Should be called when Writer exits critical section. is non-blocking.
    void exitWriter();
    
}

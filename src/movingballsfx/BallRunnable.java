/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movingballsfx;

/**
 *
 * @author Peter Boots
 * Runnable, verantwoordelijk voor het bewegen van ballen buiten en binnen
 * de critical section. Runnable dient gebruik te gaan maken van een 
 * RWMonitorInterface voor synchronisatie.
 */
public class BallRunnable implements Runnable {

    private final Monitor monitor;
    private Ball ball;

    public BallRunnable(Ball ball, Monitor monitor) {
        this.ball = ball;
        this.monitor = monitor;
    }

    /**
     * beweeg bal, check of kritieke sectie in of uitgegaan wordt. zo ja,
     * roep de juiste monitor methode aan. slaap het aantal milliseconden
     * dat aangegeven wordt door de snelheid van de bal.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ball.move();
                
                // TODO: entering c.s.
                // tip: rethrow exceptions so they are handled by catch block below
                try{
                    if (ball.isEnteringCs()){
                        if (ball.getRole() == Role.READER){
                            monitor.enterReader();
                        }
                        else{
                            monitor.enterWriter();
                        }
                    }
                }catch (InterruptedException e){
                    throw new InterruptedException();
                }
                
                
                // TODO: leaving c.s.
                // tip: rethrow exceptions so they are handled by catch block below

                if (ball.isLeavingCs()){
                    if (ball.getRole() == Role.READER){
                        monitor.exitReader();
                    }
                    else
                    {
                        monitor.exitWriter();
                    }
                }
                
                Thread.sleep(ball.getSpeed());
                
            } catch (InterruptedException ex) {
                // handle exceptions both inside and outside of critical section
                // and for all possible roles of a ball
                
                // TODO by you!
                Thread.currentThread().interrupt();
                Thread.currentThread().interrupt();
            }
        }
    }
}

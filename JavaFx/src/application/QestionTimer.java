package application;
import java.util.Timer;
import java.util.TimerTask;

public class QestionTimer extends TimerTask {
	public boolean timerDone = false;
	public static int i = 0; 
    public void run() 
    { 
        System.out.println("Timer ran " + ++i);
        
    } 
    
    public boolean isTimerDone() {
    	return this.timerDone;
    }
    
    public static void main(String[] args) 
    { 
          
        Timer timer = new Timer(); 
        TimerTask task = new QestionTimer(); 
        timer.schedule(task, 0, 1000); 
          
    } 
}

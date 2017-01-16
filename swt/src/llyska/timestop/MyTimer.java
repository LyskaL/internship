package llyska.timestop;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	Timer timer;

    public MyTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}
    
    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel(); 
        }
    }

	public static void main(String[] args) {
		new MyTimer(2);
		 System.out.println("Task scheduled.");
	}

}

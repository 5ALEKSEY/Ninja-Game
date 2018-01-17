/**
 * The class needed to create a thread in which contain the timer
 */
public class TimerCount implements Runnable {
    /**
     * The main function of the flow for incrementing the timer counter
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                GameProcess.timercount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

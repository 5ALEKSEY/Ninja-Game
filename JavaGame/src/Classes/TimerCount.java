public class TimerCount implements Runnable{
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                GameProcess.timercount++;
                System.out.println(GameProcess.timercount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package respectful.rapist.client.util;

public class Timer {
    private long lastTime;

    public Timer() {
        lastTime = System.currentTimeMillis();
    }

    public boolean elapsed(long millis) {
        return (System.currentTimeMillis() - lastTime) >= millis;
    }
}

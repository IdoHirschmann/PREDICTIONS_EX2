package termination;

import java.io.Serializable;

public class Termination implements Serializable {
    private final Integer ticks;
    private final Integer seconds;

    public Termination(Integer ticks, Integer seconds) {
        this.ticks = ticks;
        this.seconds = seconds;
    }

    public Integer getTicks() {
        return ticks;
    }

    public Integer getSeconds() {
        return seconds;
    }
}

package ex3DTO;

public class TerminationTicksSecondsDTO {
    private Integer ticks;
    private Integer seconds;

    public TerminationTicksSecondsDTO(Integer ticks, Integer seconds) {
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

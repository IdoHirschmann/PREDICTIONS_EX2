package option2;

public class TerminationDTO {
    private Integer ticks;
    private Integer seconds;

    public TerminationDTO(Integer ticks, Integer seconds) {
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

package ex3DTO;

public class RequestDTO {
    private String userName;
    private String simulationName;
    private Integer simulationAmount;
    private String terminationType;
    private Integer id;
    private String requestStatus;
    private Integer runningCount;
    private Integer endedCount;
    private Integer ticks;
    private Integer seconds;

    public RequestDTO(String userName, String simulationName, Integer simulationAmount, String terminationType, Integer id, String requestStatus, Integer runningCount, Integer endedCount, Integer ticks, Integer seconds) {
        this.userName = userName;
        this.simulationName = simulationName;
        this.simulationAmount = simulationAmount;
        this.terminationType = terminationType;
        this.id = id;
        this.requestStatus = requestStatus;
        this.runningCount = runningCount;
        this.endedCount = endedCount;
        this.ticks = ticks;
        this.seconds = seconds;
    }

    public Integer getTicks() {
        return ticks;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public String getUserName() {
        return userName;
    }

    public String getSimulationName() {
        return simulationName;
    }

    public Integer getSimulationAmount() {
        return simulationAmount;
    }

    public String getTerminationType() {
        return terminationType;
    }

    public Integer getId() {
        return id;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public Integer getRunningCount() {
        return runningCount;
    }

    public Integer getEndedCount() {
        return endedCount;
    }
}

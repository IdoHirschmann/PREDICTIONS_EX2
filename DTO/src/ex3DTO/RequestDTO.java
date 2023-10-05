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

    public RequestDTO(String userName, String simulationName, Integer simulationAmount, String terminationType, Integer id, String requestStatus, Integer runningCount, Integer endedCount) {
        this.userName = userName;
        this.simulationName = simulationName;
        this.simulationAmount = simulationAmount;
        this.terminationType = terminationType;
        this.id = id;
        this.requestStatus = requestStatus;
        this.runningCount = runningCount;
        this.endedCount = endedCount;
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

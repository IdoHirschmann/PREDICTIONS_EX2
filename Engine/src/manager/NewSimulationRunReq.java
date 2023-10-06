package manager;

import ex3DTO.NewRequestDTO;
import ex3DTO.TerminationTicksSecondsDTO;

public class NewSimulationRunReq {
    private String userName;
    private String simulationName;
    private Integer simulationAmount;
    private Boolean isByUser;
    private TerminationTicksSecondsDTO terminationTicksSecondsDTO;
    private RequestStatus requestStatus;
    private Integer endingCount = 0;
    private Integer runningCount = 0;
    private Integer id;

    public NewSimulationRunReq(String userName, String simulationName, Integer simulationAmount, Boolean isByUser, TerminationTicksSecondsDTO terminationTicksSecondsDTO, Integer id) {
        this.userName = userName;
        this.simulationName = simulationName;
        this.simulationAmount = simulationAmount;
        this.isByUser = isByUser;
        this.terminationTicksSecondsDTO = terminationTicksSecondsDTO;
        this.id = id;
        requestStatus = RequestStatus.APPROVED;
        //todo - chane to PENDING after testing
    }

    public NewSimulationRunReq(NewRequestDTO newRequestDTO , Integer id){
        userName = newRequestDTO.getUserName();
        simulationName = newRequestDTO.getSimulationName();
        simulationAmount = newRequestDTO.getSimulationAmount();
        isByUser = newRequestDTO.getByUser();
        terminationTicksSecondsDTO = newRequestDTO.getTerminationTicksSecondsDTO();
        requestStatus = RequestStatus.PENDING;
        this.id = id;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Integer getEndingCount() {
        return endingCount;
    }

    public Integer getRunningCount() {
        return runningCount;
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

    public Boolean getByUser() {
        return isByUser;
    }

    public Integer getId() {
        return id;
    }

    public void decline(){
        requestStatus = RequestStatus.DECLINE;
    }


    public void approve(){
        requestStatus = RequestStatus.APPROVED;
    }
    public TerminationTicksSecondsDTO getTerminationTicksSecondsDTO() {
        return terminationTicksSecondsDTO;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSimulationName(String simulationName) {
        this.simulationName = simulationName;
    }

    public void setSimulationAmount(Integer simulationAmount) {
        this.simulationAmount = simulationAmount;
    }

    public void setByUser(Boolean byUser) {
        isByUser = byUser;
    }

    public void setTerminationTicksSecondsDTO(TerminationTicksSecondsDTO terminationTicksSecondsDTO) {
        this.terminationTicksSecondsDTO = terminationTicksSecondsDTO;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setEndingCount(Integer endingCount) {
        this.endingCount = endingCount;
    }

    public void setRunningCount(Integer runningCount) {
        this.runningCount = runningCount;
    }
}

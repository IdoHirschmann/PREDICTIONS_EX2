package manager;

import ex3DTO.NewRequestDTO;
import ex3DTO.TerminationTicksSecondsDTO;

public class NewSimulationRunReq {

    private String userName;
    private String simulationName;
    private Integer simulationAmount;
    private Boolean isByUser;
    private TerminationTicksSecondsDTO terminationTicksSecondsDTO;

    public NewSimulationRunReq(String userName, String simulationName, Integer simulationAmount, Boolean isByUser, TerminationTicksSecondsDTO terminationTicksSecondsDTO) {
        this.userName = userName;
        this.simulationName = simulationName;
        this.simulationAmount = simulationAmount;
        this.isByUser = isByUser;
        this.terminationTicksSecondsDTO = terminationTicksSecondsDTO;
    }

    public NewSimulationRunReq(NewRequestDTO newRequestDTO){
        userName = null; //todo- after add username to dto add it here
        simulationName = newRequestDTO.getSimulationName();
        simulationAmount = newRequestDTO.getSimulationAmount();
        isByUser = newRequestDTO.getByUser();
        terminationTicksSecondsDTO = newRequestDTO.getTerminationTicksSecondsDTO();
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
}

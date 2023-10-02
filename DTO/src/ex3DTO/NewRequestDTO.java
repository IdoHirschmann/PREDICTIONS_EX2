package ex3DTO;

import option2.TerminationDTO;

public class NewRequestDTO {
    private String userName;
    private String simulationName;
    private Integer simulationAmount;
    private Boolean isByUser;
    private TerminationTicksSecondsDTO terminationTicksSecondsDTO;

    public NewRequestDTO(String userName,String simulationName, Integer simulationAmount, Boolean isByUser, TerminationTicksSecondsDTO terminationTicksSecondsDTO) {
        this.userName = userName;
        this.simulationName = simulationName;
        this.simulationAmount = simulationAmount;
        this.isByUser = isByUser;
        this.terminationTicksSecondsDTO = terminationTicksSecondsDTO;
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
}

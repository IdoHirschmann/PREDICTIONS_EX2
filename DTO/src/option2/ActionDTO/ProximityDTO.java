package option2.ActionDTO;

public class ProximityDTO{

    private String depthOfEnvironment;
    private String numOfActionsForMeetsBetweenEntities;

    public ProximityDTO(String depthOfEnvironment, String numOfActionsForMeetsBetweenEntities) {
        this.depthOfEnvironment = depthOfEnvironment;
        this.numOfActionsForMeetsBetweenEntities = numOfActionsForMeetsBetweenEntities;
    }


    public String getDepthOfEnvironment() {
        return depthOfEnvironment;
    }

    public String getNumOfActionsForMeetsBetweenEntities() {
        return numOfActionsForMeetsBetweenEntities;
    }

}

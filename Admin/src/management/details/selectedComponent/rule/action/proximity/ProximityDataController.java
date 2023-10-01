package management.details.selectedComponent.rule.action.proximity;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.ProximityDTO;

public class ProximityDataController {

    @FXML
    private Label actionCount;
    @FXML
    private Label environmentDepth;
    @FXML
    private Label sourceEntityName;
    @FXML
    private Label targetEntityName;


    public void SetData(ActionDTO proximityDTO) {
        actionCount.setText(proximityDTO.getProximityDTO().getNumOfActionsForMeetsBetweenEntities());
        environmentDepth.setText(proximityDTO.getProximityDTO().getDepthOfEnvironment());
        sourceEntityName.setText(proximityDTO.getMainEntityName());

        if(proximityDTO.getSecondaryEntityName() == null) {
            targetEntityName.setText("None");
        }
        else {
            targetEntityName.setText(proximityDTO.getSecondaryEntityName());
        }
    }
}

package management.details.selectedComponent.rule.action.increase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.IncreaseDTO;

public class IncreaseDataController {

    @FXML
    private Label by;

    @FXML
    private Label mainEntityName;

    @FXML
    private Label property;

    @FXML
    private Label secondEntityName;

    public void SetData(ActionDTO increaseDTO) {
        by.setText(increaseDTO.getIncreaseDTO().getBy());
        property.setText(increaseDTO.getIncreaseDTO().getProperty());
        mainEntityName.setText(increaseDTO.getMainEntityName());

        if(increaseDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(increaseDTO.getSecondaryEntityName());
        }
    }
}
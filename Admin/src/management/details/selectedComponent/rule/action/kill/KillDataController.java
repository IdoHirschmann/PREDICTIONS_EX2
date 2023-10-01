package management.details.selectedComponent.rule.action.kill;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.KillDTO;

public class KillDataController {
    @FXML
    private Label mainEntityName;
    @FXML
    private Label secondEntityName;

    public void SetData(ActionDTO killDTO) {
        mainEntityName.setText(killDTO.getMainEntityName());

        if(killDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(killDTO.getSecondaryEntityName());
        }
    }

}
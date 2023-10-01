package management.details.selectedComponent.rule.action.set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.SetDTO;

public class SetDataController {

    @FXML
    private Label mainEntityName;
    @FXML
    private Label property;
    @FXML
    private Label secondEntityName;
    @FXML
    private Label value;


    public void SetData(ActionDTO setDTO) {
        mainEntityName.setText(setDTO.getMainEntityName());
        property.setText(setDTO.getSetDTO().getProperty());
        value.setText(setDTO.getSetDTO().getValue());

        if(setDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(setDTO.getSecondaryEntityName());
        }
    }

}

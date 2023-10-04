package details.selectedComponent.rule.action.decrease;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;

public class DecreaseDataController {

    @FXML
    private Label by;
    @FXML
    private Label mainEntityName;
    @FXML
    private Label property;
    @FXML
    private Label secondEntityName;


    public void SetData(ActionDTO decreaseDTO) {
        by.setText(decreaseDTO.getDecreaseDTO().getBy());
        property.setText(decreaseDTO.getDecreaseDTO().getProperty());
        mainEntityName.setText(decreaseDTO.getMainEntityName());

        if(decreaseDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(decreaseDTO.getSecondaryEntityName());
        }
    }
}
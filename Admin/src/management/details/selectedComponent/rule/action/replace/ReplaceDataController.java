package management.details.selectedComponent.rule.action.replace;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.ReplaceDTO;

public class ReplaceDataController {

    @FXML
    private Label createEntityName;
    @FXML
    private Label killEntityName;
    @FXML
    private Label mode;

    public void SetData(ActionDTO replaceDTO) {
        mode.setText(replaceDTO.getReplaceDTO().getMode());
        killEntityName.setText(replaceDTO.getMainEntityName());

        if(replaceDTO.getSecondaryEntityName() == null) {
            createEntityName.setText("None");
        }
        else {
            createEntityName.setText(replaceDTO.getSecondaryEntityName());
        }
    }
}
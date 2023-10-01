package management.details.selectedComponent.rule.action.multipleCondition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.MultipleConditionDTO;

public class MultipleConditionDataController {


    @FXML
    private Label elseCount;

    @FXML
    private Label logical;

    @FXML
    private Label mainEntityName;

    @FXML
    private Label secondEntityName;

    @FXML
    private Label thisCount;

    public void SetData(ActionDTO multipleConditionDTO) {
        if(multipleConditionDTO.getMultipleConditionDTO().getElseActionAmount() == null) {
            elseCount.setText(" --- ");
        }
        else {
            elseCount.setText(Integer.toString(multipleConditionDTO.getMultipleConditionDTO().getElseActionAmount()));
        }
        logical.setText(multipleConditionDTO.getMultipleConditionDTO().getLogic());
        mainEntityName.setText(multipleConditionDTO.getMainEntityName());
        if(multipleConditionDTO.getMultipleConditionDTO().getThisActionAmount() == null) {
            thisCount.setText(" --- ");
        }
        else {
            thisCount.setText(Integer.toString(multipleConditionDTO.getMultipleConditionDTO().getThisActionAmount()));
        }

        if(multipleConditionDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(multipleConditionDTO.getSecondaryEntityName());
        }
    }

}

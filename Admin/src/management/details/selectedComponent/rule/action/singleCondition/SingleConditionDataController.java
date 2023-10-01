package management.details.selectedComponent.rule.action.singleCondition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.SingleConditionDTO;


public class SingleConditionDataController {

    @FXML
    private Label mainEntityName;
    @FXML
    private Label operator;
    @FXML
    private Label property;
    @FXML
    private Label secondEntityName;
    @FXML
    private Label value;
    @FXML
    private Label elseCount;
    @FXML
    private Label thisCount;

    public void SetData(ActionDTO singleConditionDTO) {
        if(singleConditionDTO.getSingleConditionDTO().getElseActionAmount() == null) {
            elseCount.setText(" --- ");
        }
        else {
            elseCount.setText(Integer.toString(singleConditionDTO.getSingleConditionDTO().getElseActionAmount()));
        }
        operator.setText(singleConditionDTO.getSingleConditionDTO().getOperator());
        mainEntityName.setText(singleConditionDTO.getMainEntityName());

        if(singleConditionDTO.getSingleConditionDTO().getThisActionAmount() == null) {
            thisCount.setText(" --- ");
        }
        else {
            thisCount.setText(Integer.toString(singleConditionDTO.getMultipleConditionDTO().getThisActionAmount()));
        }
        property.setText(singleConditionDTO.getSingleConditionDTO().getProperty());
        value.setText(singleConditionDTO.getSingleConditionDTO().getValue());

        if(singleConditionDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(singleConditionDTO.getSecondaryEntityName());
        }
    }
}
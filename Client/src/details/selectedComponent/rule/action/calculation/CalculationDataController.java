package details.selectedComponent.rule.action.calculation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import option2.ActionDTO.ActionDTO;

public class CalculationDataController {

    @FXML
    private Label actionName;
    @FXML
    private Label arg1;
    @FXML
    private Label arg2;
    @FXML
    private Label mainEntityName;
    @FXML
    private Label reasultProp;
    @FXML
    private Label secondEntityName;


    public void SetData(ActionDTO calculationDTO) {
        actionName.setText(calculationDTO.getName());
        arg1.setText(calculationDTO.getCalculationDTO().getArg1());
        arg2.setText(calculationDTO.getCalculationDTO().getArg2());
        mainEntityName.setText(calculationDTO.getMainEntityName());
        reasultProp.setText(calculationDTO.getCalculationDTO().getResultProp());

        if(calculationDTO.getSecondaryEntityName() == null) {
            secondEntityName.setText("None");
        }
        else {
            secondEntityName.setText(calculationDTO.getSecondaryEntityName());
        }
    }

}

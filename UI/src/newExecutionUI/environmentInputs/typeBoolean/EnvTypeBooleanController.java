package newExecutionUI.environmentInputs.typeBoolean;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import newExecutionUI.NewExecutionController;
import newExecutionUI.listener.ClearButtonClickedListener;
import newExecutionUI.listener.RerunButtonClickedListener;
import newExecutionUI.listener.StartButtonClickedListener;
import option3.EnvironmentDefinitionDTO;
import option3.EnvironmentInitDTO;

import java.util.Random;

public class EnvTypeBooleanController implements StartButtonClickedListener, ClearButtonClickedListener, RerunButtonClickedListener {

    @FXML
    private Label nameLabel;
    @FXML
    private ComboBox<String> userChoice;
    private NewExecutionController newExecutionController;
    private Boolean isValueSet = false;


    @FXML
    private void initialize() {
        userChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                isValueSet = true;
            }
        });
    }
    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
        newExecutionController.addListenerToStartButton(this);
        newExecutionController.addListenerToClearButton(this);
    }

    @Override
    public void startOnClicked() {
        String value;
        if(isValueSet) {
            value = userChoice.getValue();
        } else {
            Random random = new Random();
            Boolean randomBoolean = random.nextBoolean();
            value = randomBoolean.toString();
        }

        newExecutionController.addEnvironmentToList(new EnvironmentInitDTO(nameLabel.getText(), value));
    }

    @Override
    public void clearOnClicked() {
        userChoice.setValue(null);
        isValueSet = false;
    }

    public void setData(EnvironmentDefinitionDTO environmentDefinitionDTO) {
        nameLabel.setText(environmentDefinitionDTO.getName());
    }

    @Override
    public void onRerun(String startValue) {
        userChoice.setValue(startValue);
    }
}
package newExecution.environmentInputs.typeNumber;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import newExecution.NewExecutionController;
import newExecution.listener.StartButtonClickedListener;
import option3.EnvironmentDefinitionDTO;
import option3.EnvironmentInitDTO;

import java.util.Random;

public class EnvTypeNumberController implements StartButtonClickedListener {

    @FXML
    private Label nameLabel;
    @FXML
    private Label rangeLabel;
    @FXML
    private Spinner<Double> userChoice;
    private NewExecutionController newExecutionController;
    private Boolean isValueSet;
    private Double from = new Double(0);
    private Double to = new Double(1000);

    @FXML
    public void initialize() {
        isValueSet = false;

        userChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null){
                isValueSet = true;
            }
        });
    }

    public void setNewExecutionController(NewExecutionController newExecutionController) {
        this.newExecutionController = newExecutionController;
        newExecutionController.addListenerToStartButton(this);
    }

    @Override
    public void startOnClicked() {
        String value;
        if(isValueSet) {
            value = userChoice.getValue().toString();
        } else {
            Random random = new Random();
            Integer randomInteger = random.nextInt(to.intValue() - from.intValue()) + from.intValue();
            value = randomInteger.toString();
        }

        newExecutionController.addEnvironmentToList(new EnvironmentInitDTO(nameLabel.getText(), value));
    }

    public void setData(EnvironmentDefinitionDTO environmentDefinitionDTO) {
        nameLabel.setText(environmentDefinitionDTO.getName());

        userChoice.setEditable(true);

        if(environmentDefinitionDTO.getRangeTo() != null) {
            rangeLabel.setText(environmentDefinitionDTO.getRangeFrom() + " - " + environmentDefinitionDTO.getRangeTo());
            SpinnerValueFactory<Double> range = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.parseDouble(environmentDefinitionDTO.getRangeFrom()),
                    Double.parseDouble(environmentDefinitionDTO.getRangeTo()), Double.parseDouble(environmentDefinitionDTO.getRangeFrom()));
            userChoice.setValueFactory(range);
            from = Double.parseDouble(environmentDefinitionDTO.getRangeFrom());
            to = Double.parseDouble(environmentDefinitionDTO.getRangeTo());
        }
        else {
            rangeLabel.setText(" --- ");
        }
    }
}
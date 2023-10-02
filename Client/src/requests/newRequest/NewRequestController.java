package requests.newRequest;

import ex3DTO.NewRequestDTO;
import ex3DTO.TerminationTicksSecondsDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewRequestController {

    @FXML
    private CheckBox secondsCheckBox;
    @FXML
    private Spinner<Integer> secondsSpinner;
    @FXML
    private Spinner<Integer> simulationAmountSpinner;
    @FXML
    private Button submitButton;
    @FXML
    private CheckBox ticksCheckBox;
    @FXML
    private CheckBox ticksSecondsCheckBox;
    @FXML
    private Spinner<Integer> ticksSpinner;
    @FXML
    private CheckBox userCheckBox;
    @FXML
    private ChoiceBox<String> simulationChoiceBox;


    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        secondsSpinner.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        simulationAmountSpinner.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        ticksSpinner.setValueFactory(valueFactory);

        isSubmitLegal();

        //todo - need to get for the simulationChoiceBox all the simulations in the engine
    }

    @FXML
    void ticksSecondsCheckBoxClicked(ActionEvent event) {
        if(ticksSecondsCheckBox.isSelected()) {
            userCheckBox.setVisible(false);
            ticksCheckBox.setVisible(true);
            secondsCheckBox.setVisible(true);
        }
        else {
            userCheckBox.setVisible(true);
            ticksCheckBox.setVisible(false);
            ticksSpinner.setVisible(false);
            secondsCheckBox.setVisible(false);
            secondsSpinner.setVisible(false);

            ticksCheckBox.setSelected(false);
            secondsCheckBox.setSelected(false);
        }
        isSubmitLegal();
    }
    @FXML
    void userCheckBoxClicked(ActionEvent event) {
        if(!userCheckBox.isSelected()) {
            ticksSecondsCheckBox.setVisible(true);
        }
        else {
            ticksSecondsCheckBox.setVisible(false);
            ticksCheckBox.setVisible(false);
            ticksSpinner.setVisible(false);
            secondsCheckBox.setVisible(false);
            secondsSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void secondsCheckBoxClicked(ActionEvent event) {
        if(secondsCheckBox.isSelected()){
            secondsSpinner.setVisible(true);
        }
        else {
            secondsSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void ticksCheckBoxClicked(ActionEvent event) {
        if(ticksCheckBox.isSelected()) {
            ticksSpinner.setVisible(true);
        }
        else {
            ticksSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void submitButtonClicked(ActionEvent event) {
        NewRequestDTO newRequestDTO;
        String name = simulationChoiceBox.getValue();
        Integer count = simulationAmountSpinner.getValue();
        Integer ticks = ticksSpinner.isVisible() ? ticksSpinner.getValue() : null;
        Integer second = secondsSpinner.isVisible() ? secondsSpinner.getValue() : null;

        if(userCheckBox.isSelected()) {
            newRequestDTO = new NewRequestDTO(name, count, true, null);
        }
        else {
            newRequestDTO = new NewRequestDTO(name, count, false, new TerminationTicksSecondsDTO(ticks, second));
        }

        //todo - submit the request to the engine
    }

    private void isSubmitLegal() {
        if(simulationChoiceBox.getValue() == null) {
            submitButton.setDisable(true);
        }
        else if(!ticksSecondsCheckBox.isSelected() && !userCheckBox.isSelected()) {
            submitButton.setDisable(true);
        }
        else if(ticksSecondsCheckBox.isSelected() && !ticksCheckBox.isSelected() && !secondsCheckBox.isSelected()) {
            submitButton.setDisable(true);
        }
        else {
            submitButton.setDisable(false);
        }
    }
}
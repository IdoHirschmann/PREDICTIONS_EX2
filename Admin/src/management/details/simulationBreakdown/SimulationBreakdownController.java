package management.details.simulationBreakdown;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class SimulationBreakdownController {
    @FXML
    private ChoiceBox<String> choiceBoxEntites;
    @FXML
    private ChoiceBox<String> choiceBoxEnvironment;
    @FXML
    private ChoiceBox<String> choiceBoxRules;
    @FXML
    private ChoiceBox<String> choiceBoxSimulations;
    @FXML
    private Button chooseSimulationButton;
    @FXML
    private VBox chooseSimulationVBox;
    @FXML
    private Button showEntities;
    @FXML
    private Button showEnvironment;
    @FXML
    private Button showGrid;
    @FXML
    private Button showRules;
    @FXML
    private Button showSimulation;
    @FXML
    private VBox simulationBreakdownVBox;
    @FXML
    void EntitiesButtonClicked(ActionEvent event) {
        String entityName = choiceBoxEntites.getSelectionModel().getSelectedItem();
        if(entityName != null) {
            //todo
        }
    }
    @FXML
    void EnvironmentButtonClicked(ActionEvent event) {
        String environmentName = choiceBoxEnvironment.getSelectionModel().getSelectedItem();
        if(environmentName != null) {
            //todo
        }
    }
    @FXML
    void GridButtonClicked(ActionEvent event) {
        //todo
    }
    @FXML
    void RulesButtonClicked(ActionEvent event) {
        String ruleName = choiceBoxRules.getSelectionModel().getSelectedItem();
        if(ruleName != null) {
            //todo
        }
    }
    @FXML
    void chooseSimulationButtonClicked(ActionEvent event) {
        //todo
        chooseSimulationVBox.setVisible(true);
        simulationBreakdownVBox.setVisible(false);
        choiceBoxEntites.setValue(null);
        choiceBoxRules.setValue(null);
        choiceBoxEnvironment.setValue(null);
    }

    @FXML
    void simulationsButtonClicked(ActionEvent event) {
        String simulationName = choiceBoxSimulations.getSelectionModel().getSelectedItem();
        if(simulationName != null) {
            //todo
            chooseSimulationVBox.setVisible(false);
            simulationBreakdownVBox.setVisible(true);
            choiceBoxSimulations.setValue(null);
        }
    }
}

package management.details.simulationBreakdown;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import management.details.DetailsController;
import option2.EntityDefinitionDTO;
import option2.PropertyDefinitionDTO;
import option2.RulesDTO;
import option2.SimulationDefinitionDTO;

import java.util.List;
import java.util.Optional;

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
    private DetailsController detailsScreenController;
    private SimulationDefinitionDTO simulationDefinitionDTO;


    public void setDetailsScreenController(DetailsController detailsScreenController) {
        this.detailsScreenController = detailsScreenController;
    }

    @FXML
    private void EntitiesButtonClicked(ActionEvent event) {
        String newValue = choiceBoxEntites.getSelectionModel().getSelectedItem();
        if (newValue != null) {
            Optional<EntityDefinitionDTO> entityDefinitionDTO = simulationDefinitionDTO.getEntityDefinitionDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.entitiesShowButtonClicked(entityDefinitionDTO.get());
        }
    }
    @FXML
    private void RulesButtonClicked(ActionEvent event) {
        String newValue = choiceBoxRules.getSelectionModel().getSelectedItem();
        if(newValue != null) {
            Optional<RulesDTO> rulesDTO = simulationDefinitionDTO.getRulesDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.rulesShowButtonClicked(rulesDTO.get());
        }
    }
    @FXML
    private void EnvironmentButtonClicked(ActionEvent event) {
        String newValue = choiceBoxEnvironment.getSelectionModel().getSelectedItem();
        if (newValue != null) {
            Optional<PropertyDefinitionDTO> environmentDefinitionDTO = simulationDefinitionDTO.getEnvironmentDefenitionDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.environmentShowButtonClicked(environmentDefinitionDTO.get());
        }
    }

    @FXML
    private void GridButtonClicked(ActionEvent event) {
        detailsScreenController.gridShowButtonClicked(simulationDefinitionDTO.getGridRows(), simulationDefinitionDTO.getGridCols());
    }

    public void initializeDetailsData(SimulationDefinitionDTO simulationDefinitionDTO) {
        this.simulationDefinitionDTO = simulationDefinitionDTO;
        initializeEntities(simulationDefinitionDTO.getEntityDefinitionDTOList());
        initializeRules(simulationDefinitionDTO.getRulesDTOList());
        initializeEnvironments(simulationDefinitionDTO.getEnvironmentDefenitionDTOList());
    }
    private void initializeRules(List<RulesDTO> rulesDTOList) {
        for(RulesDTO rulesDTO : rulesDTOList) {
            choiceBoxRules.getItems().add(rulesDTO.getName());
        }
    }
    private void initializeEntities(List<EntityDefinitionDTO> entityDefinitionDTOList) {
        for(EntityDefinitionDTO entityDefinitionDTO : entityDefinitionDTOList) {
            choiceBoxEntites.getItems().add(entityDefinitionDTO.getName());
        }
    }
    private void initializeEnvironments(List<PropertyDefinitionDTO> environmentDefenitionDTOList) {
        for(PropertyDefinitionDTO environmentDefinitionDTO : environmentDefenitionDTOList) {
            choiceBoxEnvironment.getItems().add(environmentDefinitionDTO.getName());
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

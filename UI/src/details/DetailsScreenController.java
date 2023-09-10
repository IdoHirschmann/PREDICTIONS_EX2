package details;

import details.selectedComponent.entity.EntityDetailsController;
import details.selectedComponent.rule.RulesDetailsController;
import details.selectedComponent.termination.TerminationDetailsController;
import details.simulationBreakdown.SimulationBreakdownController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import managerFX.MainScreenController;
import option2.EntityDefinitionDTO;
import option2.RulesDTO;
import option2.SimulationDefinitionDTO;
import option2.TerminationDTO;

import java.io.IOException;

public class DetailsScreenController {
    @FXML
    private SplitPane simulationBreakdown;
    @FXML
    private SimulationBreakdownController simulationBreakdownController;
    @FXML
    private SplitPane root;
    private MainScreenController mainScreenController;
    private Double originalDividerPosition;

    @FXML
    public void initialize() {
        simulationBreakdownController.setDetailsScreenController(this);
        originalDividerPosition = 0.3832752613240418; // Store your original value here
        setDivider();
    }


    private void setDivider(){
        root.setDividerPositions(originalDividerPosition);

        // Add a listener to the divider position
        root.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            // Reset the position to the original value
            root.setDividerPositions(originalDividerPosition);
        });
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void entitiesShowButtonClicked(EntityDefinitionDTO EntityData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/entity/EntityDetails.fxml"));
            Parent entityContent = loader.load();
            EntityDetailsController entityDetailsController = loader.getController();
            root.getItems().set(1, entityContent);

            entityDetailsController.setAllDataMembers(EntityData);

        } catch (IOException e) {
        }
    }

    public void rulesShowButtonClicked(RulesDTO rulesDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/rule/RulesDetails.fxml"));
            Parent ruleContent = loader.load();
            RulesDetailsController rulesDetailsController = loader.getController();
            root.getItems().set(1, ruleContent);

            rulesDetailsController.setAllDataMembers(rulesDTO);

        } catch (IOException e) {
        }
    }

    public void terminationShowButtonClicked(TerminationDTO terminationDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/termination/TerminationDetails.fxml"));
            Parent terminationContent = loader.load();
            TerminationDetailsController terminationDetailsController = loader.getController();
            root.getItems().set(1, terminationContent);

            terminationDetailsController.setAllDataMembers(terminationDTO);

        } catch (IOException e) {
        }
    }

    public void initializeDetailsData(SimulationDefinitionDTO simulationDefinitionDTO) {
        simulationBreakdownController.initializeDetailsData(simulationDefinitionDTO);
    }

}

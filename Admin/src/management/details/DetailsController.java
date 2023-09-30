package management.details;

import ex3DTO.SimulationNameDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import management.details.selectedComponent.entity.EntityDetailsController;
import management.details.selectedComponent.environment.EnvironmentDetailsController;
import management.details.selectedComponent.grid.GridDetailsController;
import management.details.selectedComponent.rule.RulesDetailsController;
import management.details.simulationBreakdown.SimulationBreakdownController;
import option2.*;

import java.io.IOException;
import java.util.List;

public class DetailsController {

    @FXML
    private Pane selectedComponentPane;
    @FXML
    private StackPane simulationBreakdown;
    @FXML
    private SimulationBreakdownController simulationBreakdownController;

    public void entitiesShowButtonClicked(EntityDefinitionDTO EntityData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/entity/EntityDetails.fxml"));
            Parent entityContent = loader.load();
            EntityDetailsController entityDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(entityContent);

            entityDetailsController.setAllDataMembers(EntityData);

        } catch (IOException e) {
        }
    }

    public void environmentShowButtonClicked(PropertyDefinitionDTO EnvironmentData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/environment/EnvironmentDetails.fxml"));
            Parent environmentContent = loader.load();
            EnvironmentDetailsController environmentDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(environmentContent);

            environmentDetailsController.setData(EnvironmentData);

        } catch (IOException e) {
        }
    }


    public void rulesShowButtonClicked(RulesDTO rulesDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/RulesDetails.fxml"));
            Parent ruleContent = loader.load();
            RulesDetailsController rulesDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(ruleContent);

            rulesDetailsController.setAllDataMembers(rulesDTO);

        } catch (IOException e) {
        }
    }

    public void gridShowButtonClicked(Integer rows, Integer cols) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/grid/GridDetails.fxml"));
            Parent gridContent = loader.load();
            GridDetailsController gridDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(gridContent);

            gridDetailsController.setData(rows.toString(), cols.toString());

        } catch (IOException e) {
        }
    }


    public void initializeDetailsData(List<SimulationNameDTO> simulationNameDTOS) {
        simulationBreakdownController.initializeDetailsData(simulationNameDTOS);
    }
}
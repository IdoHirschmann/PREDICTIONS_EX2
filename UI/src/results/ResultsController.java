package results;

import ex2DTO.SimulationIDDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import manager.LoadedFileManager;
import managerFX.MainScreenController;
import option4.PastSimulationInfoDTO;
import results.simulationDetails.SimulationDetailsController;
import results.simulationResult.SimulationResultController;
import results.simulations.SimulationsController;
import results.simulations.listener.ShowButtonListener;

import java.io.IOException;
import java.util.List;

public class ResultsController implements ShowButtonListener {

    @FXML
    private Pane simulationDetails;
    @FXML
    private Pane simulationResult;
    @FXML
    private ScrollPane simulationsList;
    @FXML
    private HBox resultsMainHbox;
    @FXML
    private SimulationsController simulationsListController;
    private MainScreenController mainScreenController;
    private LoadedFileManager loadedFileManager;
    private SimulationDetailsController simulationDetailsController;

    @FXML
    public void initialize() {
        simulationsListController.setResultsController(this);
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void setPredictionManager(LoadedFileManager loadedFileManager) {
        this.loadedFileManager = loadedFileManager;
        this.simulationsListController.setPredictionManager(loadedFileManager);
        simulationsListController.manageSimulationsState();
    }
    public void setSimulationsList() {
        List<PastSimulationInfoDTO> pastSimulationInfoDTOList = loadedFileManager.createPastSimulationInfoDTOList();

        for(PastSimulationInfoDTO pastSimulationInfoDTO : pastSimulationInfoDTOList) {
            simulationsListController.addSimulation(pastSimulationInfoDTO);
        }
    }

    @Override
    public void onShowClicked(Integer id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/results/simulationDetails/SimulationDetails.fxml"));
            Parent simulationData = loader.load();
            SimulationDetailsController simulationDetailsController = loader.getController();
            simulationDetailsController.setId(id);
            simulationDetailsController.setPredictionManager(loadedFileManager);
            simulationDetailsController.setResultsController(this);
            simulationDetailsController.setValues();

            if(this.simulationDetailsController != null) {
                this.simulationDetailsController.stopThread();
                simulationDetails.getChildren().clear();
            }

            if(loadedFileManager.getSimulationState(new SimulationIDDTO(id)).getState().equals("STOPPED") ||
                    loadedFileManager.getSimulationState(new SimulationIDDTO(id)).getState().equals("FAILED")) {
                showResult(id);
                simulationDetailsController.simulationStop();
            }

            if(loadedFileManager.getSimulationState(new SimulationIDDTO(id)).getState().equals("PAUSED")) {
                simulationDetailsController.simulationPause();
            }

            this.simulationDetailsController = simulationDetailsController;
            mainScreenController.setSimulationDetailsController(simulationDetailsController);
            simulationDetails.getChildren().add(simulationData);
        } catch (IOException e) {
        }
    }
    public void tryToShowResult(Integer id) {
        if(simulationDetailsController != null) {
            if(simulationDetailsController.getId().equals(id)) {
                showResult(id);
            }
        }
    }
    public void showResult(Integer id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/results/simulationResult/SimulationResult.fxml"));
            Parent simulationData = loader.load();
            SimulationResultController simulationResultController = loader.getController();

            simulationResultController.setMainScreenController(mainScreenController);
            simulationResult.getChildren().add(simulationData);
            simulationResultController.setId(id);
            simulationResultController.setPredictionManager(loadedFileManager);

        } catch (IOException e) {
        }
    }
    public void setOnColorChange(String color) {
        resultsMainHbox.setStyle("-fx-background-color: " + color + ";");
//        simulationDetails.setStyle("-fx-background-color: " + color + ";");
//        simulationResult.setStyle("-fx-background-color: " + color + ";");
//        simulationsList.setStyle("-fx-background-color: " + color + ";");
    }
}
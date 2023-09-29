package mainPage;

import header.HeaderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import management.ManagementController;

import java.io.IOException;

public class MainPageController {
    @FXML
    private Pane bodyPane;
    @FXML
    private GridPane header;
    @FXML
    private HeaderController headerController;
    private ManagementController managementController;
    private Boolean isManagementPresent = false;
    private Boolean isAllocationsPresent = false;
    private Boolean isExecutionsHistoryPresent = false;

    @FXML
    public void initialize() {
        headerController.setMainPageController(this);
    }

    public void managementScreen() {
        try {
            if (!isManagementPresent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/Management.fxml"));
                Parent managementContent = loader.load();
                managementController = loader.getController();

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(managementContent);
                isManagementPresent = true;
                isAllocationsPresent = false;
                isExecutionsHistoryPresent = false;
            }
        } catch (IOException e) {
        }
    }

//    public void newExecutionScreen() {
//        try {
//            if (!isNewExecutionPresent) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/newExecution/NewExecution.fxml"));
//                Parent newExecutionContent = loader.load();
//                newExecutionController = loader.getController();
//
//                newExecutionController.setEntitiesData(loadedFileManager.showCurrentSimulationData().getEntityDefinitionDTOList(),
//                        loadedFileManager.showCurrentSimulationData().getGridCols() * loadedFileManager.showCurrentSimulationData().getGridRows());
//                newExecutionController.setPredictionManager(loadedFileManager);
//                newExecutionController.setMainScreenController(this);
//
//                mainBorderPane.setCenter(newExecutionContent);
//
//                EnvironmentDefinitionListDTO environmentDefinitionListDTO = loadedFileManager.runSimulationStep1();
//                newExecutionController.setEnvironmentData(environmentDefinitionListDTO);
//                newExecutionController.setOnColorChange(color);
//                if (simulationDetailsController != null) {
//                    simulationDetailsController.stopThread();
//                }
//                isNewExecutionPresent = true;
//                isDetailsPresent = false;
//                isResultsPresent = false;
//            }
//        } catch (IOException e) {
//        }
//    }
//
//    public void resultsScreen() {
//        try {
//            if (!isResultsPresent) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/results/Results.fxml"));
//                Parent resultsData = loader.load();
//                resultsController = loader.getController();
//
//                mainBorderPane.setCenter(resultsData);
//                resultsController.setMainScreenController(this);
//                resultsController.setPredictionManager(loadedFileManager);
//                resultsController.setSimulationsList();
//                resultsController.setOnColorChange(color);
//                isResultsPresent = true;
//                isDetailsPresent = false;
//                isNewExecutionPresent = false;
//            }
//        } catch (IOException e) {
//        }
//    }
}

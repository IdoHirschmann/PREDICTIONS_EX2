package managerFX;

import detailsUI.DetailsScreenController;
import header.PredictionHeaderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import manager.LoadedFileManager;
import managerFX.animation.ColorChangeAnimation;
import managerFX.animation.FadeAndSpinAnimation;
import newExecution.NewExecutionController;
import option2.SimulationDefinitionDTO;
import option3.EnvironmentDefinitionListDTO;
import results.ResultsController;
import results.simulationDetails.SimulationDetailsController;
import java.io.IOException;

public class MainScreenController {
    private LoadedFileManager loadedFileManager = new LoadedFileManager(null);
    @FXML
    private GridPane header;
    @FXML
    private PredictionHeaderController headerController;
    private DetailsScreenController detailsController;
    private NewExecutionController newExecutionController;
    private ResultsController resultsController;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ScrollPane scrollPane;
    private Boolean isDetailsPresent = false;
    private Boolean isNewExecutionPresent = false;
    private Boolean isResultsPresent = false;
    private SimulationDetailsController simulationDetailsController = null;
    private Thread thread;
    private String color = "#f4f4f4";
    private Scene scene;
    private StackPane rootStackPane;
    private Boolean isAnimationOn = false;
    private ColorChangeAnimation colorChangeAnimation;
    private FadeAndSpinAnimation fadeAndSpinAnimation;

    @FXML
    public void initialize() {
        headerController.setMainScreenController(this);
        headerController.setPredictionManager(loadedFileManager);

        StackPane stackPane = new StackPane(mainBorderPane);
        rootStackPane = stackPane;

        scrollPane.setContent(stackPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        simulationLoadManager();
        colorChangeAnimation = headerController.getColorAnimation();
        fadeAndSpinAnimation = headerController.getSpinAndFadeAnimation();
    }

    public void setAnimationOn(Boolean animationOn) {
        isAnimationOn = animationOn;

        if(animationOn) {
            colorChangeAnimation.start();
            fadeAndSpinAnimation.start();
        }
        else {
            colorChangeAnimation.stop();
            fadeAndSpinAnimation.stop();
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void simulationLoadManager() {
        Pane emptyPane = new Pane();
//        thread = new Thread(() -> {
//            while (true) {
//                try {
//                    IsNewSimLoadDTO isNewSimLoadDTO = loadedFileManager.isNewSimLoad();
//                    if (isNewSimLoadDTO.getNewSimLoad()) {
//                        Platform.runLater(() -> {
//                            mainBorderPane.setCenter(emptyPane);
//                            isDetailsPresent = false;
//                            isResultsPresent = false;
//                            isNewExecutionPresent = false;
//                        });
//                    }
//                    Thread.sleep(200);
//                } catch (InterruptedException ignore) {
//                }
//            }
//        });
//
//        thread.start();
        //todo - is really not for ex 3? ^
    }

    public void setSimulationDetailsController(SimulationDetailsController simulationDetailsController) {
        this.simulationDetailsController = simulationDetailsController;
    }

    public void loadDetailsScreen() {
        SimulationDefinitionDTO simulationDefinitionDTO = loadedFileManager.showCurrentSimulationData();
        try {
            if (!isDetailsPresent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/detailsUI/detailsScreen.fxml"));
                Parent detailsContent = loader.load();
                detailsController = loader.getController();

                detailsController.setMainScreenController(this);
                detailsController.initializeDetailsData(simulationDefinitionDTO);
                mainBorderPane.setCenter(detailsContent);
                detailsController.setOnColorChange(color);
                if (simulationDetailsController != null) {
                    simulationDetailsController.stopThread();
                }
                isDetailsPresent = true;
                isResultsPresent = false;
                isNewExecutionPresent = false;
            }
        } catch (IOException e) {
        }
    }

    public void newExecutionScreen() {
        try {
            if (!isNewExecutionPresent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/newExecution/NewExecution.fxml"));
                Parent newExecutionContent = loader.load();
                newExecutionController = loader.getController();

                newExecutionController.setEntitiesData(loadedFileManager.showCurrentSimulationData().getEntityDefinitionDTOList(),
                        loadedFileManager.showCurrentSimulationData().getGridCols() * loadedFileManager.showCurrentSimulationData().getGridRows());
                newExecutionController.setPredictionManager(loadedFileManager);
                newExecutionController.setMainScreenController(this);

                mainBorderPane.setCenter(newExecutionContent);

                EnvironmentDefinitionListDTO environmentDefinitionListDTO = loadedFileManager.runSimulationStep1();
                newExecutionController.setEnvironmentData(environmentDefinitionListDTO);
                newExecutionController.setOnColorChange(color);
                if (simulationDetailsController != null) {
                    simulationDetailsController.stopThread();
                }
                isNewExecutionPresent = true;
                isDetailsPresent = false;
                isResultsPresent = false;
            }
        } catch (IOException e) {
        }
    }

    public void resultsScreen() {
        try {
            if (!isResultsPresent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/results/Results.fxml"));
                Parent resultsData = loader.load();
                resultsController = loader.getController();

                mainBorderPane.setCenter(resultsData);
                resultsController.setMainScreenController(this);
                resultsController.setPredictionManager(loadedFileManager);
                resultsController.setSimulationsList();
                resultsController.setOnColorChange(color);
                isResultsPresent = true;
                isDetailsPresent = false;
                isNewExecutionPresent = false;
            }
        } catch (IOException e) {
        }
    }

    public void rerunClicked(Integer id) {
        newExecutionScreen();
        newExecutionController.onRerun(id);
    }

    public void setOnColorChange(String color) {
        this.color = color;
        scene.getStylesheets().clear();
        scene.getStylesheets().add("managerFX/MainScreenStyle.css");
        switch (color) {
            case "#EDF0F0":
                break;
            case "#D4E6F1":
                scene.getStylesheets().add("managerFX/lightBlueStyle.css");
                break;
            default:
                scene.getStylesheets().add("managerFX/pinkStyle.css");
                break;
        }

        mainBorderPane.setStyle("-fx-background-color: " + this.color + ";");
        rootStackPane.setStyle("-fx-background-color: " + this.color + ";");
        if (isResultsPresent) {
            resultsController.setOnColorChange(this.color);
        } else if (isDetailsPresent) {
            detailsController.setOnColorChange(this.color);
        } else if (isNewExecutionPresent) {
            newExecutionController.setOnColorChange(this.color);
        }
    }
}

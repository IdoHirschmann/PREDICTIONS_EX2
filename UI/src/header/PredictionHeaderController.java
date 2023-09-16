package header;

import header.queueInfo.QueueInfoController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.PredictionManager;
import managerFX.MainScreenController;
import option1.XmlFullPathDTO;

import java.io.File;

public class PredictionHeaderController {

    @FXML
    private Label currentFileLabel;
    @FXML
    private Button detailsButton;
    @FXML
    private Label headLineLable;
    @FXML
    private Button loadFileButton;
    @FXML
    private Button newExecutionButton;
    @FXML
    private Button resultsButton;
    @FXML
    private Button OKButton;
    @FXML
    private VBox queueInfo;
    @FXML
    private QueueInfoController queueInfoController;
    @FXML
    private Label failedLoadCause;


    private MainScreenController mainScreenController;

    private Parent root;

    private PredictionManager predictionManager;
    private BooleanProperty buttonsDisabled = new SimpleBooleanProperty(true);

    public void initialize() {
        detailsButton.disableProperty().bind(buttonsDisabledProperty());
        newExecutionButton.disableProperty().bind(buttonsDisabledProperty());
        resultsButton.disableProperty().bind(buttonsDisabledProperty());
        failedLoadCause.setVisible(false);
        OKButton.setVisible(false);
    }

    public void setPredictionManager(PredictionManager predictionManager) {
        this.predictionManager = predictionManager;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public BooleanProperty buttonsDisabledProperty() {
        return buttonsDisabled;
    }

    public void setButtonsDisabled(boolean buttonsDisabled) {
        this.buttonsDisabled.set(buttonsDisabled);
    }

    @FXML
    void loadFileButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        XmlFullPathDTO xmlFullPathDTO = new XmlFullPathDTO(selectedFile.getAbsolutePath());
        try {
            failedLoadCause.setVisible(false);
            predictionManager.loadXmlData(xmlFullPathDTO);
            currentFileLabel.setText(selectedFile.getAbsolutePath());
            queueInfoController.setPredictionManager(predictionManager);
            setButtonsDisabled(false);
        }catch (Exception exception) {
            newExecutionButton.setVisible(false);
            detailsButton.setVisible(false);
            resultsButton.setVisible(false);
            OKButton.setVisible(true);
            failedLoadCause.setText(exception.getMessage());
            failedLoadCause.setVisible(true);
            loadFileButton.setVisible(false);
        }
    }

    @FXML
    void detailsButtonClicked(ActionEvent event) {
        mainScreenController.loadDetailsScreen();
    }

    @FXML
    void newExecutionButtonClicked(ActionEvent event) {
        mainScreenController.newExecutionScreen();
    }
    @FXML
    void resultsButtonClicked(ActionEvent event) {
        mainScreenController.resultsScreen();
    }
    @FXML
    void OKButtonClicked(ActionEvent event) {
        newExecutionButton.setVisible(true);
        detailsButton.setVisible(true);
        resultsButton.setVisible(true);
        OKButton.setVisible(false);
        failedLoadCause.setVisible(false);
        loadFileButton.setVisible(true);
    }

}

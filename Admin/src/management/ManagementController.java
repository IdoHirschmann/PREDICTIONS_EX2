package management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import management.details.DetailsController;
import management.threadPoolManagement.ThreadPoolManagementController;

public class ManagementController {

    @FXML
    private Button OKButton;
    @FXML
    private Label currentFileLabel;
    @FXML
    private Label failedLoadCause;
    @FXML
    private Button loadFileButton;
    @FXML
    private Button threadsCountButton;
    @FXML
    private HBox detailsHBox;
    @FXML
    private DetailsController detailsHBoxController;
    @FXML
    private VBox threadPoolManagement;
    @FXML
    private ThreadPoolManagementController threadPoolManagementController;

    @FXML
    public void initialize() {
        //todo - need to request the simulationDefDTO   V
       // detailsHBoxController.initializeDetailsData(null);
    }
    @FXML
    void OKButtonClicked(ActionEvent event) {
        failedLoadCause.setVisible(false);
        OKButton.setVisible(false);
        currentFileLabel.setVisible(true);
        loadFileButton.setVisible(true);
    }
    @FXML
    void loadFileButtonClicked(ActionEvent event) {
        //todo


        //todo - need to request the simulationDefDTO   V
        detailsHBoxController.initializeDetailsData(null);
    }
    @FXML
    void threadsCountButtonClicked(ActionEvent event) {
        Integer newThreadCount = threadPoolManagementController.getThreadsCounter();
        //todo
    }
}

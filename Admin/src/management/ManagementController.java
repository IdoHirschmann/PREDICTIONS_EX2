package management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import management.details.DetailsController;

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
    void OKButtonClicked(ActionEvent event) {
        failedLoadCause.setVisible(false);
        OKButton.setVisible(false);
        currentFileLabel.setVisible(true);
        loadFileButton.setVisible(true);
    }
    @FXML
    void loadFileButtonClicked(ActionEvent event) {
        //todo
    }
    @FXML
    void threadsCountButtonClicked(ActionEvent event) {
        //todo
    }
}

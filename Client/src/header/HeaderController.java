package header;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mainPage.MainPageController;

public class HeaderController {

    @FXML
    private Button executionsButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Button requestsButton;
    @FXML
    private Button resultsButton;
    @FXML
    private Button simulationDetailsButton;
    private MainPageController mainPageController;

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    public void changeUserNameLabel(String name) {
        nameLabel.setText(name);
    }
    @FXML
    void executionsButtonClicked(ActionEvent event) {
        mainPageController.loadExecutionsScreen();
    }
    @FXML
    void requestsButtonClicked(ActionEvent event) {
        mainPageController.loadRequestsScreen();
    }
    @FXML
    void resultsButtonClicked(ActionEvent event) {
        mainPageController.loadResultsScreen();
    }
    @FXML
    void simulationDetailsButtonClicked(ActionEvent event) {
        mainPageController.loadSimulationDetailsScreen();
    }
}

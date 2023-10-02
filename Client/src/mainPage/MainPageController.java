package mainPage;

import header.HeaderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainPageController {
    @FXML
    private Pane bodyPane;
    @FXML
    private GridPane header;
    @FXML
    private HeaderController headerController;
    private Boolean isExecutionLoad = false;
    private Boolean isRequestsLoad = false;
    private Boolean isResultsLoad = false;
    private Boolean isSimulationDetailsLoad = false;

    @FXML
    public void initialize() {
        headerController.setMainPageController(this);
    }

    public void loadExecutionsScreen() {
        //todo
    }
    public void loadRequestsScreen() {
        try {
            if (!isRequestsLoad) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/requests/Requests.fxml"));
                Parent managementContent = loader.load();

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(managementContent);
                isRequestsLoad = true;
                isExecutionLoad = false;
                isResultsLoad = false;
                isSimulationDetailsLoad = false;
            }
        } catch (Exception e) {
        }
    }
    public void loadResultsScreen() {
        //todo
    }
    public void loadSimulationDetailsScreen() {
        //todo
    }


}

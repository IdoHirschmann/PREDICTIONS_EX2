package header;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mainPage.MainPageController;

public class HeaderController {

    private MainPageController mainPageController;

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    @FXML
    void allocationButtonClicked(ActionEvent event) {

    }

    @FXML
    void executionsHistoryButtonClicked(ActionEvent event) {

    }

    @FXML
    void managementButtonClicked(ActionEvent event) {
        mainPageController.managementScreen();
    }

}

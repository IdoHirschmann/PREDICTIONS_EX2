package mainPage;

import allocations.AllocationsController;
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
    private AllocationsController allocationsController;
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
    public void allocationsScreen() {
        try {
            if (!isAllocationsPresent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/allocations/Allocations.fxml"));
                Parent managementContent = loader.load();
                allocationsController = loader.getController();

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(managementContent);
                isManagementPresent = false;
                isAllocationsPresent = true;
                isExecutionsHistoryPresent = false;
            }
        } catch (IOException e) {
        }
    }
}

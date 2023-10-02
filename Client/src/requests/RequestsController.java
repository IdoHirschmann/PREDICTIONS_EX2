package requests;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import requests.newRequest.NewRequestController;

public class RequestsController {
    @FXML
    private VBox newRequest;
    @FXML
    private NewRequestController newRequestController;

    public void setUserName(String name){
        newRequestController.setUserName(name);
    }

}

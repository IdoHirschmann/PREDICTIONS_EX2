package requests;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import requests.newRequest.NewRequestController;
import requests.requestTable.RequestTableController;

public class RequestsController {
    @FXML
    private VBox newRequest;
    @FXML
    private NewRequestController newRequestController;
    @FXML
    private VBox requestTable;
    @FXML
    private RequestTableController requestTableController;

    public void setUserName(String name){
        newRequestController.setUserName(name);
        requestTableController.setUserName(name);
    }

}

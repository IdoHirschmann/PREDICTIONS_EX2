package requests.requestTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RequestTableController {

    @FXML
    private TableColumn<?, ?> endedCol;
    @FXML
    private Button executeButton;
    @FXML
    private TableView<?> requestsTable;
    @FXML
    private TableColumn<?, ?> runningCol;
    @FXML
    private TableColumn<?, ?> runsCol;
    @FXML
    private TableColumn<?, ?> serialNumberCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private TableColumn<?, ?> worldNameCol;
    @FXML
    void executeButtonClicked(ActionEvent event) {
        
    }

}
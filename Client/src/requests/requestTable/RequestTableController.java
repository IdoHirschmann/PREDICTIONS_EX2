package requests.requestTable;

import ex3DTO.RequestDTO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class  RequestTableController {

    @FXML
    private TableColumn<RequestDTO, Integer> endedCol;
    @FXML
    private Button executeButton;
    @FXML
    private TableView<RequestDTO> requestsTable;
    @FXML
    private TableColumn<RequestDTO, Integer> runningCol;
    @FXML
    private TableColumn<RequestDTO, Integer> runsCol;
    @FXML
    private TableColumn<RequestDTO, Integer> serialNumberCol;
    @FXML
    private TableColumn<RequestDTO, String> statusCol;
    @FXML
    private TableColumn<RequestDTO, String> worldNameCol;

    @FXML
    public void initialize(){
        statusCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(cellData.getValue().getRequestStatus());
            return property;
        });
        worldNameCol.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(cellData.getValue().getSimulationName());
            return property;
        });
        endedCol.setCellValueFactory(cellData -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            property.setValue(cellData.getValue().getEndedCount());
            return property.asObject();
        });
        runningCol.setCellValueFactory(cellData -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            property.setValue(cellData.getValue().getRunningCount());
            return property.asObject();
        });
        runsCol.setCellValueFactory(cellData -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            property.setValue(cellData.getValue().getSimulationAmount());
            return property.asObject();
        });
        serialNumberCol.setCellValueFactory(cellData -> {
            SimpleIntegerProperty property = new SimpleIntegerProperty();
            property.setValue(cellData.getValue().getId());
            return property.asObject();
        });

        loadData();
    }

    private void loadData() {
        //todo need to load the data every 2 seconds with timer
    }
    @FXML
    void executeButtonClicked(ActionEvent event) {
        //todo - my work
    }

}
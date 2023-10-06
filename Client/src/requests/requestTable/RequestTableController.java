package requests.requestTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ex2DTO.QueueInfoDTO;
import ex3DTO.RequestDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

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
    private String userName;
    private ObservableList <RequestDTO> data = FXCollections.observableArrayList();
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

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

        requestsTable.setItems(data);

        loadData();
    }

    private void loadData() {
        //todo need to load the data every 2 seconds with timer

        Timer timer = new Timer();
        Gson gson = new Gson();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    // Create a GET request
                    Request request = new Request.Builder()
                            .url("http://localhost:8080/Predictions/get_sim_reqs")
                            .build();

                    // Send the GET request
                    Response response = client.newCall(request).execute();

                    // Check if the request was successful
                    if (response.isSuccessful()) {
                        Type listType = new TypeToken<List<RequestDTO>>() {}.getType();
                        List<RequestDTO> lst = gson.fromJson(response.body().string(), listType);

                        List<RequestDTO> specificUserReqList = new ArrayList<>();
                        lst.forEach(requestDTO -> {
                            if(requestDTO.getUserName().equals(userName)){
                                specificUserReqList.add(requestDTO);
                            }
                        });

                        Platform.runLater(() -> {
                            specificUserReqList.forEach(requestDTO -> {
                                boolean found = false;

                                for(int i = 0 ; i< data.size(); i++){
                                    if(Objects.equals(data.get(i).getId() , requestDTO.getId())){
                                        found = true;

                                        if((!Objects.equals(data.get(i).getEndedCount() , requestDTO.getEndedCount()))
                                        || (!Objects.equals(data.get(i).getRequestStatus() , requestDTO.getRequestStatus()))){
                                            data.set(i, requestDTO);
                                        }
                                        break;
                                    }
                                }
                                if(!found){
                                    data.add(requestDTO);
                                }
                            });
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }
    @FXML
    void executeButtonClicked(ActionEvent event) {
        //todo - my work
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
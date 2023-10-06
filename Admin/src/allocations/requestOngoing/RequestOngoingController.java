package allocations.requestOngoing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ex2DTO.QueueInfoDTO;
import ex3DTO.RequestDTO;
import ex3DTO.SimulationNameDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RequestOngoingController {

    @FXML
    private Button acceptButton;
    @FXML
    private Label byUserLabel;
    @FXML
    private Button declineButton;
    @FXML
    private Label secondsLabel;
    @FXML
    private Label simulationNameLabel;
    @FXML
    private Label simulationQunatityLabel;
    @FXML
    private Label ticksLabel;
    @FXML
    private Label userNameLabel;
    private Integer id;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    public void setData(RequestDTO requestDTO){
        simulationNameLabel.setText(requestDTO.getSimulationName());
        simulationQunatityLabel.setText(requestDTO.getSimulationAmount().toString());
        userNameLabel.setText(requestDTO.getUserName());

        if(requestDTO.getTerminationType().equals("By user")){
            secondsLabel.setText("----");
            ticksLabel.setText("----");
            byUserLabel.setText("True");
        }else {
            if(requestDTO.getSeconds() != null){
                secondsLabel.setText(requestDTO.getSeconds().toString());
            }
            else{
                secondsLabel.setText("----");
            }

            if(requestDTO.getTicks() != null){
                ticksLabel.setText(requestDTO.getTicks().toString());
            }
            else{
                ticksLabel.setText("----");
            }

            byUserLabel.setText("False");
        }

    }

    public void setId(Integer id) {
        this.id = id;
    }

    @FXML
    void AcceptButtonClicked(ActionEvent event) throws IOException {
        String url = "http://localhost:8080/Predictions/approve_req?id=" + id.toString();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", body)
                .build();
        Response response = client.newCall(request).execute();

    }
    @FXML
    void DeclineButtonClicked(ActionEvent event) throws IOException {
        String url = "http://localhost:8080/Predictions/decline_req?id=" + id.toString();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", body)
                .build();
        Response response = client.newCall(request).execute();
    }

}
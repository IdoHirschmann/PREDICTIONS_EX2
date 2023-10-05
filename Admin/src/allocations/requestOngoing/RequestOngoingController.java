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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public void setData(RequestDTO requestDTO){
        simulationNameLabel.setText(requestDTO.getSimulationName());
        simulationQunatityLabel.setText(requestDTO.getSimulationAmount().toString());
        userNameLabel.setText(requestDTO.getUserName());

        if(requestDTO.getTerminationType().equals("By user")){
            secondsLabel.setText("----");
            ticksLabel.setText("----");
            byUserLabel.setText("True");
        }else {
            secondsLabel.setText(requestDTO.getSeconds().toString());
            ticksLabel.setText(requestDTO.getTicks().toString());
            byUserLabel.setText("False");
        }

    }

    @FXML
    void AcceptButtonClicked(ActionEvent event) {

    }
    @FXML
    void DeclineButtonClicked(ActionEvent event) {

    }

}
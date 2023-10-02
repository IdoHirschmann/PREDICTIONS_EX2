package requests.newRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ex2DTO.QueueInfoDTO;
import ex3DTO.NewRequestDTO;
import ex3DTO.SimulationNameDTO;
import ex3DTO.TerminationTicksSecondsDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class NewRequestController {

    @FXML
    private CheckBox secondsCheckBox;
    @FXML
    private Spinner<Integer> secondsSpinner;
    @FXML
    private Spinner<Integer> simulationAmountSpinner;
    @FXML
    private Button submitButton;
    @FXML
    private CheckBox ticksCheckBox;
    @FXML
    private CheckBox ticksSecondsCheckBox;
    @FXML
    private Spinner<Integer> ticksSpinner;
    @FXML
    private CheckBox userCheckBox;
    @FXML
    private ChoiceBox<String> simulationChoiceBox;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    private String userName;
    @FXML
    public void initialize() throws IOException {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        secondsSpinner.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        simulationAmountSpinner.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        ticksSpinner.setValueFactory(valueFactory);

        isSubmitLegal();

        Gson gson = new Gson();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("http://localhost:8080/Predictions/get_simulations_name")
                            .build();
                    Response response = client.newCall(request).execute();

                    Type listType = new TypeToken<List<SimulationNameDTO>>() {}.getType();
                    List<SimulationNameDTO> nameDTOList = gson.fromJson(response.body().string(), listType);
                    String chosenOption = simulationChoiceBox.getValue();

                    Platform.runLater(() -> {
                        simulationChoiceBox.getItems().clear();
                        simulationChoiceBox.setValue(chosenOption);
                        for(SimulationNameDTO simulationNameDTO: nameDTOList){
                            simulationChoiceBox.getItems().add(simulationNameDTO.getName().toString());
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }

    @FXML
    void ticksSecondsCheckBoxClicked(ActionEvent event) {
        if(ticksSecondsCheckBox.isSelected()) {
            userCheckBox.setVisible(false);
            ticksCheckBox.setVisible(true);
            secondsCheckBox.setVisible(true);
        }
        else {
            userCheckBox.setVisible(true);
            ticksCheckBox.setVisible(false);
            ticksSpinner.setVisible(false);
            secondsCheckBox.setVisible(false);
            secondsSpinner.setVisible(false);

            ticksCheckBox.setSelected(false);
            secondsCheckBox.setSelected(false);
        }
        isSubmitLegal();
    }
    @FXML
    void userCheckBoxClicked(ActionEvent event) {
        if(!userCheckBox.isSelected()) {
            ticksSecondsCheckBox.setVisible(true);
        }
        else {
            ticksSecondsCheckBox.setVisible(false);
            ticksCheckBox.setVisible(false);
            ticksSpinner.setVisible(false);
            secondsCheckBox.setVisible(false);
            secondsSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void secondsCheckBoxClicked(ActionEvent event) {
        if(secondsCheckBox.isSelected()){
            secondsSpinner.setVisible(true);
        }
        else {
            secondsSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void ticksCheckBoxClicked(ActionEvent event) {
        if(ticksCheckBox.isSelected()) {
            ticksSpinner.setVisible(true);
        }
        else {
            ticksSpinner.setVisible(false);
        }
        isSubmitLegal();
    }
    @FXML
    void submitButtonClicked(ActionEvent event) throws IOException {
        NewRequestDTO newRequestDTO;
        String name = simulationChoiceBox.getValue();
        Integer count = simulationAmountSpinner.getValue();
        Integer ticks = ticksSpinner.isVisible() ? ticksSpinner.getValue() : null;
        Integer second = secondsSpinner.isVisible() ? secondsSpinner.getValue() : null;

        if(userCheckBox.isSelected()) {
            newRequestDTO = new NewRequestDTO(userName,name, count, true, null);
        }
        else {
            newRequestDTO = new NewRequestDTO(userName,name, count, false, new TerminationTicksSecondsDTO(ticks, second));
        }

        Gson gson = new Gson();
        String json = gson.toJson(newRequestDTO);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Create a request body with the JSON data
        RequestBody requestBody = RequestBody.create(json, JSON);

        // Create a POST request
        Request request = new Request.Builder()
                .url("http://localhost:8080/Predictions/new_sim_run")
                .post(requestBody)
                .build();

        // Execute the request
        Response response = client.newCall(request).execute();

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private void isSubmitLegal() {
        if(simulationChoiceBox.getValue() == null) {
            submitButton.setDisable(true);
        }
        else if(!ticksSecondsCheckBox.isSelected() && !userCheckBox.isSelected()) {
            submitButton.setDisable(true);
        }
        else if(ticksSecondsCheckBox.isSelected() && !ticksCheckBox.isSelected() && !secondsCheckBox.isSelected()) {
            submitButton.setDisable(true);
        }
        else {
            submitButton.setDisable(false);
        }
    }
}
package management.threadPoolManagement;

import com.google.gson.Gson;
import ex2DTO.QueueInfoDTO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadPoolManagementController {

    @FXML
    private Label simDone;
    @FXML
    private Label simDoneCounter;
    @FXML
    private Label simInQueue;
    @FXML
    private Label simInQueueCounter;
    @FXML
    private Label simRunning;
    @FXML
    private Label simRunningCounter;
    @FXML
    private Spinner<Integer> thredsCountSpinner;
    private Thread thread;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        thredsCountSpinner.setValueFactory(valueFactory);
    }

    public void manageInfo(){
        Timer timer = new Timer();
        Gson gson = new Gson();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    // Create a GET request
                    Request request = new Request.Builder()
                            .url("http://localhost:8080/Predictions/get_num_of_thread")
                            .build();

                    // Send the GET request
                    Response response = client.newCall(request).execute();

                    // Check if the request was successful
                    if (response.isSuccessful()) {
                        QueueInfoDTO queueInfoDTO = gson.fromJson(response.body().string(), QueueInfoDTO.class);
                        Platform.runLater(() -> {
                        simDone.setText(queueInfoDTO.getSimDone().toString());
                        simInQueue.setText(queueInfoDTO.getSimInQueue().toString());
                        simRunning.setText(queueInfoDTO.getSimRunning().toString());
                    });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 650);
    }



    public Integer getThreadsCounter(){
        return thredsCountSpinner.getValue();
    }
}
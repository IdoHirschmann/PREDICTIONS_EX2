package allocations;

import allocations.requestDone.requestAccept.RequestAcceptController;
import allocations.requestDone.requestDecline.RequestDeclineController;
import allocations.requestOngoing.RequestOngoingController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ex3DTO.RequestDTO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AllocationsController {

    @FXML
    private VBox requestDoneHBox;
    @FXML
    private VBox requestOngoingHBox;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    @FXML
    public void initialize() throws IOException {
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
                        Platform.runLater(() -> {
                            updateData(lst);
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }

    private void updateData(List<RequestDTO> lst) {
        requestOngoingHBox.getChildren().clear();
        requestDoneHBox.getChildren().clear();

        lst.forEach((requestDTO)->{
            try {
                if(requestDTO.getRequestStatus().toString().equals("PENDING")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/allocations/requestOngoing/RequestOngoing.fxml"));
                    Parent ongoingContent = loader.load();
                    RequestOngoingController requestOngoingController = loader.getController();

                    requestOngoingController.setData(requestDTO);
                    requestOngoingController.setId(requestDTO.getId());

                    requestOngoingHBox.getChildren().add(ongoingContent);
                } else if (requestDTO.getRequestStatus().toString().equals("APPROVED")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/allocations/requestDone/requestAccept/RequestAccept.fxml"));
                    Parent approvedContent = loader.load();
                    RequestAcceptController requestAcceptController = loader.getController();

                    requestAcceptController.setData(requestDTO);

                    requestDoneHBox.getChildren().add(approvedContent);
                }else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/allocations/requestDone/requestDecline/RequestDecline.fxml"));
                    Parent declinedContent = loader.load();
                    RequestDeclineController requestDeclineController = loader.getController();

                    requestDeclineController.setData(requestDTO);

                    requestDoneHBox.getChildren().add(declinedContent);
                }


            } catch (IOException e) {
            }
        });
    }

}
package management;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ex3DTO.SimulationNameDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import management.details.DetailsController;
import management.threadPoolManagement.ThreadPoolManagementController;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ManagementController {

    @FXML
    private Button OKButton;
    @FXML
    private Label currentFileLabel;
    @FXML
    private Label failedLoadCause;
    @FXML
    private Button loadFileButton;
    @FXML
    private Button threadsCountButton;
    @FXML
    private HBox detailsHBox;
    @FXML
    private DetailsController detailsHBoxController;
    @FXML
    private VBox threadPoolManagement;
    @FXML
    private ThreadPoolManagementController threadPoolManagementController;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    @FXML
    public void initialize() throws IOException {
        Gson gson = new Gson();

        // MediaType mediaType = MediaType.parse("text/plain");
        //RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://localhost:8080/Predictions/get_simulations_name")
                .build();
        Response response = client.newCall(request).execute();

        Type listType = new TypeToken<List<SimulationNameDTO>>() {}.getType();
        List<SimulationNameDTO> nameDTOList = gson.fromJson(response.body().string(), listType);

        detailsHBoxController.initializeDetailsData(nameDTOList);
    }
    @FXML
    void OKButtonClicked(ActionEvent event) {
        failedLoadCause.setVisible(false);
        OKButton.setVisible(false);
        currentFileLabel.setVisible(true);
        loadFileButton.setVisible(true);
    }
    @FXML
    void loadFileButtonClicked(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("xmlFile","file",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(selectedFile.getAbsolutePath())))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/Predictions/loadXml")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            currentFileLabel.setVisible(false);
            failedLoadCause.setVisible(true);
            loadFileButton.setVisible(false);
            OKButton.setVisible(true);

            failedLoadCause.setText(response.body().string());
        }else {
            Gson gson = new Gson();
            currentFileLabel.setText(selectedFile.getAbsolutePath());

           // RequestBody simulationNamesBody = RequestBody.create(mediaType, "");
            Request simulationNamesRequest = new Request.Builder()
                    .url("http://localhost:8080/Predictions/get_simulations_name")
                    .build();
            Response simulationNamesresponse = client.newCall(simulationNamesRequest).execute();

            Type listType = new TypeToken<List<SimulationNameDTO>>() {}.getType();
            List<SimulationNameDTO> nameDTOList = gson.fromJson(simulationNamesresponse.body().string(), listType);

            detailsHBoxController.initializeDetailsData(nameDTOList);
        }
    }
    @FXML
    void threadsCountButtonClicked(ActionEvent event) {
        Integer newThreadCount = threadPoolManagementController.getThreadsCounter();
        //todo
    }
}

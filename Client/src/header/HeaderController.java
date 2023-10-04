package header;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import mainPage.MainPageController;
import okhttp3.*;

import java.io.IOException;

public class HeaderController {

    @FXML
    private Button executionsButton;
    @FXML
    private HBox nameHBox;
    @FXML
    private HBox loginHBox;
    @FXML
    private Label nameLabel;
    @FXML
    private Button requestsButton;
    @FXML
    private Button resultsButton;
    @FXML
    private Button simulationDetailsButton;
    @FXML
    private TextField userName;
    private MainPageController mainPageController;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    public void changeUserNameLabel(String name) {
        nameLabel.setText(name);
    }
    @FXML
    void executionsButtonClicked(ActionEvent event) {
        mainPageController.loadExecutionsScreen();
    }
    @FXML
    void requestsButtonClicked(ActionEvent event) {
        mainPageController.loadRequestsScreen(nameLabel.getText());

    }
    @FXML
    void resultsButtonClicked(ActionEvent event) {
        mainPageController.loadResultsScreen();
    }
    @FXML
    void simulationDetailsButtonClicked(ActionEvent event) {
        mainPageController.loadSimulationDetailsScreen();
    }
    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {

            if(!userName.getText().equals("")){
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("name",userName.getText())
                        .build();
                Request request = new Request.Builder()
                        .url("http://localhost:8080/Predictions/user_login")
                        .method("POST", body)
                        .build();
                Response response = client.newCall(request).execute();

                if(response.isSuccessful()){
                    loginHBox.setVisible(false);
                    nameHBox.setVisible(true);
                    requestsButton.setVisible(true);
                    resultsButton.setVisible(true);
                    simulationDetailsButton.setVisible(true);
                    executionsButton.setVisible(true);
                    nameLabel.setText(userName.getText());

                }else {
                    //todo - usernameAllreadyExist
                }
            }

    }

}

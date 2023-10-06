package allocations.requestDone.requestDecline;

import ex3DTO.RequestDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RequestDeclineController {

    @FXML
    private Label byUserLabel;
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

    public void setData(RequestDTO requestDTO) {
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
}
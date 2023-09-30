package management.threadPoolManagement;

import ex2DTO.QueueInfoDTO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        thredsCountSpinner.setValueFactory(valueFactory);
    }

    private void manageInfo(){
        thread = new Thread(() -> {
            QueueInfoDTO queueInfo = null;
            while (true) {
                try {
                    //todo get threadPoolInfo
                    //queueInfo = loadedFileManager.getQueueInfo();

                    Platform.runLater(() -> {
                        simDone.setText(queueInfo.getSimDone().toString());
                        simInQueue.setText(queueInfo.getSimInQueue().toString());
                        simRunning.setText(queueInfo.getSimRunning().toString());
                    });
                    Thread.sleep(200);

                } catch (InterruptedException ignore) {
                }
            }
        });

        thread.start();
    }

    public Integer getThreadsCounter(){
        return thredsCountSpinner.getValue();
    }
}
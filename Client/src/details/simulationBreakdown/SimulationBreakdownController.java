package details.simulationBreakdown;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import details.DetailsController;
import ex3DTO.SimulationNameDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import option2.EntityDefinitionDTO;
import option2.PropertyDefinitionDTO;
import option2.RulesDTO;
import option2.SimulationDefinitionDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class SimulationBreakdownController {
    @FXML
    private ChoiceBox<String> choiceBoxEntites;
    @FXML
    private ChoiceBox<String> choiceBoxEnvironment;
    @FXML
    private ChoiceBox<String> choiceBoxRules;
    @FXML
    private ChoiceBox<String> choiceBoxSimulations;
    @FXML
    private Button chooseSimulationButton;
    @FXML
    private VBox chooseSimulationVBox;
    @FXML
    private Button showEntities;
    @FXML
    private Button showEnvironment;
    @FXML
    private Button showGrid;
    @FXML
    private Button showRules;
    @FXML
    private Button showSimulation;
    @FXML
    private VBox simulationBreakdownVBox;
    private DetailsController detailsScreenController;
    private SimulationDefinitionDTO simulationDefinitionDTO;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();


    public void setDetailsScreenController(DetailsController detailsScreenController) {
        this.detailsScreenController = detailsScreenController;
    }
    @FXML
    private void EntitiesButtonClicked(ActionEvent event) {
        String newValue = choiceBoxEntites.getSelectionModel().getSelectedItem();
        if (newValue != null) {
            Optional<EntityDefinitionDTO> entityDefinitionDTO = simulationDefinitionDTO.getEntityDefinitionDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.entitiesShowButtonClicked(entityDefinitionDTO.get());
        }
    }
    @FXML
    private void RulesButtonClicked(ActionEvent event) {
        String newValue = choiceBoxRules.getSelectionModel().getSelectedItem();
        if(newValue != null) {
            Optional<RulesDTO> rulesDTO = simulationDefinitionDTO.getRulesDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.rulesShowButtonClicked(rulesDTO.get());
        }
    }
    @FXML
    private void EnvironmentButtonClicked(ActionEvent event) {
        String newValue = choiceBoxEnvironment.getSelectionModel().getSelectedItem();
        if (newValue != null) {
            Optional<PropertyDefinitionDTO> environmentDefinitionDTO = simulationDefinitionDTO.getEnvironmentDefenitionDTOList().stream()
                    .filter(myObject -> myObject.getName().equals(newValue))
                    .findFirst();
            detailsScreenController.environmentShowButtonClicked(environmentDefinitionDTO.get());
        }
    }
    @FXML
    private void GridButtonClicked(ActionEvent event) {
        detailsScreenController.gridShowButtonClicked(simulationDefinitionDTO.getGridRows(), simulationDefinitionDTO.getGridCols());
    }
    @FXML
    private void chooseSimulationButtonClicked(ActionEvent event) throws IOException {

        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("http://localhost:8080/Predictions/get_simulations_name")
                .build();
        Response response = client.newCall(request).execute();

        Type listType = new TypeToken<List<SimulationNameDTO>>() {}.getType();
        List<SimulationNameDTO> nameDTOList = gson.fromJson(response.body().string(), listType);

        for(SimulationNameDTO simulationNameDTO: nameDTOList){
            choiceBoxSimulations.getItems().add(simulationNameDTO.getName().toString());
        }

        chooseSimulationVBox.setVisible(true);
        simulationBreakdownVBox.setVisible(false);
        choiceBoxEntites.setValue(null);
        choiceBoxEntites.getItems().clear();
        choiceBoxRules.getItems().clear();
        choiceBoxEnvironment.getItems().clear();
        choiceBoxRules.setValue(null);
        choiceBoxEnvironment.setValue(null);

        detailsScreenController.clearSelectedComponent();
        detailsScreenController.initializeDetailsData(nameDTOList);
    }
    @FXML
    private void simulationsButtonClicked(ActionEvent event) throws IOException {
        String simulationName = choiceBoxSimulations.getSelectionModel().getSelectedItem();
        if(simulationName != null) {
            Gson gson = new Gson();

            String url  = "http://localhost:8080/Predictions//get_simulations_details?name=";

            Request request = new Request.Builder()
                    .url(url + simulationName)
                    .build();
            Response response = client.newCall(request).execute();
            simulationDefinitionDTO = gson.fromJson(response.body().string(),SimulationDefinitionDTO.class);


            initializeRules(simulationDefinitionDTO.getRulesDTOList());
            initializeEntities(simulationDefinitionDTO.getEntityDefinitionDTOList());
            initializeEnvironments(simulationDefinitionDTO.getEnvironmentDefenitionDTOList());

            chooseSimulationVBox.setVisible(false);
            simulationBreakdownVBox.setVisible(true);
            choiceBoxSimulations.setValue(null);
            choiceBoxSimulations.getItems().clear();
        }
    }
    public void initializeDetailsData(List<SimulationNameDTO> simulationNameDTOS) {
        choiceBoxSimulations.getItems().clear();

        for(SimulationNameDTO simulationNameDTO: simulationNameDTOS){
            choiceBoxSimulations.getItems().add(simulationNameDTO.getName().toString());//todo
        }
    }
    private void initializeRules(List<RulesDTO> rulesDTOList) {
        for(RulesDTO rulesDTO : rulesDTOList) {
            choiceBoxRules.getItems().add(rulesDTO.getName());
        }
    }
    private void initializeEntities(List<EntityDefinitionDTO> entityDefinitionDTOList) {
        for(EntityDefinitionDTO entityDefinitionDTO : entityDefinitionDTOList) {
            choiceBoxEntites.getItems().add(entityDefinitionDTO.getName());
        }
    }
    private void initializeEnvironments(List<PropertyDefinitionDTO> environmentDefenitionDTOList) {
        for(PropertyDefinitionDTO environmentDefinitionDTO : environmentDefenitionDTOList) {
            choiceBoxEnvironment.getItems().add(environmentDefinitionDTO.getName());
        }
    }

}

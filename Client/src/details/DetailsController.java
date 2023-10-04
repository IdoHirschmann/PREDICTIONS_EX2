package details;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import details.selectedComponent.entity.EntityDetailsController;
import details.selectedComponent.environment.EnvironmentDetailsController;
import details.selectedComponent.grid.GridDetailsController;
import details.selectedComponent.rule.RulesDetailsController;
import details.simulationBreakdown.SimulationBreakdownController;
import ex3DTO.SimulationNameDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import option2.EntityDefinitionDTO;
import option2.PropertyDefinitionDTO;
import option2.RulesDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class DetailsController {

    @FXML
    private Pane selectedComponentPane;
    @FXML
    private StackPane simulationBreakdown;
    @FXML
    private SimulationBreakdownController simulationBreakdownController;
    private OkHttpClient client = new OkHttpClient().newBuilder().build();

    @FXML
    public void initialize() throws IOException {
        simulationBreakdownController.setDetailsScreenController(this);
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("http://localhost:8080/Predictions/get_simulations_name")
                .build();
        Response response = client.newCall(request).execute();

        Type listType = new TypeToken<List<SimulationNameDTO>>() {}.getType();
        List<SimulationNameDTO> nameDTOList = gson.fromJson(response.body().string(), listType);

        initializeDetailsData(nameDTOList);
    }
    public void entitiesShowButtonClicked(EntityDefinitionDTO EntityData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/entity/EntityDetails.fxml"));
            Parent entityContent = loader.load();
            EntityDetailsController entityDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(entityContent);

            entityDetailsController.setAllDataMembers(EntityData);

        } catch (IOException e) {
        }
    }

    public void environmentShowButtonClicked(PropertyDefinitionDTO EnvironmentData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/environment/EnvironmentDetails.fxml"));
            Parent environmentContent = loader.load();
            EnvironmentDetailsController environmentDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(environmentContent);

            environmentDetailsController.setData(EnvironmentData);

        } catch (IOException e) {
        }
    }


    public void rulesShowButtonClicked(RulesDTO rulesDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/rule/RulesDetails.fxml"));
            Parent ruleContent = loader.load();
            RulesDetailsController rulesDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(ruleContent);

            rulesDetailsController.setAllDataMembers(rulesDTO);

        } catch (IOException e) {
        }
    }

    public void gridShowButtonClicked(Integer rows, Integer cols) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/details/selectedComponent/grid/GridDetails.fxml"));
            Parent gridContent = loader.load();
            GridDetailsController gridDetailsController = loader.getController();
            selectedComponentPane.getChildren().clear();
            selectedComponentPane.getChildren().add(gridContent);

            gridDetailsController.setData(rows.toString(), cols.toString());

        } catch (IOException e) {
        }
    }

    public void clearSelectedComponent(){
        selectedComponentPane.getChildren().clear();
    }

    public void initializeDetailsData(List<SimulationNameDTO> simulationNameDTOS) {
        simulationBreakdownController.initializeDetailsData(simulationNameDTOS);
    }
}
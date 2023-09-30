package management.details.selectedComponent.rule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import management.details.selectedComponent.rule.action.calculation.CalculationDataController;
import management.details.selectedComponent.rule.action.decrease.DecreaseDataController;
import management.details.selectedComponent.rule.action.increase.IncreaseDataController;
import management.details.selectedComponent.rule.action.kill.KillDataController;
import management.details.selectedComponent.rule.action.multipleCondition.MultipleConditionDataController;
import management.details.selectedComponent.rule.action.proximity.ProximityDataController;
import management.details.selectedComponent.rule.action.replace.ReplaceDataController;
import management.details.selectedComponent.rule.action.set.SetDataController;
import management.details.selectedComponent.rule.action.singleCondition.SingleConditionDataController;
import option2.ActionDTO.*;
import option2.RulesDTO;

import java.io.IOException;
import java.util.List;

public class RulesDetailsController {

    @FXML
    private Pane actionDetails;
    @FXML
    private Label numberOfActions;
    @FXML
    private Label probability;
    @FXML
    private Label rulleName;
    @FXML
    private Label ticks;
    @FXML
    private TreeView<String> actionTreeView = new TreeView<>();

    private List<ActionDTO> actionDTOList;

    public void setAllDataMembers(RulesDTO rulesDTO) {
        numberOfActions.setText(rulesDTO.getActionCounter().toString());
        probability.setText(rulesDTO.getProbability().toString());
        rulleName.setText(rulesDTO.getName());
        ticks.setText(rulesDTO.getTicks().toString());
        setActionTreeView(rulesDTO.getActionTypes());
    }

    private void setActionTreeView(List<ActionDTO> actionTypes) {
        TreeItem<String> root = new TreeItem<>("Select Action");
        for (ActionDTO actionType : actionTypes) {
            root.getChildren().add(new TreeItem<>(actionType.getName()));
        }
        actionTreeView.setRoot(root);
        actionDTOList = actionTypes;
    }

    @FXML
    private void SelectActionClicked(ActionEvent event) {
        TreeItem<String> selectedItem = actionTreeView.getSelectionModel().getSelectedItem();
        Boolean stopped = false;
        int i = 0;
        for(TreeItem<String> treeItem : actionTreeView.getRoot().getChildren()) {
            if(treeItem.equals(selectedItem)) {
                stopped = true;
                break;
            }
            i++;
        }
        if(!stopped) {
            return;
        }
        ActionDTO actionDTO = actionDTOList.get(i);
        setActionDetailsPain(actionDTO);
    }

    private void setActionDetailsPain(ActionDTO actionDTO) {
        switch (actionDTO.getName()) {
            case "Increase":
                createIncreaseAction((IncreaseDTO)actionDTO);
                break;
            case "Decrease":
                createDecreaseAction((DecreaseDTO)actionDTO);
                break;
            case "Multiply":
                createCalculationAction((CalculationDTO)actionDTO);
                break;
            case "Divide":
                createCalculationAction((CalculationDTO)actionDTO);
                break;
            case "Multiple Condition":
                createMulConAction((MultipleConditionDTO)actionDTO);
                break;
            case "Single Condition":
                createSinConAction((SingleConditionDTO)actionDTO);
                break;
            case "Kill":
                createKillAction((KillDTO)actionDTO);
                break;
            case "Set":
                createSetAction((SetDTO)actionDTO);
                break;
            case "Proximity":
                createProximityAction((ProximityDTO)actionDTO);
                break;
            case "Replace":
                createReplaceAction((ReplaceDTO)actionDTO);
                break;
        }
    }

    private void createIncreaseAction(IncreaseDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/increase/IncreaseData.fxml"));
            Parent increaseContent = loader.load();
            IncreaseDataController increaseDataController = loader.getController();

            increaseDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(increaseContent);

        } catch (IOException e) {
        }
    }

    private void createDecreaseAction(DecreaseDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/decrease/DecreaseData.fxml"));
            Parent decreaseContent = loader.load();
            DecreaseDataController decreaseDataController = loader.getController();

            decreaseDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(decreaseContent);

        } catch (IOException e) {
        }
    }

    private void createCalculationAction(CalculationDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/calculation/CalculationData.fxml"));
            Parent calculationContent = loader.load();
            CalculationDataController calculationDataController = loader.getController();

            calculationDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(calculationContent);

        } catch (IOException e) {
        }
    }

    private void createMulConAction(MultipleConditionDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/multipleCondition/MultipleConditionData.fxml"));
            Parent multipleConditionContent = loader.load();
            MultipleConditionDataController multipleConditionDataController = loader.getController();

            multipleConditionDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(multipleConditionContent);

        } catch (IOException e) {
        }
    }

    private void createSinConAction(SingleConditionDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/singleCondition/SingleConditionData.fxml"));
            Parent singleConditionContent = loader.load();
            SingleConditionDataController singleConditionDataController = loader.getController();

            singleConditionDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(singleConditionContent);

        } catch (IOException e) {
        }
    }

    private void createKillAction(KillDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/kill/KillData.fxml"));
            Parent killContent = loader.load();
            KillDataController killDataController = loader.getController();

            killDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(killContent);

        } catch (IOException e) {
        }
    }

    private void createSetAction(SetDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/set/SetData.fxml"));
            Parent setContent = loader.load();
            SetDataController setDataController = loader.getController();

            setDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(setContent);

        } catch (IOException e) {
        }
    }

    private void createProximityAction(ProximityDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/proximity/ProximityData.fxml"));
            Parent proximityContent = loader.load();
            ProximityDataController proximityDataController = loader.getController();

            proximityDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(proximityContent);

        } catch (IOException e) {
        }
    }

    private void createReplaceAction(ReplaceDTO actionDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management/details/selectedComponent/rule/action/replace/ReplaceData.fxml"));
            Parent replaceContent = loader.load();
            ReplaceDataController replaceDataController = loader.getController();

            replaceDataController.SetData(actionDTO);
            actionDetails.getChildren().clear();
            actionDetails.getChildren().add(replaceContent);

        } catch (IOException e) {
        }
    }

}
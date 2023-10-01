package rule.action.impl.condition;

import entity.definition.EntityDefinition;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.MultipleConditionDTO;
import rule.action.ActionType;
import rule.action.api.Action;
import rule.action.context.api.ActionContext;
import rule.action.impl.condition.enums.ConditionResult;
import rule.action.impl.condition.enums.LogicType;
import rule.action.impl.secondaryEntity.SecondaryEntity;

import java.util.List;

public class MultipleCondition extends AbstractCondition {
    private final List<AbstractCondition> conditions;
    private final LogicType logic;

    public MultipleCondition(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition , List<Action> then, List<Action> elsE, List<AbstractCondition> conditions, LogicType logic) {
        super(primaryEntityDefinition,secondaryEntityDefinition ,ActionType.MULTIPLE_CONDITION, then, elsE);
        this.conditions = conditions;
        this.logic = logic;
    }

    @Override
    public ActionDTO createDTO() {
        MultipleConditionDTO multipleConditionDTO = new MultipleConditionDTO(  logic.toString(), thenAmount(), elseAmount());
        if(getSecondaryEntity() == null) {
            return new ActionDTO("Multiple Condition", getPrimaryEntityDefinition().getName(), null,
                    null,null,null,null,multipleConditionDTO,null,null,null,null
            );
        }
        return new ActionDTO("Multiple Condition", getPrimaryEntityDefinition().getName(), getSecondaryEntity().getEntityName(),
                null,null,null,null,multipleConditionDTO,null,null,null,null
        );
    }

    @Override
    public ConditionResult runCondition(ActionContext context) {
        switch (logic){
            case OR:
                return runOrCondition(context);
            case AND:
                return runAndCondition(context);
            default:
                return ConditionResult.FALSE;
        }
    }

    private ConditionResult runAndCondition(ActionContext context) {
        if(isEveryConditionIgnore(context)){
            return ConditionResult.IGNORE;
        }

        for (AbstractCondition condition: conditions) {
            if (condition.runCondition(context) == ConditionResult.FALSE){
                return ConditionResult.FALSE;
            }
        }
        return ConditionResult.TRUE;
    }

    private ConditionResult runOrCondition(ActionContext context) {
        if(isEveryConditionIgnore(context)){
            return ConditionResult.IGNORE;
        }

        for (AbstractCondition condition: conditions) {
            if (condition.runCondition(context) == ConditionResult.TRUE){
                return ConditionResult.TRUE;
            }
        }

        return ConditionResult.FALSE;
    }

    private boolean isEveryConditionIgnore(ActionContext context){
        for (AbstractCondition condition: conditions) {
            if (condition.runCondition(context) != ConditionResult.IGNORE){
                return false;
            }
        }

        return true;
    }

}

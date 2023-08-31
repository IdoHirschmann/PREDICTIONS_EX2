package rule.action.impl.numeric.calculation;

import entity.definition.EntityDefinition;
import expression.ExpressionType;
import expression.api.Expression;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.CalculationDTO;
import option2.ActionDTO.KillDTO;
import option2.ActionDTO.SetDTO;
import property.instance.AbstractPropertyInstance;
import rule.action.ActionType;
import rule.action.context.api.ActionContext;

import static utills.string.StringConvertor.convertStringToFloat;
import static utills.string.StringConvertor.convertStringToInt;

public class Divide extends AbstractCalculation {
    public Divide(EntityDefinition primaryEntityDefinition,EntityDefinition secondaryEntityDefinition, String resultProp, Expression firstArgument, Expression secondArgument) {
        super(primaryEntityDefinition,secondaryEntityDefinition ,ActionType.DIVIDE, resultProp, firstArgument, secondArgument);
    }

    @Override
    public ActionDTO createDTO() {
        if(getSecondaryEntityDefinition() == null) {
            return new CalculationDTO("Divide", getPrimaryEntityDefinition().getName(), null,
                    getFirstArgument().GetSimpleValue(), getSecondArgument().GetSimpleValue(), getResultProp());
        }
        return new CalculationDTO("Divide", getPrimaryEntityDefinition().getName(), getSecondaryEntityDefinition().getName(),
                getFirstArgument().GetSimpleValue(), getSecondArgument().GetSimpleValue(), getResultProp());
    }

    @Override
    public void Invoke(ActionContext context) {
        Number result;

        if((getSecondArgument().GetExplicitValue(context.getPrimaryEntityInstance()).equals("0"))) {
            throw new RuntimeException("Divide by zero is not allowed! Problem occurred while trying to active Divide method");
        }

        if(getFirstArgument().getType() == ExpressionType.FLOAT || getSecondArgument().getType() == ExpressionType.FLOAT){
            result = convertStringToFloat(getFirstArgument().GetExplicitValue(context.getPrimaryEntityInstance())) /
                    convertStringToFloat(getSecondArgument().GetExplicitValue(context.getPrimaryEntityInstance()));
        }
        else{
            result = convertStringToInt(getFirstArgument().GetExplicitValue(context.getPrimaryEntityInstance())) /
                    convertStringToInt(getSecondArgument().GetExplicitValue(context.getPrimaryEntityInstance()));
        }

        AbstractPropertyInstance propertyInstance = extractProperty(context);

        propertyInstance.setValue(checkIfActionResultIsInRange(result,propertyInstance).toString());
    }
}

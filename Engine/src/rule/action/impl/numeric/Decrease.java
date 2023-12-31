package rule.action.impl.numeric;

import entity.definition.EntityDefinition;
import entity.instance.EntityInstance;
import exception.TryToPreformFloatActionOnDecimalPropertyException;
import expression.ExpressionType;
import expression.api.Expression;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.DecreaseDTO;
import property.instance.AbstractPropertyInstance;
import rule.action.ActionType;
import rule.action.context.api.ActionContext;
import rule.action.impl.secondaryEntity.SecondaryEntity;

import static utills.helperFunction.Helper.isDecimal;
import static utills.helperFunction.Helper.isFloat;

public class Decrease extends AbstractNumericAction {

    private final Expression by;

    public Decrease(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition, String resultProp, Expression by) {
        super(primaryEntityDefinition,secondaryEntityDefinition,ActionType.DECREASE, resultProp);
        this.by = by;
    }

    @Override
    public ActionDTO createDTO() {
        if(getSecondaryEntity() == null) {
            return new DecreaseDTO("Decrease", getPrimaryEntityDefinition().getName(), null,
                    getResultProp(), by.GetSimpleValue());
        }
        return new DecreaseDTO("Decrease", getPrimaryEntityDefinition().getName(), getSecondaryEntity().getEntityName(),
                getResultProp(), by.GetSimpleValue());
    }

    @Override
    public void Invoke(ActionContext context) {
        AbstractPropertyInstance property = extractProperty(context);
        Number newPropertyValue = extractANumber(context);
        EntityInstance mainEntity = getEntityForInvoke(context);
        if(mainEntity == null){
            return;
        }
        EntityInstance otherEntity = getOtherEntity(mainEntity, context);
        Boolean isSeconderyShouldExist = true;
        if(otherEntity == null) {
            if(context.getSecondaryEntityName() == null){
                isSeconderyShouldExist = false;
            }
        }

        if(by.getType() == ExpressionType.INT) {
            if(isDecimal(newPropertyValue.toString())) {
                newPropertyValue = newPropertyValue.intValue() - Integer.parseInt(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            } else {
                newPropertyValue = newPropertyValue.floatValue() - Integer.parseInt(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            }
        }
        else if (by.getType() == ExpressionType.FLOAT) {
            if(isFloat(newPropertyValue.toString())) {
                newPropertyValue = newPropertyValue.floatValue() - Float.parseFloat(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            }
            else {
               throw new TryToPreformFloatActionOnDecimalPropertyException("Float subtraction from Decimal property is not allowed. Error occurred in"
               + this.getClass());
            }
        }

        property.setValue(checkIfActionResultIsInRange(newPropertyValue, property).toString());
    }
}

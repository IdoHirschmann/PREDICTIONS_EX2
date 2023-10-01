package rule.action.impl.numeric;

import entity.definition.EntityDefinition;
import entity.instance.EntityInstance;
import exception.TryToPreformFloatActionOnDecimalPropertyException;
import expression.ExpressionType;
import expression.api.Expression;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.IncreaseDTO;
import property.instance.AbstractPropertyInstance;
import rule.action.ActionType;
import rule.action.context.api.ActionContext;
import rule.action.impl.secondaryEntity.SecondaryEntity;


import static utills.helperFunction.Helper.isDecimal;

public class Increase extends AbstractNumericAction {
    private final Expression by;

    public Increase(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition, String resultProp, Expression by) {
        super(primaryEntityDefinition, secondaryEntityDefinition,ActionType.INCREASE, resultProp);
        this.by = by;
    }

    @Override
    public ActionDTO createDTO() {
        IncreaseDTO increaseDTO = new IncreaseDTO( getResultProp(), by.GetSimpleValue());
        if(getSecondaryEntity() == null) {

            return new ActionDTO("Increase", getPrimaryEntityDefinition().getName(), null,
                    increaseDTO,null,null,null,null,null,null,null,null
            );
        }
        return new ActionDTO("Increase", getPrimaryEntityDefinition().getName(), getSecondaryEntity().getEntityName(),
                increaseDTO,null,null,null,null,null,null,null,null
        );
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
                newPropertyValue = newPropertyValue.intValue() + Integer.parseInt(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            }
            else {
                newPropertyValue = newPropertyValue.floatValue() + Integer.parseInt(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            }
        }
        else if (by.getType() == ExpressionType.FLOAT) {
            if(isDecimal(newPropertyValue.toString())) {
                throw new TryToPreformFloatActionOnDecimalPropertyException("Float add on Decimal property is not allowed. Error occurred in"
                        + this.getClass());
            }
            else {
                newPropertyValue = newPropertyValue.floatValue() + Float.parseFloat(by.GetExplicitValue(mainEntity, otherEntity, context.getEnvironments(), isSeconderyShouldExist));
            }
        }

        property.setValue(checkIfActionResultIsInRange(newPropertyValue, property).toString());
    }
}

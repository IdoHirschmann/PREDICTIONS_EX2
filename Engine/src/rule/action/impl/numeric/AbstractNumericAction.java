package rule.action.impl.numeric;

import entity.definition.EntityDefinition;
import entity.instance.EntityInstance;
import exception.NotRealPropertyException;
import exception.PropertyNotFoundException;
import property.definition.PropertyType;
import property.instance.AbstractPropertyInstance;
import rule.action.ActionType;
import rule.action.context.api.ActionContext;
import rule.action.impl.AbstractAction;
import rule.action.impl.secondaryEntity.SecondaryEntity;

import static utills.helperFunction.Helper.isDecimal;

public abstract class AbstractNumericAction extends AbstractAction {
    private final String resultProp;

    public AbstractNumericAction(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition, ActionType type, String resultProp) {
        super(primaryEntityDefinition, secondaryEntityDefinition ,type);
        this.resultProp = resultProp;
    }

    public String getResultProp() {
        return resultProp;
    }

    protected EntityInstance getEntityForInvoke(ActionContext context) {
        if(context.getPrimaryEntityInstance().getEntType().equals(getPrimaryEntityDefinition().getName())) {
            return context.getPrimaryEntityInstance();
        }
        else if(getPrimaryEntityDefinition().getName().equals(context.getSecondaryEntityName())) {
            return context.getSecondaryEntityInstance();
        }
        else {
            throw new RuntimeException("Entity exception! The entity: " + getPrimaryEntityDefinition().getName() + " is not valid.\n" +
                    "In this function the main entity must be: " + context.getPrimaryEntityInstance().getEntType() + (context.getSecondaryEntityInstance() == null ? "." : (" or: " + context.getSecondaryEntityInstance().getEntType())));
        }
    }
    protected EntityInstance getOtherEntity(EntityInstance entityInstance, ActionContext context) {
        if(context.getPrimaryEntityInstance().equals(entityInstance)) {
            return context.getSecondaryEntityInstance();
        }
        else {
            return context.getPrimaryEntityInstance();
        }
    }

    protected Number extractANumber(ActionContext context) {
        AbstractPropertyInstance property = extractProperty(context);
        String propertyValue;

        propertyValue = property.getValue();
        if(isDecimal(propertyValue)){
            return Integer.parseInt(propertyValue);
        } else {
            return Float.parseFloat(propertyValue);
        }
    }

    protected AbstractPropertyInstance extractProperty(ActionContext context) {
        EntityInstance entityInstance = getEntityForInvoke(context);
        AbstractPropertyInstance res = entityInstance.getProperty(resultProp);

        if(res != null) {
            return res;
        }
        else {
            throw new NotRealPropertyException("NotRealPropertyException! The requested property" + resultProp + "does not exist. Occurred " + this.getClass());
        }
    }

//    protected Boolean isANumber(AbstractPropertyInstance property) {
//        String propertyVal = property.getValue();
//        return propertyVal.matches("-?\\d+(\\.\\d+)?");
//
//    }
}

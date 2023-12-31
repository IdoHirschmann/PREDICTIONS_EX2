package expression.impl.function;

import entity.instance.EntityInstance;
import exception.SecondEntityIgnoreException;
import expression.ExpressionType;
import property.instance.AbstractPropertyInstance;

import java.util.Map;

import static utills.helperFunction.Helper.evaluate;
import static utills.helperFunction.Helper.ticks;

public class TicksFunctionExpression extends AbstractFunctionExpression {
    String entityName;
    String propertyName;

    public TicksFunctionExpression(String value, String entityName, String propertyName) {
        super(value, ExpressionType.INT);
        this.entityName = entityName;
        this.propertyName = propertyName;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }


    @Override
    public String GetSimpleValue() {
        return getValue();
    }

    @Override
    public String GetExplicitValue(EntityInstance primaryEntity, EntityInstance secondaryEntity, Map<String, AbstractPropertyInstance> environments, Boolean isSeconderyShouldExist) {
        if(entityName.equals(primaryEntity.getEntType())){
            return ticks(primaryEntity, propertyName).toString();
        }
        else if(isSeconderyShouldExist) {
            if(secondaryEntity != null) {
                if(entityName.equals(secondaryEntity.getEntType())){
                    return ticks(secondaryEntity, propertyName).toString();
                }else {
                    throw new RuntimeException("Entity exception! The entity: " + entityName + " in is not valid.\n" +
                            "In this function the main entity must be: " + primaryEntity.getEntType() + " or: " + secondaryEntity.getEntType() + ".\n" +
                            "Problem occurred in Ticks expression.");
                }
            }
            else {
                throw new SecondEntityIgnoreException();
            }
        }
        else {
            throw new RuntimeException("Entity exception! The entity: " + entityName + " in is not valid.\n" +
                    "In this function the main entity must be: " + primaryEntity.getEntType() + ". Problem occurred in Ticks expression.");
        }
    }
}


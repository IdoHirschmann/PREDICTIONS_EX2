package expression.impl.function;

import entity.instance.EntityInstance;
import exception.SecondEntityIgnoreException;
import expression.ExpressionType;
import property.instance.AbstractPropertyInstance;
import utills.helperFunction.EvaluateInfo;

import java.util.Map;

import static utills.helperFunction.Helper.evaluate;

public class EvaluateFunctionExpression extends AbstractFunctionExpression {
    private String entityName;
    private String propertyName;

    public EvaluateFunctionExpression(String value, ExpressionType type, String entityName, String propertyName) {
        super(value, type);
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
        EvaluateInfo res;

        if(entityName.equals(primaryEntity.getEntType())){
            res = evaluate(primaryEntity,propertyName);
        }
        else if(isSeconderyShouldExist) {
            if(secondaryEntity != null) {
                if(entityName.equals(secondaryEntity.getEntType())){
                    res = evaluate(secondaryEntity, propertyName);
                }else {
                    throw new RuntimeException("Entity exception! The entity: " + entityName + " in is not valid.\n" +
                            "In this function the main entity must be: " + primaryEntity.getEntType() + " or: " + secondaryEntity.getEntType() + ".\n" +
                            "Problem occurred in Evaluate expression.");
                }
            }
            else {
                throw new SecondEntityIgnoreException();
            }
        }
        else {
            throw new RuntimeException("Entity exception! The entity: " + entityName + " in is not valid.\n" +
                    "In this function the main entity must be: " + primaryEntity.getEntType() + ". Problem occurred in Evaluate expression.");
        }

        this.setType(res.getType());
        return res.getValue();
    }
}

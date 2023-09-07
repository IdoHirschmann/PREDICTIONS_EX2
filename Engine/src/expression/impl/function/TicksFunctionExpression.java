package expression.impl.function;

import entity.instance.EntityInstance;
import expression.ExpressionType;
import property.instance.AbstractPropertyInstance;

import java.util.Map;

import static utills.helperFunction.Helper.ticks;

public class TicksFunctionExpression extends AbstractFunctionExpression{
    String entityName;
    String propertyName;

    public TicksFunctionExpression(String value, ExpressionType type, String entityName, String propertyName) {
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
    public String GetExplicitValue(EntityInstance primaryEntity, EntityInstance secondaryEntity, Map<String, AbstractPropertyInstance> environments) {
        if(entityName.equals(primaryEntity.getEntType())){
            return ticks(primaryEntity, propertyName).toString();
        } else if (entityName.equals(secondaryEntity.getEntType())) {
            return ticks(secondaryEntity, propertyName).toString();
        } else {
            throw new RuntimeException(); //todo
        }
    }
}

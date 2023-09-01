package expression.impl.function;

import entity.instance.EntityInstance;
import expression.ExpressionType;

public class TicksFunctionExpression extends AbstractFunctionExpression{
    String entityName;
    String PropertyName;

    public TicksFunctionExpression(String value, ExpressionType type, String entityName, String propertyName) {
        super(value, type);
        this.entityName = entityName;
        PropertyName = propertyName;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }


    @Override
    public String GetSimpleValue() {
        return getValue();
    }

    //todo method
    @Override
    public String GetExplicitValue(EntityInstance primaryEntity, EntityInstance secondaryEntity) {
        return null;
    }
}

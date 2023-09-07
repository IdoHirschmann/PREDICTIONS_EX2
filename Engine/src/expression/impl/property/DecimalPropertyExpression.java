package expression.impl.property;

import entity.instance.EntityInstance;
import exception.PropertyNotFoundException;
import expression.ExpressionType;
import property.instance.AbstractPropertyInstance;

import java.util.Map;

public class DecimalPropertyExpression extends AbstractPropertyExpression {
    public DecimalPropertyExpression(String value) {
        super(value, ExpressionType.INT);
    }

    @Override
    public String GetExplicitValue(EntityInstance primaryEntity, EntityInstance secondaryEntity, Map<String, AbstractPropertyInstance> environments){
        String res = primaryEntity.getSpecificPropertyValue(getValue());
        if(res == null) {
            if (secondaryEntity != null) {
                res = secondaryEntity.getSpecificPropertyValue(getValue());
            }

            if(res == null) {
                throw new PropertyNotFoundException("PropertyNotFoundException: " + getValue() + "was not found!\n" +
                        "       Please make sure the property you enter to an action is in the right entity. Problem occurred in class DecimalPropertyExpression");
            }
        }
        return res;
    }
}

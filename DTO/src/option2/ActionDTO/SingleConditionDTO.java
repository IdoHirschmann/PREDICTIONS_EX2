package option2.ActionDTO;

public class SingleConditionDTO {

    private final String property;
    private final String value;
    private final String operator;
    private final Integer thisActionAmount;
    private final Integer elseActionAmount;

    public SingleConditionDTO(String property,
                              String value, String operator, Integer thisActionAmount, Integer elseActionAmount) {
        this.property = property;
        this.value = value;
        this.operator = operator;
        this.thisActionAmount = thisActionAmount;
        this.elseActionAmount = elseActionAmount;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }

    public Integer getThisActionAmount() {
        return thisActionAmount;
    }

    public Integer getElseActionAmount() {
        return elseActionAmount;
    }
}

package option2.ActionDTO;

public class MultipleConditionDTO  {
    private final String logic;
    private final Integer thisActionAmount;
    private final Integer elseActionAmount;

    public MultipleConditionDTO(String logic, Integer thisActionAmount, Integer elseActionAmount) {
        this.logic = logic;
        this.thisActionAmount = thisActionAmount;
        this.elseActionAmount = elseActionAmount;
    }

    public String getLogic() {
        return logic;
    }

    public Integer getThisActionAmount() {
        return thisActionAmount;
    }

    public Integer getElseActionAmount() {
        return elseActionAmount;
    }
}

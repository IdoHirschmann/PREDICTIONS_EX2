package option2.ActionDTO;

public class CalculationDTO {

    private final String arg1;
    private final String arg2;
    private final String resultProp;

    public CalculationDTO( String arg1, String arg2, String resultProp) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultProp = resultProp;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getResultProp() {
        return resultProp;
    }
}

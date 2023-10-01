package option2.ActionDTO;

public class IncreaseDTO{

    private String property;
    private String by;


    public IncreaseDTO(String property, String by) {

        this.property = property;
        this.by = by;
    }

    public String getProperty() {
        return property;
    }

    public String getBy() {
        return by;
    }
}

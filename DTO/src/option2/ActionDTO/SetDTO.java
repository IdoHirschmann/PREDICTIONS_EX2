package option2.ActionDTO;

public class SetDTO {
     private String value;
     private String property;

    public SetDTO(String value, String property) {
        this.value = value;
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public String getProperty() {
        return property;
    }
}

package option2.ActionDTO;



public class ActionDTO {
    private  String name;
    private  String mainEntityName;
    private  String secondaryEntityName;
    private IncreaseDTO increaseDTO;
    private CalculationDTO calculationDTO;
    private DecreaseDTO decreaseDTO;
    private KillDTO killDTO;
    private MultipleConditionDTO multipleConditionDTO;
    private ProximityDTO proximityDTO;
    private ReplaceDTO replaceDTO;
    private SetDTO setDTO;
    private SingleConditionDTO singleConditionDTO;


    public ActionDTO(String name, String mainEntityName, String secondaryEntityName, IncreaseDTO increaseDTO, CalculationDTO calculationDTO, DecreaseDTO decreaseDTO, KillDTO killDTO, MultipleConditionDTO multipleConditionDTO, ProximityDTO proximityDTO, ReplaceDTO replaceDTO, SetDTO setDTO, SingleConditionDTO singleConditionDTO) {
        this.name = name;
        this.mainEntityName = mainEntityName;
        this.secondaryEntityName = secondaryEntityName;
        this.increaseDTO = increaseDTO;
        this.calculationDTO = calculationDTO;
        this.decreaseDTO = decreaseDTO;
        this.killDTO = killDTO;
        this.multipleConditionDTO = multipleConditionDTO;
        this.proximityDTO = proximityDTO;
        this.replaceDTO = replaceDTO;
        this.setDTO = setDTO;
        this.singleConditionDTO = singleConditionDTO;
    }

    public IncreaseDTO getIncreaseDTO() {
        return increaseDTO;
    }

    public CalculationDTO getCalculationDTO() {
        return calculationDTO;
    }

    public DecreaseDTO getDecreaseDTO() {
        return decreaseDTO;
    }

    public KillDTO getKillDTO() {
        return killDTO;
    }

    public MultipleConditionDTO getMultipleConditionDTO() {
        return multipleConditionDTO;
    }

    public ProximityDTO getProximityDTO() {
        return proximityDTO;
    }

    public ReplaceDTO getReplaceDTO() {
        return replaceDTO;
    }

    public SetDTO getSetDTO() {
        return setDTO;
    }

    public SingleConditionDTO getSingleConditionDTO() {
        return singleConditionDTO;
    }

    public String getName() {
        return name;
    }

    public String getMainEntityName() {
        return mainEntityName;
    }
    public String getSecondaryEntityName() {
        return secondaryEntityName;
    }

}

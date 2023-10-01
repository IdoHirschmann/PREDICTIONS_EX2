package rule.action.impl;

import entity.definition.EntityDefinition;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.ReplaceDTO;
import rule.action.ActionType;
import rule.action.context.api.ActionContext;
import rule.action.impl.secondaryEntity.SecondaryEntity;

public class Replace extends AbstractAction {
    private String mode;

    public Replace(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition, String mode) {
        super(primaryEntityDefinition, secondaryEntityDefinition, ActionType.REPLACE);
        this.mode = mode;
    }

    @Override
    public ActionDTO createDTO() {
        ReplaceDTO replaceDTO = new ReplaceDTO(mode);
        if(getSecondaryEntity() == null) {
            return new ActionDTO("Replace", getPrimaryEntityDefinition().getName(), null,
                    null,null,null,null,null,null,replaceDTO,null,null
            );

        }
        return new ActionDTO("Replace", getPrimaryEntityDefinition().getName(), getSecondaryEntity().getEntityName(),
                null,null,null,null,null,null,replaceDTO,null,null
        );
    }

    public String getMode() {
        return mode;
    }

    @Override
    public void Invoke(ActionContext context) {
        context.getPrimaryEntityInstance().replaceMe();
        context.getPrimaryEntityInstance().setReplaceMode(mode);
        context.getPrimaryEntityInstance().setWhoToReplace(this.getSecondaryEntity().getEntityName());
    }
}

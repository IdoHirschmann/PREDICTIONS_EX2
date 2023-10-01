package rule.action.impl;

import entity.definition.EntityDefinition;
import exception.SecondEntityIgnoreException;
import expression.api.Expression;
import grid.GridIndex;
import option2.ActionDTO.ActionDTO;
import option2.ActionDTO.ProximityDTO;
import rule.action.ActionType;
import rule.action.api.Action;
import rule.action.context.api.ActionContext;
import rule.action.impl.secondaryEntity.SecondaryEntity;

import java.util.List;

public class Proximity extends AbstractAction {

   private Expression of;
   private List<Action> actionList;

    public Proximity(EntityDefinition primaryEntityDefinition, SecondaryEntity secondaryEntityDefinition, Expression of, List<Action> actionList) {
        super(primaryEntityDefinition, secondaryEntityDefinition, ActionType.PROXIMITY);
        this.of = of;
        this.actionList = actionList;
    }
    @Override
    public ActionDTO createDTO() {
        ProximityDTO proximityDTO = new ProximityDTO(of.GetSimpleValue(), Integer.toString(actionList.size()));
        if(getSecondaryEntity() == null) {
            return new ActionDTO("Proximity", getPrimaryEntityDefinition().getName(), null,
                    null,null,null,null,null,proximityDTO,null,null,null);
        }
        return new ActionDTO("Proximity", getPrimaryEntityDefinition().getName(), getSecondaryEntity().getEntityName(),
                null,null,null,null,null,proximityDTO,null,null,null);
    }

    @Override
    public void Invoke(ActionContext context) {
        Integer degree = null;

        try {
            degree = (int)Double.parseDouble(of.GetExplicitValue(context.getPrimaryEntityInstance(), context.getSecondaryEntityInstance(), context.getEnvironments(), true));
        } catch (SecondEntityIgnoreException secondEntityIgnoreException) {

        }

        GridIndex source = context.getPrimaryEntityInstance().getGridIndex();
        if(context.getSecondaryEntityInstance() == null){
            return;
        }
        GridIndex target = context.getSecondaryEntityInstance().getGridIndex();

        Integer rowsDist = Math.abs(source.getRow() - target.getRow());
        Integer colsDist = Math.abs(source.getCol() - target.getCol());

        rowsDist = Math.min(rowsDist, context.getRows() - rowsDist);
        colsDist = Math.min(colsDist, context.getCols() - colsDist);

        if(rowsDist <= degree && colsDist <= degree){
            for (Action action: actionList) {
                action.Invoke(context);
            }
            context.setStopAction(true);
        }
    }
}

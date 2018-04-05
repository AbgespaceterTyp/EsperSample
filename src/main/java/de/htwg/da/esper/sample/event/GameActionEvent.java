package de.htwg.da.esper.sample.event;

import java.util.Date;

public class GameActionEvent extends BaseEvent {

    private final GameActionType actionType;

    public GameActionEvent(String playerName, Date timestamp, GameActionType actionType) {
        super(playerName, timestamp);
        this.actionType = actionType;
    }

    public GameActionType getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return "GameActionEvent{" +
                "actionType=" + actionType +
                "} " + super.toString();
    }
}

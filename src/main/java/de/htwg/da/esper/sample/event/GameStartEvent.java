package de.htwg.da.esper.sample.event;

import java.util.Arrays;
import java.util.Date;

public class GameStartEvent extends BaseEvent {

    private final String[] playerNames;

    public GameStartEvent(String[] playerNames, Date timestamp) {
        super(playerNames[0], timestamp);
        this.playerNames = playerNames;
    }

    public int getPlayerCount() {
        return playerNames.length;
    }

    @Override
    public String toString() {
        return "GameStartEvent{" +
                "playerNames=" + Arrays.toString(playerNames) +
                "} " + super.toString();
    }
}

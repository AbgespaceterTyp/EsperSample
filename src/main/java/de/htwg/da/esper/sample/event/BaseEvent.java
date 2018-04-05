package de.htwg.da.esper.sample.event;

import com.espertech.esper.client.util.DateTime;

import java.util.Date;

public abstract class BaseEvent {
    private final String playerName;
    private final Date timestamp;

    BaseEvent(String playerName, Date timestamp){
        this.playerName = playerName;
        this.timestamp = timestamp;
    }

    public String getPlayerName(){
        return playerName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "BaseEvent{playerName='" + playerName + '\'' +
                ", timestamp=" + DateTime.print(timestamp) +
                '}';
    }
}

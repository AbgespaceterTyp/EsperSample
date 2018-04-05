package de.htwg.da.esper.sample.event;

import java.util.Date;

public class GameEndEvent extends BaseEvent {

    private final int prize;
    private final String deck;

    public GameEndEvent(String playerName, Date timestamp, int prize, Deck deck) {
        super(playerName, timestamp);
        this.prize = prize;
        this.deck = deck.name();
    }

    public int getPrize() {
        return prize;
    }

    public String getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return "GameEndEvent{" +
                "prize=" + prize +
                ", deck=" + deck +
                "} " + super.toString();
    }
}

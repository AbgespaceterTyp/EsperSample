package de.htwg.da.esper.sample.gen;

import de.htwg.da.esper.sample.event.*;

import java.util.Date;
import java.util.Random;

public final class EventGenerator {
    private static String[] PLAYER_NAMES = {"Clemens", "Lukas", "Ulrich", "Simon", "Kathrin", "Kay", "Isabelle", "Bastian"};
    private static Random GENERATOR = new Random();

    public static GameActionEvent randomGameActionEvent() {
        final String playerName = randomPlayerName();
        final Date timestamp = randomTimestamp();
        final GameActionType gameActionType = randomGameActionType();
        return new GameActionEvent(playerName, timestamp, gameActionType);
    }

    public static GameEndEvent randomGameEndEvent() {
        final String playerName = randomPlayerName();
        final Date timestamp = randomTimestamp();
        final int prize = GENERATOR.nextInt(500000);
        final Deck deck = randomDeck();

        return new GameEndEvent(playerName, timestamp, prize, deck);
    }

    public static GameStartEvent randomGameStartEvent() {
        final Date timestamp = randomTimestamp();

        return new GameStartEvent(PLAYER_NAMES, timestamp);
    }

    private static Deck randomDeck() {
        int deckIndex = GENERATOR.nextInt(Deck.values().length);
        return Deck.values()[deckIndex];
    }

    private static GameActionType randomGameActionType() {
        int gameActionTypeIndex = GENERATOR.nextInt(GameActionType.values().length);
        return GameActionType.values()[gameActionTypeIndex];
    }

    private static String randomPlayerName() {
        int playerIndex = GENERATOR.nextInt(PLAYER_NAMES.length);
        String playerName = PLAYER_NAMES[playerIndex];
        return playerName;
    }

    private static Date randomTimestamp() {
        long timeStamp = System.currentTimeMillis();
        return new Date(timeStamp);
    }
}

package de.htwg.da.esper.sample;

import com.espertech.esper.client.*;
import de.htwg.da.esper.sample.event.GameActionEvent;
import de.htwg.da.esper.sample.event.GameEndEvent;
import de.htwg.da.esper.sample.event.GameStartEvent;
import de.htwg.da.esper.sample.gen.EventGenerator;
import de.htwg.da.esper.sample.listener.GameActionListener;
import de.htwg.da.esper.sample.listener.GameEndListener;
import de.htwg.da.esper.sample.listener.KickOutListener;

public class EsperSample {
    public static void main(String[] args) {
        Configuration esperConfig = createConfig();
        EPServiceProvider cep = EPServiceProviderManager.getProvider(EsperSample.class.getSimpleName(), esperConfig);

        setupSelectStatements(cep.getEPAdministrator());
        setupPatternStatements(cep.getEPAdministrator());

        // Fire sample events
        final EPRuntime cepRT = cep.getEPRuntime();
        for (int i = 0; i < 10; i++) {
            final GameStartEvent gameStartEvent = EventGenerator.randomGameStartEvent();
            System.out.println("Sending GameStartEvent:" + gameStartEvent);
            cepRT.sendEvent(gameStartEvent);
            try {
                for(int j = 0; j < 20; j++){
                    Thread.sleep(50);
                    final GameActionEvent gameActionEvent = EventGenerator.randomGameActionEvent();
                    System.out.println("Sending GameActionEvent:" + gameActionEvent);
                    cepRT.sendEvent(gameActionEvent);
                }
                final GameEndEvent gameEndEvent = EventGenerator.randomGameEndEvent();
                System.out.println("Sending GameEndEvent:" + gameEndEvent);
                cepRT.sendEvent(gameEndEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setupPatternStatements(EPAdministrator cepAdm){
        EPStatement royalFlushStatement = cepAdm.createPattern("every a=Action and not b=GameStart and not c=GameEnd");
        royalFlushStatement.addListener(new GameActionListener());
    }

    private static void setupSelectStatements(EPAdministrator cepAdm){
        EPStatement royalFlushStatement = cepAdm.createEPL("select playerName,deck from GameEnd(deck='ROYAL_FLUSH')");
        royalFlushStatement.addListener(new GameEndListener());

        EPStatement winOverviewStatement = cepAdm.createEPL("select playerName,count(playerName) as wins,sum(prize) as prize_sum from GameEnd.win:length(10) group by playerName");
        winOverviewStatement.addListener(new GameEndListener());

        EPStatement maxPrizeStatement = cepAdm.createEPL("select playerName, sum(prize) as prize_sum from GameEnd.win:length(10) having sum(prize) > 2000000");
        maxPrizeStatement.addListener(new KickOutListener());
    }

    private static Configuration createConfig(){
        Configuration config = new Configuration();
        // Register event types to observe
        config.addEventType("GameStart", GameStartEvent.class.getName());
        config.addEventType("Action", GameActionEvent.class.getName());
        config.addEventType("GameEnd", GameEndEvent.class.getName());
        return config;
    }
}

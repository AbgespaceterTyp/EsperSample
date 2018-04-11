package de.htwg.da.esper.sample.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class KickOutListener implements UpdateListener{

    public void update(EventBean[] newData, EventBean[] oldDate) {
        final Object prize_sum = newData[0].get("prize_sum");
        final Object playerName = newData[0].get("playerName");

        System.out.println("------> Player '" + playerName + "' has won too much '"+ prize_sum +"' - kick him/her out!");
    }
}

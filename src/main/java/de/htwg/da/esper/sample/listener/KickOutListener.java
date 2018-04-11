package de.htwg.da.esper.sample.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class KickOutListener implements UpdateListener{

    public void update(EventBean[] newData, EventBean[] oldDate) {
        final Integer prize_sum = (Integer) newData[0].get("prize_sum");
        final Object playerName = newData[0].get("playerName");

        String formattedSum = String.format("%,d", prize_sum);
        System.out.println("------> Player '" + playerName + "' has won too much '"+ formattedSum +"' - kick him/her out!");
    }
}

package de.htwg.da.esper.sample.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class GameEndListener implements UpdateListener {

    public void update(EventBean[] newData, EventBean[] oldData) {
        for(int i = 0; i < newData.length; i++) {
            Object event = newData[i].getUnderlying();
            System.out.println("------> Game end result: " + event);
        }
    }
}

package de.htwg.da.esper.sample.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class GameEndListener implements UpdateListener {

    public void update(EventBean[] newData, EventBean[] oldData) {
        Object event = newData[0].getUnderlying();

        System.out.println("------> Game ends: " + event);
    }
}

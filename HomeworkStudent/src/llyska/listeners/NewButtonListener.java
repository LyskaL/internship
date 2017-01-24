package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class NewButtonListener implements Listener {

    public NewButtonListener() {
        //super();

    }

    @Override
    public void handleEvent(Event event) {
        System.out.println("you pressing New button");
    }

}

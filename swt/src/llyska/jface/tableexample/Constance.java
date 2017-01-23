package llyska.jface.tableexample;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class Constance {
    public static Display DISPLAY;
    public static Image CHECKED;
    public static Image UNCHECKED;

    public static void setDisplay(Display display) {
        DISPLAY = display;
        CHECKED = new Image(display, "./src/resources/CHECKED.png");
        UNCHECKED = new Image(display, "./src/resources/UNCHECKED.png");
    }
}
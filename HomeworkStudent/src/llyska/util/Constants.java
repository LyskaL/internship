package llyska.util;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import llyska.services.TableService;
import llyska.services.TableServiceImp;

/**
 * The class stores constants required for working other classes.
 *
 * @author Lyska Lyudmila
 */
public class Constants {
    /** Link to Display **/
    public static Display DISPLAY;
    /** Link to image with checkbox is selected **/
    public static Image CHECKED;
    /** Link to image with checkbox isn't selected **/
    public static Image UNCHECKED;
    /** Service for working with data in table **/
    public static TableService TABLE_SERVICE;

    /**
     * Initialization all constants upon creation a program.
     *
     * @param display for our program.
     */
    public static void setDisplay(Display display) {
        DISPLAY = display;
        CHECKED = new Image(display, "./src/resources/CHECKED.png");
        UNCHECKED = new Image(display, "./src/resources/UNCHECKED.png");
        TABLE_SERVICE = new TableServiceImp();
    }
}

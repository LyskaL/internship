package llyska.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Constants {
	public static Display DISPLAY;
	public static Color RED;
	public static Color GRAY;
	public static Color GREEN;
	public static Color YELLOW;
	
	public static void setDisplay(Display display) {
		DISPLAY = display;
		RED = Constants.DISPLAY.getSystemColor(SWT.COLOR_RED);
		GRAY = Constants.DISPLAY.getSystemColor(SWT.COLOR_GRAY);
		GREEN = Constants.DISPLAY.getSystemColor(SWT.COLOR_GREEN);
		YELLOW = Constants.DISPLAY.getSystemColor(SWT.COLOR_YELLOW);
	}
}

package eclipsercp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import viewpart.CalculatorTab;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
	    layout.setEditorAreaVisible(false);

	    layout.addView(CalculatorTab.ID, IPageLayout.LEFT, 1.0f, layout.getEditorArea());

	   /* layout.addShowViewShortcut(CalculatorTab.ID);
	    layout.addView(HistoryTab.ID, IPageLayout.RIGHT,
	            1.0f, layout.getEditorArea());*/

	    // layout.addView(CalculatorTab.ID, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	}
}

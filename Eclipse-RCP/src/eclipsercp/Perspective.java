package eclipsercp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
	    layout.addView("MyView", IPageLayout.TOP,
              IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
	}
}

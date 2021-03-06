package eclipsercp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import viewpart.ContactsView;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
	    layout.setEditorAreaVisible(false);
	    layout.addStandaloneView(ContactsView.ID, false, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	}
}

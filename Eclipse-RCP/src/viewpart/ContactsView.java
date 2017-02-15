package viewpart;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import events.GroupChangeListener;
import services.GroupService;
import services.GroupServiceImpl;
import treeprovider.LabelContentProvider;
import treeprovider.TreeContentProvider;

public class ContactsView extends ViewPart implements GroupChangeListener {

    public static final String ID = "Eclipse-RCP.viewpart.contacts";

    private TreeViewer _treeViewer;
    private Composite _parent;
    private final GroupService _service = GroupServiceImpl.getInstance();

    public ContactsView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        _parent = parent;
        _service.addDataEventListener(this);

        _treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        _treeViewer.setContentProvider(new TreeContentProvider());
        _treeViewer.setInput(_service.getGroupRoot());
        _treeViewer.setLabelProvider(new LabelContentProvider());
        getSite().setSelectionProvider(_treeViewer);
        _treeViewer.getTree().setLinesVisible(true);
    }

    @Override
    public void setFocus() {
        _treeViewer.getControl().setFocus();
    }

    @Override
    public void groupChanged() {
       _treeViewer.refresh();
       _parent.redraw();
    }

}

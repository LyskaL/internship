package viewpart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import services.HistoryManager;
import services.ServiceProvider;

public class HistoryView extends Composite{
    private final List _history;
    private final Button _clean;
    private final Button _remove;
    private final Button _deselect;

    public HistoryView(Composite parent) {
      super(parent, SWT.BORDER);
      setLayout(new GridLayout(1, true));

      _history = new List(this, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
      _history.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      _history.setBackground(getBackground());

      _history.addListener(SWT.Selection, new Listener() {
        @Override
        public void handleEvent(Event e) {
          _remove.setEnabled(true);
          _deselect.setEnabled(true);
        }
      });

      Composite buttonsPanel = new Composite(this, SWT.NONE);
      buttonsPanel.setLayout(new GridLayout(3, true));

      _clean = new Button(buttonsPanel, SWT.NONE);
      _clean.setText("Clean");
      _clean.setEnabled(false);
      _clean.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
      _clean.addSelectionListener(new SelectionListener() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          ServiceProvider.getService(HistoryManager.class).clean();
          _history.removeAll();
        }
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {}
      });

      _remove = new Button(buttonsPanel, SWT.NONE);
      _remove.setText("Remove");
      _remove.setEnabled(false);
      _remove.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
      _remove.addSelectionListener(new SelectionListener() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          int[] indexes = _history.getSelectionIndices();
          ServiceProvider.getService(HistoryManager.class).removeItems(indexes);
        }
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {}
      });
      _deselect = new Button(buttonsPanel, SWT.NONE);
      _deselect.setText("Deselect");
      _deselect.setEnabled(false);
      _deselect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
      _deselect.addSelectionListener(new SelectionListener() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          _history.deselectAll();
          _deselect.setEnabled(false);
          _remove.setEnabled(false);
        }
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {}
      });
    }

    public void cleanHistory() {
      _history.removeAll();
      _deselect.setEnabled(false);
      _clean.setEnabled(false);
      _remove.setEnabled(false);
    }

    public void createHistory(java.util.List<String> history) {
      _clean.setEnabled(true);
      for (String string : history) {
        _history.add(string);
      }
    }
}

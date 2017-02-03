package services;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import events.DataEvent;
import events.DataEventListener;
import util.Constants;
import viewpart.HistoryView;

public class HistoryManagerImpl implements HistoryManager, DataEventListener {
  private final HistoryService _historiService;
  private final HistoryView _historyView;

  public HistoryManagerImpl() {
    Shell shell = new Shell(Constants.DISPLAY);
    _historiService = new HistoryServiceImpl();
    _historiService.addDataEventListener(this);
    _historyView = new HistoryView(shell);
  }

  @Override
  public void clean() {
    _historiService.clean();
  }

  @Override
  public void remove(int index) {
    _historiService.removeItem(index);
  }

  @Override
  public Composite getCompositeToHistoryView() {
    return _historyView;
  }

  @Override
  public void setParentToHistoryView(Composite parent) {
    _historyView.setParent(parent);
  }

  @Override
  public void addItem(String result) {
    _historiService.addItem(result);
  }

  @Override
  public void removeItems(int[] indexes) {
    _historiService.removeItems(indexes);
  }

  @Override
  public void handleEvent(DataEvent e) {
    _historyView.cleanHistory();
    _historyView.createHistory(_historiService.getHistory());
  }
}
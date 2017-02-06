package llyska.jface.treeviewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class Main extends ApplicationWindow
{
   private final Category rootCategory;

   public Main() {
      super(null);

      // statically create categories
      rootCategory = new Category("Java", null);
      Category j2seCategory = new Category("J2SE", rootCategory);

      Link link = new Link("http://java.sun.com/j2se/");
      j2seCategory.addLink(link);
      Category j2eeCategory = new Category("J2EE", rootCategory);
      link = new Link("http://java.sun.com/j2se/");
      j2eeCategory.addLink(link);
      Category j2meCategory = new Category("J2ME", rootCategory);
      link = new Link("http://java.sun.com/j2se/");
      j2meCategory.addLink(link);

      rootCategory.addSubCategory(j2seCategory);
      rootCategory.addSubCategory(j2eeCategory);
      rootCategory.addSubCategory(j2meCategory);
   }

   @Override
protected Control createContents(Composite parent) {
      TreeViewer treeViewer = new TreeViewer(parent);
      treeViewer.setContentProvider(new CategoryTreeContentProvider());
      treeViewer.setInput(rootCategory);
      treeViewer.setLabelProvider(new CategoryTreeLabelProvider());
      return treeViewer.getTree();
   }

   public static void main(String []args) {
      Main main = new Main();
      main.setBlockOnOpen(true);
      main.open();
      Display.getCurrent().dispose();
   }
}

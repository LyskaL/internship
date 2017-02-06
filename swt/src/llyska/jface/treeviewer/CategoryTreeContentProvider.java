package llyska.jface.treeviewer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CategoryTreeContentProvider implements ITreeContentProvider
{
   public Object[] getChildren(Object element) {
      Category category = (Category) element;
      System.out.println(category.getName());

      ArrayList children = new ArrayList();
      children.addAll(category.getSubCategories());
      children.addAll(category.getLinks());

      if (children.size() == 0) {
         return new Object[0];
      }

      return children.toArray();
   }

   public Object[] getElements(Object element) {
      return getChildren(element);
   }

   public boolean hasChildren(Object element) {
      if (element instanceof Category) {
         Category category = (Category) element;

         return category.hasSubCategories() || category.hasLinks();
      }

      return false;
   }

   public Object getParent(Object element) {
      if (element instanceof Category) {
         return ((Category) element).getParent();
      }

      return null;
   }

   public void dispose() {

   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

   }
}
package llyska.jface.treeviewer;

import java.util.ArrayList;

public class Category {

    private final String name;
    private final Category parent;
    private final ArrayList categories = new ArrayList();
    private final ArrayList links = new ArrayList();

    public Category(String name, Category parent) {
       this.name = name;
       this.parent = parent;
    }

    public void addSubCategory(Category category) {
       categories.add(category);
    }

    public void addLink(Link link) {
       links.add(link);
    }

    public ArrayList getSubCategories() {
       return categories;
    }

    public ArrayList getLinks() {
       return links;
    }

    public boolean hasSubCategories() {
       return categories.size() > 0;
    }

    public boolean hasLinks() {
       return links.size() > 0;
    }

    public String getName() {
       return name;
    }

    public Category getParent() {
       return parent;
    }

    @Override
    public String toString() {
       return name;
    }
 }
package llyska.jface.treeviewer;

public class Link {

    private final String url;

    public Link(String url) {
       this.url = url;
    }

    public String getUrl() {
       return url;
    }

    @Override
    public String toString() {
       return url;
    }
 }

package llyska.jface.tableexample;

public class Student {
    private final String _name;

    private boolean _swtDone;

    public Student(String name, boolean swtDone) {
        // TODO Validator
        _name = name;
        _swtDone = swtDone;
    }

    public String getName() {
        return _name;
    }

    public boolean isSwtDone() {
        return _swtDone;
    }

    public void setSwtDone(boolean swtDone) {
        _swtDone = swtDone;
    }

}

package llyska.entities;

public class Student {
    private String _name;

    private boolean _swtDone;

    public Student(String _name, boolean _swtDone) {
        // TODO Validator
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

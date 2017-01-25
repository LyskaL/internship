package llyska.entities;

public class Student {

    private final String _name;
    private String _numberGroup;
    private boolean _swtDone;

    public Student(String name, String numberGroup, boolean swtDone) {
        // TODO Validator
        _name = name;
        _swtDone = swtDone;
        _numberGroup = numberGroup;
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

    public String getNumberGroup() {
        return _numberGroup;
    }

    public void setNumberGroup(String numberGroup) {
        _numberGroup = numberGroup;
    }

    @Override
    public String toString() {
        return "Student [_name=" + _name + ", _numberGroup=" + _numberGroup + ", _swtDone=" + _swtDone + "]";
    }
}

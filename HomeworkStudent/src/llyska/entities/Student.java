package llyska.entities;

public class Student {
    private final String _name;
    private int _numberGroup;
    private boolean _swtDone;

    public Student(String name, int numberGroup, boolean swtDone) {
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

    public int getNumberGroup() {
        return _numberGroup;
    }

    public void setNumberGroup(int numberGroup) {
        _numberGroup = numberGroup;
    }

}

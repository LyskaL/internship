package entities;

public class User {
    private String _login;
    private String _service;
    private String _name;

    public User(String login, String service, String name) {
        setLogin(login);
        setName(name);
        setService(service);
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    @Override
    public String toString() {
        return "" +_login + " (" + _name + "@" + _service +")";
    }

    public String getService() {
        return _service;
    }

    public void setService(String service) {
        _service = service;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

}

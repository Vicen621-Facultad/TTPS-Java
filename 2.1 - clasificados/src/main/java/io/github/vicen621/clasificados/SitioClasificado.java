package io.github.vicen621.clasificados;


public class SitioClasificado {
    private String email;
    private String name;
    private String phone;

    public SitioClasificado(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

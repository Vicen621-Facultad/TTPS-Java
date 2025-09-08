package io.github.vicen621.clasificados.users;

public enum UserType {
    ADMIN("Dashboard", "Manage Users", "Settings", "Reports"),
    PUBLISHER("Create Ad", "My Ads", "Profile"),;

    private final String[] menuEntries;

    UserType(String... menuEntries) {
        this.menuEntries = menuEntries;
    }

    public String[] getMenuEntries() {
        return menuEntries;
    }
}

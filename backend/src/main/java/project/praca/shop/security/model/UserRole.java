package project.praca.shop.security.model;

public enum UserRole {

    ROLE_CUSTOMER("CUSTOMER"),
    ROLE_ADMIN("ADMIN");


    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

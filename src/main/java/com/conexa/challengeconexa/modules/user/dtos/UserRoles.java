package com.conexa.challengeconexa.modules.user.dtos;

public enum UserRoles {
    User("user");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

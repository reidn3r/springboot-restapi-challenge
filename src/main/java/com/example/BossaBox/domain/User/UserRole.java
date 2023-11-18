package com.example.BossaBox.domain.User;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    public String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

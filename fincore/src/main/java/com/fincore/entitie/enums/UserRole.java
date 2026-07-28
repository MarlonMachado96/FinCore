package com.fincore.entitie.enums;

public enum UserRole {
    ADMIN("admin"),
    CONTADOR("contado"),
    EMPRESARIO("Empresário"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
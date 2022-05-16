package ru.kronx.backend.ecomoney.model;

public enum Permission {
    DEF_PERMISSION("user:defalut_permission"),
    MANAGE_USER("administrator:CRUD_USER");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

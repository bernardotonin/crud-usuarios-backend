package com.desafio.crud_usuarios.core.dto;

import com.desafio.crud_usuarios.core.model.Permission;
import com.desafio.crud_usuarios.core.model.User;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private Boolean active;
    private String profilePicture;
    private Permission permission;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.profilePicture = user.getProfilePicture() != null ? "http://localhost:8080/" + user.getProfilePicture().replace("\\", "/") : null;
        this.username = user.getUsername();
        this.active = user.isActive();
        this.permission = user.getPermission() != null ? user.getPermission() : null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getActive() {
        return active;
    }

    public Permission getPermission() {
        return permission;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}

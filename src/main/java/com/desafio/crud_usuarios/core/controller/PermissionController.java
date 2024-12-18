package com.desafio.crud_usuarios.core.controller;

import com.desafio.crud_usuarios.core.model.Permission;
import com.desafio.crud_usuarios.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok(createdPermission);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }
}
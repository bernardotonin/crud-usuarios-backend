package com.desafio.crud_usuarios.core.service;

import com.desafio.crud_usuarios.core.model.Permission;
import com.desafio.crud_usuarios.core.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}

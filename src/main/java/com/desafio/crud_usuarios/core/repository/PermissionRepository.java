package com.desafio.crud_usuarios.core.repository;

import com.desafio.crud_usuarios.core.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}

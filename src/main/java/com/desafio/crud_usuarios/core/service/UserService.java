package com.desafio.crud_usuarios.core.service;

import com.desafio.crud_usuarios.core.dto.ProfilePictureResponseDTO;
import com.desafio.crud_usuarios.core.dto.UserDTO;
import com.desafio.crud_usuarios.core.dto.UserResponseDTO;
import com.desafio.crud_usuarios.core.dto.UserUpdateDTO;
import com.desafio.crud_usuarios.core.model.Permission;
import com.desafio.crud_usuarios.core.model.User;
import com.desafio.crud_usuarios.core.repository.PermissionRepository;
import com.desafio.crud_usuarios.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) throws IllegalStateException {
        long activeUsersCount = userRepository.countActiveUsers();

        if (activeUsersCount >= 5 && userDTO.isActive()) {
            throw new IllegalStateException("Apenas 5 usuários podem estar ativos simultaneamente!");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());

        Permission permission = permissionRepository.findById(userDTO.getPermissionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid permission ID: " + userDTO.getPermissionId()));
        user.setPermission(permission);

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser);
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO userDTO) throws IllegalStateException {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário: " + id + " não encontrado");
        }

        User existingUser = existingUserOpt.get();

        if (!existingUser.isActive() && userDTO.getActive()) {
            long activeUsersCount = userRepository.countActiveUsers();

            if (activeUsersCount >= 5) {
                throw new IllegalStateException("Apenas 5 usuários podem estar ativos simultaneamente!");
            }
        }

        // Atualiza os campos do usuário
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setActive(userDTO.getActive());

        if (userDTO.getPermissionId() != null) {
            Permission permission = permissionRepository.findById(userDTO.getPermissionId())
                    .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada"));
            existingUser.setPermission(permission);
        }

        User updatedUser = userRepository.save(existingUser);
        return new UserResponseDTO(updatedUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return userOptional.get();
    }

    public List<User> searchUsersByName(String namePart) {
        return userRepository.findByNameContainingIgnoreCase(namePart);
    }

    public ProfilePictureResponseDTO uploadProfilePicture(Long id, MultipartFile file) throws IOException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        User user = userOptional.get();

        String uploadDir = "uploads/profile-pictures/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = "user-" + id + "-" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        user.setProfilePicture(filePath.toString());
        userRepository.save(user);

        return new ProfilePictureResponseDTO("Imagem de perfil enviada com sucesso!", filePath.toString());
    }
}

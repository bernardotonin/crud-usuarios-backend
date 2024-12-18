package com.desafio.crud_usuarios.core.dto;

public class ProfilePictureResponseDTO {
    private String message;
    private String filePath;

    public ProfilePictureResponseDTO(String message, String filePath) {
        this.message = message;
        this.filePath = filePath;
    }

    public String getMessage() {
        return message;
    }

    public String getFilePath() {
        return filePath;
    }
}


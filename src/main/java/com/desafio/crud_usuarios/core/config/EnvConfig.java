package com.desafio.crud_usuarios.core.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    public static void loadEnvFile(){
        Dotenv dotenv = Dotenv.configure().load();

        String dataBaseUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");

        System.setProperty("DB_USERNAME", dbUser);
        System.setProperty("DB_PASSWORD", dbPassword);
        System.setProperty("DB_URL", dataBaseUrl);
    }
}
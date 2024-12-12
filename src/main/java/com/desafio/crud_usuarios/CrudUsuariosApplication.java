package com.desafio.crud_usuarios;

import com.desafio.crud_usuarios.core.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudUsuariosApplication {
    public static void main(String[] args) {
		EnvConfig.loadEnvFile();
        SpringApplication.run(CrudUsuariosApplication.class, args);
    }

}

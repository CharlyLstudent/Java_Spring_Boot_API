package fr.campus.cda.charly.java_spring_boot_api.dto;

import java.util.List;

public class UserTokenDTO {
        private String username; // Vous pouvez ajouter d'autres champs selon les informations que vous souhaitez renvoyer, par exemple :
        private String token; // Le jeton JWT

        public UserTokenDTO(String username) {
            this.username = username;

        }
        // Getters et Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }



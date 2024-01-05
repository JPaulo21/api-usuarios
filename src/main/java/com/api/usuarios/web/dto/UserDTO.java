package com.api.usuarios.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "Login deve ser preenchido!")
        String username,
        @NotBlank(message = "Passaword deve ser preenchido!")
        String password) {
}

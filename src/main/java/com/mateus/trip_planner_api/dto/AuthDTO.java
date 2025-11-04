package com.mateus.trip_planner_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "e-mail inválido")
    private String email;

    @NotBlank(message = "Senha obrigatória")
    private String password;
}

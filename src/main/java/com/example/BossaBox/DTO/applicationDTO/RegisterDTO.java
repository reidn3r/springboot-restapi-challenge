package com.example.BossaBox.DTO.applicationDTO;

import com.example.BossaBox.domain.User.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank String username,
                          @NotBlank String password,
                          @NotNull UserRole role) {
}

package com.example.BossaBox.DTO;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(@NotNull String username,
                                @NotNull String password) {
}

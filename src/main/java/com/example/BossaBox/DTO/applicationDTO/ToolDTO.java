package com.example.BossaBox.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ToolDTO(@NotBlank String title,
                      @NotBlank String link,
                      @NotBlank String description,
                      @NotNull List<String> tags) {
}

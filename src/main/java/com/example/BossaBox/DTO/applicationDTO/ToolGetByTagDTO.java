package com.example.BossaBox.DTO.applicationDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ToolGetByTagDTO {
    private Integer id;
    private String title;
    private String link;
    private String description;
    private List<String> tags = new ArrayList<>();

}

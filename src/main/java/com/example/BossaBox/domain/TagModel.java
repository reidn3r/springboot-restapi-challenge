package com.example.BossaBox.domain;

import com.example.BossaBox.DTO.ToolDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_tags")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class TagModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tool_id")
    @JsonIgnore
    private ToolModel tool_model;

    public TagModel(String tag){
        this.description=tag;
    }
}

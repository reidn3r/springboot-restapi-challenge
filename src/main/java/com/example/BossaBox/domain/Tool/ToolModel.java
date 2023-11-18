package com.example.BossaBox.domain.Tool;

import com.example.BossaBox.DTO.ToolDTO;
import com.example.BossaBox.domain.Tag.TagModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tb_tools")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ToolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String link;

    private String description;

    @OneToMany(mappedBy = "tool_model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagModel> tags;

    public ToolModel(ToolDTO data){
        this.title = data.title();
        this.link = data.link();
        this.description = data.description();
    }

}

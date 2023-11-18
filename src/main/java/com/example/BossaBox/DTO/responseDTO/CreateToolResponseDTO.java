package com.example.BossaBox.DTO.responseDTO;

import com.example.BossaBox.domain.Tag.TagModel;
import com.example.BossaBox.domain.Tool.ToolModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateToolResponseDTO {
    private String title;
    private String link;
    private String description;
    private ArrayList<String> tags = new ArrayList<String>();

    public CreateToolResponseDTO(ToolModel data){
        this.title = data.getTitle();
        this.link = data.getLink();
        this.description = data.getDescription();
        if(data.getTags() != null){
            for(TagModel tag : data.getTags()){
                tags.add(tag.getDescription());
            }
        }
    }
}

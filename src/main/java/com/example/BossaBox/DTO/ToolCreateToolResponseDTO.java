package com.example.BossaBox.DTO;

import com.example.BossaBox.domain.TagModel;
import com.example.BossaBox.domain.ToolModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolCreateToolResponseDTO {
    private String title;
    private String link;
    private String description;
    private ArrayList<String> tags = new ArrayList<String>();

    public ToolCreateToolResponseDTO(ToolModel data){
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

package com.example.BossaBox.DTO;

import com.example.BossaBox.domain.TagModel;
import com.example.BossaBox.domain.ToolModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolGetAllResponseDTO {
    private Integer id;
    private String title;
    private String link;
    private String description;
    private List<String> tags = new ArrayList<String>();

    public ToolGetAllResponseDTO(ToolModel data){
        this.id = data.getId();
        this.title = data.getTitle();
        this.link = data.getLink();
        this.description= data.getDescription();

        if(data.getTags() != null) {
            for(TagModel tag : data.getTags()){
                this.tags.add(tag.getDescription());
            }
        }
    }
}

package com.example.BossaBox.controller;

import com.example.BossaBox.DTO.CreateToolResponseDTO;
import com.example.BossaBox.DTO.ToolDTO;
import com.example.BossaBox.DTO.GetAllResponseDTO;
import com.example.BossaBox.domain.Tag.TagModel;
import com.example.BossaBox.domain.Tool.ToolModel;
import com.example.BossaBox.repository.TagRepository;
import com.example.BossaBox.repository.ToolRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/tools")
public class ToolController {
    @Autowired
    ToolRepository toolRepo;

    @Autowired
    TagRepository tagRepo;

    @GetMapping
    public ResponseEntity<List<GetAllResponseDTO>> getAllTools(@RequestParam(required = false) String tag){
        if(tag != null && !tag.isEmpty()){
            /* 1. Laço executado caso a requisição tenha parâmetro "tag" */
            List<TagModel> foundDataByTag = tagRepo.findToolIdByDescription(tag);
            if(foundDataByTag == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());

            /* 2. HashMap criado para armazenar dados únicos baseado no id
                do dado encontrado no banco de dados */
            Map<Integer, ToolModel> dataMap = new HashMap<Integer, ToolModel>();
            for(TagModel foundTag : foundDataByTag){
                dataMap.put(foundTag.getTool_model().getId(), foundTag.getTool_model());
            }

            /* 3. Transferência dos dados do HashMap para a Lista de resposta */
            List<GetAllResponseDTO> response = new ArrayList<>();
            for(ToolModel tool : dataMap.values()){
                GetAllResponseDTO item = new GetAllResponseDTO(tool);
                response.add(item);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            /* 1. Laço executado caso a requisição não tenha parâmetros */
            List<ToolModel> foundData = toolRepo.findAll();
            List<GetAllResponseDTO> response = new ArrayList<>();
            for(ToolModel model : foundData){
                GetAllResponseDTO dtoResponse = new GetAllResponseDTO(model);
                response.add(dtoResponse);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTool(@RequestBody @Valid ToolDTO data){
        /* 1. Criação de novo dado
        *       - Inicialmente, a ferramenta é salva no banco de dados (caso ainda não esteja registrada)
        *       - Em seguida, as tags são salvas separadamente e com a chave estrangeira associada
        *           corretamente ao dado salvo anteriormente
        *       - O novo dado pode ser salvo mesmo sem tag's associadas */
        if(this.toolRepo.findByTitle(data.title()) != null ||
            this.toolRepo.findByLink(data.link()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Ferramenta ja cadastrada");

        ToolModel toolCreated = new ToolModel(data);
        toolRepo.save(toolCreated);

        List<TagModel> tags = new ArrayList<>();
        for(String t : data.tags()){
            TagModel newTag = new TagModel(t);
            newTag.setTool_model(toolCreated); //Método que faz associação da chaves estrangeira
            tagRepo.save(newTag);
            tags.add(newTag);
        }

        toolCreated.setTags(tags);
        CreateToolResponseDTO response = new CreateToolResponseDTO(toolCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTool(@PathVariable(value = "id") Integer id){
        /* Deleção por id:
         *       1. Dados na tabela tb_tags são removidos automaticamente (Delete em Cascata)
         *           graças ao parâmetro "orphanRemoval" definido na anotação
         *           @OneToMany na classe ToolModel */
        Optional<ToolModel> foundData = toolRepo.findById(id);
        if(foundData.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.EMPTY_LIST);
        toolRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.EMPTY_LIST);
    }
}

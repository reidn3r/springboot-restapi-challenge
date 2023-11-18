package com.example.BossaBox.repository;

import com.example.BossaBox.domain.Tool.ToolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<ToolModel, Integer> {
    //query
    public ToolModel findByTitle(String title);
    public ToolModel findByLink(String link);
    public ToolModel findByDescription(String description);
}
